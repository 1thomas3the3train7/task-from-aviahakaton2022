package com.example.tasksher.Repository;

import com.example.tasksher.Model.Road;

import java.util.List;

public interface RoadRepository {
    void save(Road road);
    void delete(Road road);
    List<Road> getRoadsBySourcePoint(final int id);
}
