package com.pausa.pranzo.core.user;

public interface UserRepository {

    /**
     * @param username username
     * @param password password
     * @return user if exists
     * @throws UserNotFoundException in case no user match usr/pwd combination
     */
    User find(String username, String password);

    final class UserNotFoundException extends RuntimeException {

        public static final String FAKE_OBFUSCATED_PASSWORD = "/************";

        public UserNotFoundException(String username) {
            super(username + FAKE_OBFUSCATED_PASSWORD);
        }
    }

}
