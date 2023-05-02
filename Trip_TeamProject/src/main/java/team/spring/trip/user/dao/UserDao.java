package team.spring.trip.user.dao;

import team.spring.trip.user.vo.User;



public interface UserDao {

	User emailCheck(String userEmail);

	int insertUser(User user);

	int getUserNum(String string);

	int deleteUser(String userEmail);

	User isOutCheck(String userEmail);

	User loginCheck(User user);

	User nicknameCheck(String userNickname);

	int updateUser(User user);

	
	
}
