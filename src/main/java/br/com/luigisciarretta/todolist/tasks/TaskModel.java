package br.com.luigisciarretta.todolist.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID" )
    public UUID id;

    public String description;
    @Column(length = 50)
    public String title;
    public String priority;
    public LocalDateTime startAt;
    public LocalDateTime endAt;
    public UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public UUID getIdUser() {
        return idUser;
    }
}
