package team.spring.trip.user.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String userEmail;
	private String userNickname;
	private int userNum;
	private String userPW;
	private String userAddr;
	private int userAge;
	private Date inDate;
	private String isOut;
	private String outDate;
	
}
