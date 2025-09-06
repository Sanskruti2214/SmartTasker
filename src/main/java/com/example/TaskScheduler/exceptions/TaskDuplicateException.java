package com.example.TaskScheduler.exceptions;

public class TaskDuplicateException extends RuntimeException{
	
	public TaskDuplicateException(String message) {
		super(message);
	}

}
