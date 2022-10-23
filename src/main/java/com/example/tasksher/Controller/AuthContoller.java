package com.example.tasksher.Controller;

import com.example.tasksher.Dto.JwtDTO;
import com.example.tasksher.Dto.UserDTO;
import com.example.tasksher.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthContoller {
    private final UserService userService;

    public AuthContoller(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/login")
    public ResponseEntity<JwtDTO> login(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.login(userDTO), HttpStatus.OK);
    }
    @PostMapping(value = "/reloadtoken")
    public ResponseEntity<JwtDTO> reloadToken(@RequestBody JwtDTO jwtDTO){
        return null;
    }
}
