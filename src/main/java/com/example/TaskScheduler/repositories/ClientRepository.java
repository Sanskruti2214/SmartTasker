package com.example.TaskScheduler.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;  

import com.example.TaskScheduler.models.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{
	Optional<Client> findByTaskName(String taskName);
}
