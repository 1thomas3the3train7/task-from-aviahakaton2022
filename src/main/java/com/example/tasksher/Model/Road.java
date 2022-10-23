package com.example.tasksher.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "road")
@NoArgsConstructor
@ToString
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int road_id;
    private int source_point_id;
    private int target_point_id;
    private int distance;

    public Road(int road_id, int source_point_id, int target_point_id, int distance) {
        this.road_id = road_id;
        this.source_point_id = source_point_id;
        this.target_point_id = target_point_id;
        this.distance = distance;
    }
}
