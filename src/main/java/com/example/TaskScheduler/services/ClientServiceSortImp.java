package com.example.TaskScheduler.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.TaskScheduler.models.Client;
import com.example.TaskScheduler.repositories.ClientRepository;
@Service
public class ClientServiceSortImp implements ClientServiceSort{
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> getDFCFSData(){
		List<Client> clients = clientRepository.findAll(); // Fetch all clients
        clients.sort(Comparator.comparing(Client::getDeadline_date));
        return clients;
	}
	
	public List<Client> getSJFData(){
		List<Client> clients = clientRepository.findAll(); // Fetch all clients
	    clients.sort(Comparator.comparingInt(Client::getEstimated_time)); 
        return clients;
	}
	
	public List<Client> getPriorityData(){
		List<Client> clients = clientRepository.findAll(); // Fetch all clients
		clients.sort(Comparator.comparingInt(client -> {
		    String taskType = ((Client) client).getTask_type();
		      switch (taskType) {
		        case "High": return 1;
		        case "Medium": return 2;
		        case "Low": return 3;
		        default: return Integer.MAX_VALUE; // Handle unexpected types
		      }
		    })
		); 
		return clients;
	}
	
	
	
	
	

	
}
