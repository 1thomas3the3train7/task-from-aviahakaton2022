package com.example.tasksher.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "role")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_role",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn( name = "user_Id"))
    private Set<User> users;
}
