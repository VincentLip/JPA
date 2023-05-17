package org.example.model;


import javax.persistence.*;

@Entity
@Table(name= "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long id;

    @OneToOne(mappedBy = "task",cascade = CascadeType.ALL,orphanRemoval = true)
    private Info info;
    private String title;
    private boolean completed;
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
