package team.spring.trip.courseBoard.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBoard {

	private int courseNum;
	private int userNum;
//	private String placeName;
	private String courseTitle;
	private String courseContents;
	private int courseLike;
	private String createDate;
	private String travelStatus;
	private String purpose;
	
}
