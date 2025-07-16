package com.example.TaskScheduler.controllers;
import java.util.Comparator;

import java.util.List;
import java.util.Optional;
import java.sql.Date;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TaskScheduler.models.Client;
import com.example.TaskScheduler.repositories.ClientRepository;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
public class HomeController {
	@Autowired
	ClientRepository clientRepo;
	@GetMapping({"","/"})
	public String home(Model model) {
		var clients=clientRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
		model.addAttribute("clients", clients);
		return "index";
	}
	@GetMapping("/AddTask")
	public String taskCreation() {
		return "AddTask";
	}
	@PostMapping("submit")
	public String addTask(
			@RequestParam("task_name")String task_name,
			@RequestParam("task_type")String task_type,
			@RequestParam("entry_date")Date entry_date,
			@RequestParam("deadline_date")Date deadline_date,
			@RequestParam("estimated_time")int estimated_time) {
		Client client=new Client();
		client.setTask_name(task_name);
		client.setTask_type(task_type);
		client.setEntry_date(entry_date);
		client.setDeadline_date(deadline_date);
		client.setEstimated_time(estimated_time);
		clientRepo.save(client);
		return "AddTask";
	}
	@GetMapping("/fcfs")
	public String fcfs(Model model) {
		List<Client> clients = clientRepo.findAll(); // Fetch all clients
        clients.sort(Comparator.comparing(Client::getDeadline_date)); // Sort by entry date
        model.addAttribute("clients", clients);
        return "fcfs";
	}
	@GetMapping("/sjf")
	public String sjf(Model model) {
		List<Client> clients = clientRepo.findAll(); // Fetch all clients
        clients.sort(Comparator.comparingInt(Client::getEstimated_time)); // Sort by estimated time
        model.addAttribute("clients", clients);
        return "sjf";
	}
	@GetMapping("/priority")
	public String priority(Model model) {
		List<Client> clients = clientRepo.findAll(); // Fetch all clients
		clients.sort(Comparator.comparingInt(client -> {
		    String taskType = ((Client) client).getTask_type();
		      switch (taskType) {
		        case "High": return 1;
		        case "Medium": return 2;
		        case "Low": return 3;
		        default: return Integer.MAX_VALUE; // Handle unexpected types
		      }
		    })
		); // Sort by estimated_time
		model.addAttribute("clients", clients);
		return "priority";
	}
	@PostMapping("/edit")
	public String editTask(@RequestParam("id") int id, Model model) {
	    Optional<Client> client = clientRepo.findById(id);
	    if (client.isPresent()) {
	        model.addAttribute("client", client.get());
	        return "edit"; // Replace with the name of your edit page
	    }
	    return "redirect:/tasks"; // Redirect if client not found
	}
	
	 @PostMapping("/delete")
	    public String deleteTask(@RequestParam("id") int id, Model model) {
	        // Check if the task exists
	        if (clientRepo.existsById(id)) {
	            clientRepo.deleteById(id); // Delete task by ID
	           
	        } else {
	            model.addAttribute("error", "Task not found");
	        }
	        model.addAttribute("clients", clientRepo.findAll());
	        // Redirect to the task list view or refresh the current page
	        return "index";
	    }
	 
	 @PostMapping("/save")
	 public String saveClient(@ModelAttribute Client client) {
	     if (client.getId() != 0) {
	         // Update existing task
	         Optional<Client> existingClient = clientRepo.findById(client.getId());
	         if (existingClient.isPresent()) {
	             Client updatedClient = existingClient.get();
	             updatedClient.setTask_name(client.getTask_name());
	             updatedClient.setTask_type(client.getTask_type());
	             updatedClient.setEntry_date(client.getEntry_date());
	             updatedClient.setDeadline_date(client.getDeadline_date());
	             updatedClient.setEstimated_time(client.getEstimated_time());
	             clientRepo.save(updatedClient);
	         }
	     } else {
	         // Create new task
	         clientRepo.save(client);
	     }
	     return "index";
	 }

}
