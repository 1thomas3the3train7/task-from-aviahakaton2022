package com.example.tasksher.Utils;

import com.example.tasksher.Dto.RoleDTO;
import com.example.tasksher.Dto.UserDTO;
import com.example.tasksher.Model.Role;
import com.example.tasksher.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DtoUtils {
    public UserDTO EntityToUserDTO(final User user){
        return new UserDTO(user.getEmail(),EntitiesToRolesDTO(user.getRoles()));
    }
    public RoleDTO[] EntitiesToRolesDTO(Set<Role> roles){
        final List<Role> roleList = roles.stream().toList();
        final int c = roles.size();
        RoleDTO[] roles1 = new RoleDTO[c];
        for(int i = 0;i < c - 1;i++){
            roles1[i] = new RoleDTO(roleList.get(i).getName());
        }
        return roles1;
    }
}
