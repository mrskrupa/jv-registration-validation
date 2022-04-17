package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

import java.util.Objects;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 18;
    private static final int MIN_LENGTH = 6;

    @Override
    public User register(User user) {
        if (user == null) {
            throw new NullPointerException("User can't be register");
        }
        if (user.getLogin() == null) {
            throw new NullPointerException("Invalid login");
        }
        if (user.getPassword() == null) {
            throw new NullPointerException("Invalid password");
        }
        if (user.getAge() == null) {
            throw new NullPointerException("Invalid age");
        }
        checkingAge(user);
        checkingPasswordLength(user);
        checkingInStorage(user);
        Storage.people.add(user);
        return user;
    }

    private void checkingInStorage(User user) {
        for (User users : Storage.people) {
            if (Objects.equals(user.getLogin(), users.getLogin())) {
                throw new RuntimeException("This user was created");
            }
        }
    }

    private void checkingAge(User user) {
        if (user.getAge() < MIN_AGE) {
            throw new RuntimeException("This user so younger");
        }
    }

    private void checkingPasswordLength(User user) {
        if (user.getPassword().length() < MIN_LENGTH) {
            throw new RuntimeException("Not valid password");
        }
    }
}
