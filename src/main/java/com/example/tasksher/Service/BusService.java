package com.example.tasksher.Service;

import com.example.tasksher.Repository.BusType1Repository;
import com.example.tasksher.Repository.BusType2Repository;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    private final BusType1Repository busType1Repository;
    private final BusType2Repository busType2Repository;

    public BusService(BusType1Repository busType1Repository, BusType2Repository busType2Repository) {
        this.busType1Repository = busType1Repository;
        this.busType2Repository = busType2Repository;
    }


}
