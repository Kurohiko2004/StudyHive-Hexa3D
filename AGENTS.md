# Smart Education Platform (SEP)

Framework: Spring Boot 3
Java: 21+
Architecture: Hexagonal Architecture (Ports and Adapters) +DDD
Build: Multi-module Maven 

---

## DOMAIN LAYER RULES
- KHÔNG BAO GIỜ dùng @Entity, @Table, @Autowired, @Component trong `domain/model`.
- Domain chỉ chứa Java thuần (POJO).
- Không phụ thuộc Spring Framework.

---

## APPLICATION LAYER RULES
- Use Case phải là interface nằm ở `application/port/in`.
- Implementation nằm ở `application/service`.
- Service gọi `Port Out`.
- Mọi input phải đi qua `Command`.

Ví dụ:
CreateClassroomCommand

---

## ADAPTER LAYER RULES

### Web Adapter
- nằm ở `adapter/in/web`
- Controller phải trả về:

BaseResponse<T>

### Persistence Adapter
- nằm ở `adapter/out/persistence`
- sử dụng:
    - JpaRepository
    - Mapper

Mapping:
JpaEntity <-> Domain Model

---

## MODULE STRUCTURE

common
- exception
- base response
- config chung

class-management
identity
...

Mỗi module:
- có @SpringBootApplication riêng
- có application.yml riêng
- chạy độc lập

common là module duy nhất được share.