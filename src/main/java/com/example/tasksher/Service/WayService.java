package com.example.tasksher.Service;

import com.example.tasksher.Model.Road;
import com.example.tasksher.Model.Task;
import com.example.tasksher.Repository.RoadRepository;
import com.example.tasksher.Repository.TaskRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WayService {
    private final RoadRepository roadRepository;
    private final TaskRepository taskRepository;
    private final Gson gson = new Gson();

    public WayService(RoadRepository roadRepository, TaskRepository taskRepository) {
        this.roadRepository = roadRepository;
        this.taskRepository = taskRepository;
    }

    public void check(int id, List<Integer> way, int distance, int idFinal,String token){
        if(!way.contains(id)){
            List<Road> roads = roadRepository.getRoadsBySourcePoint(id);
            way.add(id);
            for(Road r : roads){
                if(r.getTarget_point_id() == idFinal){
                    way.add(r.getTarget_point_id());
                    int[] wayJ = new int[way.size()];
                    for(int i = 0;i < way.size() - 1;i++){
                        wayJ[i] = way.get(i);
                    }
                    Task checkTask = taskRepository.getTaskByToken(token);
                    if(checkTask != null){
                        distance = distance + r.getDistance();
                        if(checkTask.getDistance() >= distance){
                            System.out.println("if");
                            taskRepository.delete(checkTask);
                            Task task = new Task();
                            task.setDistance(distance);
                            task.setToken(token);
                            /*String wayJson = gson.toJson(wayJ,int[].class);
                            task.setWayJson(wayJson);*/
                            taskRepository.save(task);
                        }
                    } else {
                        Task task = new Task();
                        task.setDistance(distance + r.getDistance());
                        task.setToken(token);
                        /*String wayJson = gson.toJson(wayJ,int[].class);
                        task.setWayJson(wayJson);*/
                        System.out.println(distance + r.getDistance());
                        taskRepository.save(task);
                    }
                } else {
                    id = r.getTarget_point_id();
                    distance = distance + r.getDistance();
                    check(id, way, distance, idFinal,token);
                }
            }
        }
    }
    public void check(int id, List<Integer> way, int distance, int idFinal,String token,Long busId){
        if(!way.contains(id)){
            List<Road> roads = roadRepository.getRoadsBySourcePoint(id);
            way.add(id);
            for(Road r : roads){
                if(r.getTarget_point_id() == idFinal){
                    way.add(r.getTarget_point_id());
                    int[] wayJ = new int[way.size()];
                    for(int i = 0;i < way.size() - 1;i++){
                        wayJ[i] = way.get(i);
                    }
                    Task checkTask = taskRepository.getTaskByToken(token);
                    if(checkTask != null){
                        distance = distance + r.getDistance();
                        if(checkTask.getDistance() >= distance){
                            System.out.println("if");
                            taskRepository.delete(checkTask);
                            Task task = new Task();
                            task.setDistance(distance);
                            task.setToken(token);
                            task.setBusId(busId);
                            /*String wayJson = gson.toJson(wayJ,int[].class);
                            task.setWayJson(wayJson);*/
                            taskRepository.save(task);
                        }
                    } else {
                        Task task = new Task();
                        task.setDistance(distance + r.getDistance());
                        task.setToken(token);
                        task.setBusId(busId);
                        /*String wayJson = gson.toJson(wayJ,int[].class);
                        task.setWayJson(wayJson);*/
                        System.out.println(distance + r.getDistance());
                        taskRepository.save(task);
                    }
                } else {
                    id = r.getTarget_point_id();
                    distance = distance + r.getDistance();
                    check(id, way, distance, idFinal,token,busId);
                }
            }
        }
    }
}
