package com.example.tasksher.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bustype1")
public class BusType1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean move;
    private int speed;
    private int countPeople = 100;
    private int timeCompleted;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id",referencedColumnName = "id")
    private Point point;
}
