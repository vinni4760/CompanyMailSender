package com.rest.service;

import com.rest.model.Employe;

public interface IService {
	
	public String saveEmploye(Employe emp);
	public String varifyOtp(String otp);

}
