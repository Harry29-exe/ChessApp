package com.kw.DocumentRepository.persistence;


public interface UserRepository {

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    void addUser(User user);

    void deleteUser(int id);

}
