package com.example.tasksher.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "point")
@NoArgsConstructor
@ToString
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int point_id;
    private String location_id;
    @OneToOne(mappedBy = "point")
    private BusType1 busType1;
    @OneToOne(mappedBy = "point")
    private BusType2 busType2;

    public Point(int point_id, String location_id) {
        this.point_id = point_id;
        this.location_id = location_id;
    }
}
