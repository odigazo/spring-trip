package team.spring.trip.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.spring.trip.user.dao.UserDao;
import team.spring.trip.user.vo.User;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserDao dao;
	
	Logger log = LogManager.getLogger("case3");


	public User checkEmail(String userEmail) {
		
		boolean canRegister = false;
		
		User user = dao.emailCheck(userEmail);
		
		return user;
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

	public User loginCheck(User user) {
		User userinfo = dao.loginCheck(user);
		return userinfo;
	}
	
	
	
}
