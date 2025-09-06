package com.example.TaskScheduler.controllers;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TaskScheduler.exceptions.TaskDuplicateException;
import com.example.TaskScheduler.models.Client;
import com.example.TaskScheduler.services.ClientServiceModify;

@Controller
public class ClientsController {
	@Autowired
	ClientServiceModify clientServiceModify;
	
	@PostMapping("/delete")
	 public String deleteTask(@RequestParam("id") int id, Model model) {
		    clientServiceModify.deleteTask(id);
		    var clients=clientServiceModify.getAllClients();
     		model.addAttribute("clients", clients);
	        return "index";
	  }
	
	@GetMapping("/edit")
	 public String editTask(@RequestParam("id") int id,Model model) {
		model.addAttribute("id", id);
		return "edit";
	 }
	
	@PostMapping("/save")
	public String saveEditTask(@RequestParam("id") int id,@RequestParam(value="task_type", required = false) String taskType,
			@RequestParam(value="deadline_date", required = false)Date deadline_datedeadlineDate,
			@RequestParam(value="estimated_time", required = false)int estimated_time,Model model) {
		clientServiceModify.editTask(id,taskType,deadline_datedeadlineDate,estimated_time);
		var clients=clientServiceModify.getAllClients();
 		model.addAttribute("clients", clients);
	    return "index";
	}
	
	@PostMapping("/submit")
	public String addTask(
			@RequestParam("task_name")String task_name,
			@RequestParam("task_type")String task_type,
			@RequestParam("entry_date")Date entry_date,
			@RequestParam("deadline_date")Date deadline_date,
			@RequestParam("estimated_time")int estimated_time,RedirectAttributes redirectAttributes,Model model) {
		Client client=new Client();
		client.setTask_name(task_name);
		client.setTask_type(task_type);
		client.setEntry_date(entry_date);
		client.setDeadline_date(deadline_date);
		client.setEstimated_time(estimated_time);
		 try {
		        clientServiceModify.saveClient(client);
		        redirectAttributes.addFlashAttribute("successMessage", "Task Added Successfully!!");
		    } catch (TaskDuplicateException e) {
		        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		    }
		    return "redirect:/AddTask"; 
	}
}
