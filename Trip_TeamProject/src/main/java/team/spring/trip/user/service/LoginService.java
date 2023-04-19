package team.spring.trip.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.trip.user.dao.UserDao;
import team.spring.trip.user.vo.User;

@Service
public class LoginService {
	
	@Autowired
	private UserDao dao;
	
	Logger log = LogManager.getLogger("case3");

	public void userRegistration(User user) {
		int count = dao.insertUser(user);
		if(count==1) {
			log.debug("유저 등록 성공");
		}else {
			log.debug("유저 등록 실패");
		}
		
	}

	public boolean checkEmail(String userEmail) {
		
		boolean canRegister = false;
		
		User user = dao.emailCheck(userEmail);
		
		if(user==null) {
			canRegister=true;
		}
		
		return canRegister;
	}

	

	public boolean checkIsOut(String userEmail) {
		boolean isOut = true;
		User user = dao.isOutCheck(userEmail);
		if(user==null) {
			isOut = false;
		}
		return isOut;
	}

	

	public User getUser(String email) {
		User user = dao.emailCheck(email);
		return user;
	}
	
	
	
}
