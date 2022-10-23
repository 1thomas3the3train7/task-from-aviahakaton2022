package com.example.tasksher.Repository;

import com.example.tasksher.Model.BusType2;

import java.util.List;

public interface BusType2Repository {
    void save(BusType2 busType2);
    void delete(BusType2 busType2);
    List<BusType2> getNotMoveBus();
}
