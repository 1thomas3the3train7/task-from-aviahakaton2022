package com.example.tasksher.Repository;

import com.example.tasksher.Model.RefreshToken;

public interface RefreshTokenRepository {
    void save(RefreshToken refreshToken);
    void delete(RefreshToken refreshToken);
}
