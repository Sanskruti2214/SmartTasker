package com.example.TaskScheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TaskScheduler.repositories.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	@Autowired
	ClientRepository clientRepo;
	@GetMapping({"","/"})
	public String getClients(Model model) {
		var clients=clientRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
		model.addAttribute("clients", clients);
		return "clients/index";
	}
	
}
