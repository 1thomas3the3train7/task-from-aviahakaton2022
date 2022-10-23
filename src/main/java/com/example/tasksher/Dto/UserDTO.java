package com.example.tasksher.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;
    private RoleDTO[] roles;
    private boolean enabled;
    private boolean banned;
    private String firstName;

    public UserDTO(String email, RoleDTO[] roles) {
        this.email = email;
        this.roles = roles;
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
