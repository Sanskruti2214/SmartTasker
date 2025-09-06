package com.example.TaskScheduler.controllers;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TaskScheduler.services.ClientServiceModify;
import com.example.TaskScheduler.services.ClientServiceSort;
import com.example.TaskScheduler.models.Client;

@Controller
public class HomeController {
	@Autowired
	ClientServiceSort clientServiceSort;
	@Autowired
	ClientServiceModify clientServiceModify;
	
	@GetMapping({"","/"})
	public String home(Model model) {
		List<Client> clients=clientServiceModify.getAllClients();
		model.addAttribute("clients", clients);
		return "index";
	}
	
	@GetMapping("/AddTask")
	public String taskCreation() {
		return "AddTask";
	}
	
	@GetMapping("/dfcfs")
	public String dfcfs(Model model) {
        model.addAttribute("clients", clientServiceSort.getDFCFSData());
        return "dfcfs";
	}
	
	@GetMapping("/sjf")
	public String sjf(Model model) {
		// Sort by estimated time
        model.addAttribute("clients", clientServiceSort.getSJFData());
        return "sjf";
	}
	
	@GetMapping("/priority")
	public String priority(Model model) {
		// Sort by estimated_time
		model.addAttribute("clients", clientServiceSort.getPriorityData());
		return "priority";
	}
	
	
	
	 
	 

}
