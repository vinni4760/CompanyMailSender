package com.rest.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rest.model.Employe;
import com.rest.repository.IRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Serviceimpl implements IService{
	@Autowired
	private IRepository repo;
	@Autowired
	private JavaMailSender sendmail;
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String saveEmploye(Employe emp) {
		// TODO Auto-generated method stub
		String otp = sendOtp();
		emp.setOtp(otp);
		emp.setStatus("Not Varified");
		repo.save(emp);
		MimeMessage message = sendmail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(sender);
			helper.setTo(emp.getEmail());
			helper.setSubject("Confirm Email");
			helper.setText("Your One time Password:"+otp);
			sendmail.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Employe data is saved with id :"+emp.getId();
		
	}

	private String sendOtp() {
		// TODO Auto-generated method stub
		int length =4;
		StringBuilder otp = new StringBuilder();
		for(int i=0;i<length;i++) {
			otp.append(ThreadLocalRandom.current().nextInt(0,10));
		}
		return otp.toString();
	}

	@Override
	public String varifyOtp(String otp) {
		// TODO Auto-generated method stub
		Employe emp = repo.findByOtp(otp);
		if(emp!=null) {
			emp.setStatus("Varified");
			repo.save(emp);
			return "Varied Successfully";
		}
		return "Invalid Otp";
	}

}
