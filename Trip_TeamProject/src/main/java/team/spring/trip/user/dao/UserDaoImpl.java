package team.spring.trip.user.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.trip.user.vo.User;





@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession session;
	
	Logger log = LogManager.getLogger("case3");
	
	@Override
	public User emailCheck(String userEmail) {
		
		User user = session.selectOne("myUser.checkEmail",userEmail);
		
		return user;
	}

	@Override
	public int insertUser(User user) {

		int count = session.insert("myUser.insertUser", user);
		
		if(count==1) {
			log.debug("유저 등록 커밋 성공");
		}else {
			log.debug("유저 등록 실패, 롤백");
		}
		return count;
	}

	@Override
	public int getUserNum(String userEmail) {
		
		int userNum = session.selectOne("myUser.getUserNum", userEmail);
		return userNum;
	}


	@Override
	public int deleteUser(String userEmail) {
		Date outDate=java.sql.Date.valueOf(java.time.LocalDate.now());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userEmail",userEmail);
		map.put("outDate",outDate); // 현재 탈퇴 시간을 받아 옴
		int count = session.update("myUser.deleteUser", map);
		
		return count;
	}

	


	@Override
	public User isOutCheck(String userEmail) {
		User user = session.selectOne("myUser.checkIsOut",userEmail);
		return user;
	}

	
	
	
}
