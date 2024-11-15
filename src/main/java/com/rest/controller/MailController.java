package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.model.Employe;
import com.rest.service.IService;

@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	private IService service;

	@GetMapping("/home")
	public ResponseEntity<String> registEmploye(){
		String msg = "Home Page !";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	 @PostMapping("/save")
	 public ResponseEntity<String> registEmploye(@RequestBody()Employe emp){
		 String msg = service.saveEmploye(emp);
		 return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	 }
	 @PostMapping("/varify/{otp}")
	 public ResponseEntity<String> varifyemail(@PathVariable String otp){
		 String msg= service.varifyOtp(otp);
		 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	 }
 }
