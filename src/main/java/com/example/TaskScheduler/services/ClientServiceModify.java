package com.example.TaskScheduler.services;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.TaskScheduler.models.Client;

public interface ClientServiceModify {
	
	public void deleteTask(int id);
	
	public Client editTask(int id,String taskType,Date deadline_datedeadlineDate,int estimated_time );
	
	public Client saveClient(Client client);
	
	public List<Client> getAllClients();
	
}
