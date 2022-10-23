package com.example.tasksher.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bustype2")
public class BusType2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean move;
    private int speed;
    private int coundPeople = 50;
    private int timeCompleted;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id",referencedColumnName = "id")
    private Point point;
}
