package com.example.tasksher.Repository;

import com.example.tasksher.Model.User;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    User getUserAndRoleAndTokenByEmail(String email);
}
