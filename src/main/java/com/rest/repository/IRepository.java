package com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.model.Employe;

public interface IRepository extends JpaRepository<Employe, Integer> {

	 @Query
	 public Employe findByOtp(String otp);
}
