package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "category_task",joinColumns = @JoinColumn(name="category_id"),
    inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> taskList = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }


    public void addTask(Task task){
        taskList.add(task);
        task.getCategoryList().add(this);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
