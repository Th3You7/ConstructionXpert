package app.com.constructionxpert.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class AllocatedResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
