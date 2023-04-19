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

import team.spring.trip.user.service.LoginService;
import team.spring.trip.user.vo.User;





@RestController
@RequestMapping(value="login",produces="application/json")
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	
	
	
	Logger log = LogManager.getLogger("case3");
	
	
	@GetMapping
	public  Map<String,String> Login(
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password) throws JsonProcessingException {
		log.debug("email - " + email);
		log.debug("password - " + password);
		
		User user = new User();
		user.setUserEmail(email);
		user.setUserPW(password);
		
		//
		
	    
	    ObjectMapper mapper = new ObjectMapper();
	    String userInfo = mapper.writeValueAsString(user);
	    Map<String,String> map = new HashMap<String, String>();
	    map.put("userInfo",userInfo);
	    
	    
		return map;
		
	}
	
	
}
