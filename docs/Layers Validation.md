Here is the explanation enriched with concrete Java/Spring Boot code examples, formatted completely in Markdown so you can easily copy and paste it into your notes or documentation.

***

# Different Layers, Different Responsibilities

Think of your application in layers, each with a specific job when it comes to validation.

### 1. Adapter/Controller Layer (The Boundary)
*   **Job:** Receives raw input from the outside world (e.g., Postman, web frontend).
*   **Validation Concern:** "Is this input well-formed? Does it meet the basic syntax and format rules?"

### 2. Application Layer (Use Cases & Commands)
*   **Job:** Orchestrates the business process.
*   **Validation Concern:** "Does this request make sense in the context of the business rule I'm about to execute?"

### 3. Domain Layer (Entities)
*   **Job:** Represents the core business concepts and guarantees its own internal consistency.
*   **Validation Concern:** "Can I exist in this state? Are my internal business rules (invariants) being protected?"

---

## Applying this to Your Code

### 1. The Command's Job: Input Validation (Boundary)
When you put `@Email` and `@Pattern` on the `RegisterUserCommand`, you are telling the framework: *"Before you even bother calling my use case, check if the incoming data is syntactically valid. If the email isn't an email or the password doesn't meet the complexity rules, reject it immediately."*
This is the first line of defense. It's efficient and happens at the boundary.

**Example: The Command & Controller**
```java
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// 1. The Command (Data Transfer Object)
public record RegisterUserCommand(
    @NotBlank(message = "Email is required") 
    @Email(message = "Must be a valid email format") 
    String email,

    @NotBlank(message = "Password is required") 
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$", 
             message = "Password must be 8-30 characters and contain letters and numbers") 
    String rawPassword,

    @NotBlank(message = "Full name is required") 
    String fullName
) {}

// 2. The Controller (Adapter)
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final RegisterUserUseCase useCase;

    public UserController(RegisterUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        // @Valid triggers the annotations in the Command BEFORE this code runs.
        useCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
```

### 2. The Use Case's Job: Orchestration
The Use Case acts as the bridge. It takes the *already syntactically validated* raw password, encrypts it, and passes the safe, encrypted hash to the Domain Entity.

**Example: The Use Case (Application Layer)**
```java
@Service
public class RegisterUserUseCase {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder; // e.g., BCrypt

    public void execute(RegisterUserCommand command) {
        // Check business rules (e.g., uniqueness)
        if (repository.existsByEmail(command.email())) {
            throw new EmailAlreadyExistsException("Email is already in use.");
        }

        // Transform raw data into domain-ready data
        String hashedPassword = passwordEncoder.encode(command.rawPassword());

        // Create the domain entity
        User newUser = new User(command.email(), hashedPassword, command.fullName());

        repository.save(newUser);
    }
}
```

### 3. The User Domain's Job: Invariant Protection
The `User` entity should only be concerned with its own state. It should ask questions like:
*   "Do I have an email? It can't be null or empty."
*   "Do I have a password hash? It can't be null or empty."
*   "Is my fullName not empty?"

**Notice the difference:** The `User` entity *doesn't care* about the format of the raw password, because it should never see the raw password. It only ever holds the final, encrypted hash. Validating a 60-character BCrypt hash against a regex meant for an 8-30 character raw password is incorrect.

**Example: The Domain Entity**
```java
public class User {
    private String id;
    private String email;
    private String passwordHash; // Notice: This is the hash, not the raw password.
    private String fullName;

    // Constructor enforces domain invariants (Internal consistency)
    public User(String email, String passwordHash, String fullName) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("User must have an email.");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("User must have a secure password hash.");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("User must have a full name.");
        }
        
        // We DO NOT check the regex of the passwordHash here, 
        // because a BCrypt hash will never match a standard password regex!

        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
    }

    // Getters, business methods, etc.
}
```

### Summary of the Flow
1. **User sends:** `{"email": "test@test.com", "rawPassword": "Password123"}`
2. **Controller/Command:** Intercepts it. Regex says `"Password123"` is valid.
3. **Use Case:** Takes `"Password123"`, hashes it to `"$2a$10$w8...rY9"`.
4. **Domain Entity:** Receives `"$2a$10$w8...rY9"`. It just checks: *"Is this hash string not null/empty? Yes. I am in a valid state."*


# Password Validation function
- At first, I thought it should be placed inside the Service (RegisterUserUseCaseImpl). However, if there is a need to have a ChangePasswordUseCase/ForgotPasswordUseCase in the future, I would have to duplicate the password validation logic from RegisterUserUseCase. 
- This violates DRY (Don't Repeat Yourself) principle.