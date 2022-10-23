package com.example.tasksher.Repository;

import com.example.tasksher.Model.Point;

public interface PointRepository {
    void save(Point point);
    void delete(Point point);
    Point getPointByLocationId(String location_id);
}
