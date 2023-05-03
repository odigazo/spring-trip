package team.spring.trip.tripMain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendInput {

	private String startDate;
	private String endDate;
	private String purpose;
	private String distance;
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String season;
	private double longitude;
	private double latitude;
	private int userNum;
}
