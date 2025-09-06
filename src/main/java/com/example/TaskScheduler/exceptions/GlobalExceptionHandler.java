package com.example.TaskScheduler.exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TaskDuplicateException.class)
	public ResponseEntity<String> handleTaskDuplicate(TaskDuplicateException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

}
