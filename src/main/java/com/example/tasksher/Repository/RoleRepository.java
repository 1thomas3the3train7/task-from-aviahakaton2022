package com.example.tasksher.Repository;

import com.example.tasksher.Model.Role;

public interface RoleRepository {
    void save(Role role);
    void delete(Role role);
}
