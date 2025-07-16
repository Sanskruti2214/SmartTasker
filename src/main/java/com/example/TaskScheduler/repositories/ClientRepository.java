package com.example.TaskScheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaskScheduler.models.Client;


public interface ClientRepository extends JpaRepository<Client,Integer>{
	
}
