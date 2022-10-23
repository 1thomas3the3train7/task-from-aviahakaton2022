package com.example.tasksher.Service;

import com.example.tasksher.Dto.JwtDTO;
import com.example.tasksher.Dto.UserDTO;
import com.example.tasksher.Exception.UserNotFoundException;
import com.example.tasksher.Model.RefreshToken;
import com.example.tasksher.Model.User;
import com.example.tasksher.Repository.RefreshTokenRepository;
import com.example.tasksher.Repository.UserRepository;
import com.example.tasksher.Utils.DtoUtils;
import com.example.tasksher.Utils.JwtUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JwtUtils jwtUtils;
    private final DtoUtils dtoUtils;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public UserService(JwtUtils jwtUtils, DtoUtils dtoUtils, UserRepository userRepository,
                       RefreshTokenRepository refreshTokenRepository) {
        this.jwtUtils = jwtUtils;
        this.dtoUtils = dtoUtils;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public JwtDTO login(final UserDTO userDTO){
        final User user = userRepository.getUserAndRoleAndTokenByEmail(userDTO.getEmail());
        if(user.getPassword().equals(userDTO.getPassword())){
            final UserDTO userDTO1 = dtoUtils.EntityToUserDTO(user);
            return new JwtDTO(jwtUtils.generateAccessToken(userDTO1), jwtUtils.generateRefreshToken(userDTO1));
        }
        throw new UserNotFoundException("incorrect email or password");
    }

}
