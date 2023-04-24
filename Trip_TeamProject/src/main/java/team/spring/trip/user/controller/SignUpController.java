package team.spring.trip.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.trip.user.service.SignUpService;
import team.spring.trip.user.vo.User;

@RestController
@RequestMapping(value = "signup")
public class SignUpController {
	@Autowired
	private SignUpService signupservice;
	
	Logger log = LogManager.getLogger("case3");
	
	
	@GetMapping
	public  String SignUp(
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="nickname", required=false) String nickname,
			@RequestParam(value="address", required=false) String address,
			@RequestParam(value="age", required=false) int age) throws JsonProcessingException {
		log.debug("email - " + email);
		log.debug("password - " + password);
		log.debug("nickname - " + nickname);
		log.debug("address - " + address);
		log.debug("age - " + age);
		
		User user = new User();
		user.setUserEmail(email);
		user.setUserPW(password);
		user.setUserNickname(nickname);
		user.setUserAddr(address);
		user.setUserAge(age);
		
		
		
	    int isSucess = signupservice.userRegistration(user);
		
		if(isSucess==1) {
			return "등록성공";
		}else {
			return "등록실패";
		}
		
	}
	
	@GetMapping("checkemail")
	public  boolean checkemail(
			@RequestParam(value="email", required=false) String email
			) throws JsonProcessingException {
		log.debug("email - " + email);
		
		return signupservice.checkEmail(email);
		
	}
	
	@GetMapping("checknickname")
	public  boolean checknickname(
			@RequestParam(value="nickname", required=false) String nickname
			) throws JsonProcessingException {
		log.debug("nickname - " + nickname);
		
		return signupservice.checkNickname(nickname);
		
	}
}
