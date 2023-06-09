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
public class SignUpService {
	
	@Autowired
	private UserDao dao;
	
	Logger log = LogManager.getLogger("case3");

	public int userRegistration(User user) {
		int count = dao.insertUser(user);
		if(count==1) {
			log.debug("유저 등록 성공");
		}else {
			log.debug("유저 등록 실패");
		}
		return count;
	}

	public boolean checkEmail(String userEmail) {
		
		boolean canRegister = false;
		
		User user = dao.emailCheck(userEmail);
		
		if(user==null) {
			canRegister=true;
		}
		
		return canRegister;
	}
	
	public boolean checkNickname(String userNickname) {
		
		boolean canRegister = false;
		
		User user = dao.nicknameCheck(userNickname);
		
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

	public int updateinfo(User user) {
		
		return dao.updateUser(user);
	}

	public int deleteUser(User user) {
		
		return dao.deleteUser(user.getUserEmail());
	}

	
}
