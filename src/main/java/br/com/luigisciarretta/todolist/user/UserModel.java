package br.com.luigisciarretta.todolist.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity(name = "tb_users")
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    public String name;
    public String username;
    public String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

}

