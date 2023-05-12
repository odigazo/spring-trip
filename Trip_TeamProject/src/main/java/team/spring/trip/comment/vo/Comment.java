package team.spring.trip.comment.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	private int commentNum;
	private int courseNum;
	private int userNum;
	private String contents;
	private String contentsDate;
	private String isDelete;
	private String deleteDate;
}
