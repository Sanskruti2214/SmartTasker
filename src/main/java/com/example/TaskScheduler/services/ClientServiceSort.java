package com.example.TaskScheduler.services;
import com.example.TaskScheduler.models.Client;
import java.util.List;

public interface ClientServiceSort {
	
	public List<Client> getDFCFSData();
	
	public List<Client> getSJFData();
	
	public List<Client> getPriorityData();
	

}
