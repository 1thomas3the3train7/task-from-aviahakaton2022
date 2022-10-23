package com.example.tasksher.Repository;

import com.example.tasksher.Model.BusType1;
import com.example.tasksher.Model.BusType2;

import java.util.List;

public interface BusType1Repository {
    void save(BusType1 busType1);
    void delete(BusType1 busType1);
    List<BusType1> getNotMoveBus();
}
