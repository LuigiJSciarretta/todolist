package br.com.luigisciarretta.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByName(String name);
    UserModel findByUsername(String username);
}
