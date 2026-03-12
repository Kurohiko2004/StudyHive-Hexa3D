package com.sep.commonModule.application.port.out;

// is an interface
// serves as an outbound port that abstract data-related operations.

// Inside this file, we list methods that reflect business requirements (save,
// findById, findAll, existsByEmail...). The interface does not specify how to
// save, how to find, where to store it(db, file, cache...).

// And since this is an interface with abstract methods, the implementation of
// this interface (or the class that implements it) will do the detailed parts
// (save to where? could be postgres/mongodb, run what kind of query script?)

import com.sep.commonModule.domain.model.User;
import com.sep.commonModule.domain.model.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface UserRepository {
    User save(User user);
    boolean existsByEmail(String email);


}
