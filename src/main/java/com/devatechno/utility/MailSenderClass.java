package com.devatechno.utility;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSenderClass {
	@Autowired
	private JavaMailSender mailSender;
	public void sendMail(String subject,String body,String to,File file) {
		try {
			MimeMessage mimemsg=mailSender.createMimeMessage();
			MimeMessageHelper msgHelper=new MimeMessageHelper(mimemsg, true);
			msgHelper.setSubject(subject);
			msgHelper.setText(body,true);
			msgHelper.setTo(to);
			msgHelper.addAttachment("Insurance plans", file);
			mailSender.send(mimemsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
