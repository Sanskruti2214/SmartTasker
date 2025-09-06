package com.example.TaskScheduler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.TaskScheduler.exceptions.TaskDuplicateException;
import com.example.TaskScheduler.models.Client;
import com.example.TaskScheduler.repositories.ClientRepository;

import java.sql.Date;
import java.util.*;

@Service
public class ClientServiceModifyImpl implements ClientServiceModify {
	@Autowired
	private ClientRepository clientRepository;
	
	public void deleteTask(int id){
		if(clientRepository.existsById(id)) {
			clientRepository.deleteById(id);
		}
	}
	
	public List<Client> getAllClients() {
     	List<Client> clients=clientRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
     	return clients;
	}
	
	public Client editTask(int id, String taskType, Date deadlineDate, int estimatedTime) {
		 Client existing = clientRepository.findById(id).get(); // .get() is safe here

		    // Update only if new values are provided
		    if (taskType != null && !taskType.isBlank()) {
		        existing.setTask_type(taskType);
		    }
		    if (deadlineDate != null) {
		        existing.setDeadline_date(deadlineDate);
		    }
		    if (estimatedTime != 0) {
		        existing.setEstimated_time(estimatedTime);
		    }

		    // Save and return updated client
		    return clientRepository.save(existing);
	   
	}
	
	public Client saveClient(Client client) {
		Optional<Client> duplicate=clientRepository.findByTaskName(client.getTask_name()); 
   	    if(duplicate.isPresent()) {
   		   throw new TaskDuplicateException("Duplicate entry! Task already exists.");
   	    }else {
   		   return clientRepository.save(client);
   	    }
	}
	
	

}
