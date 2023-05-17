package org.example.model;

import javax.persistence.*;

@Entity
@Table(name ="info")
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info")
    private Long id;
    @Column(name = "priorité")
    private int priority;
    @Column(name = "date d'échéance")
    private String end_date;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name= "id_info")
    private Task task;
    public Info() {
    }

    public String getDescription() {
        return description;
    }

    public Info(int priority, String end_date, String description) {
        this.priority = priority;
        this.end_date = end_date;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", priority=" + priority +
                ", end_date='" + end_date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
