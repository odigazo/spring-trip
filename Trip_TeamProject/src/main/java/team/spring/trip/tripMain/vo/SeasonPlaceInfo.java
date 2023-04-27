package team.spring.trip.tripMain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonPlaceInfo {
	
	private String placeName;
	private String thumnailUrl;
	private double longitude;
	private double latitude;
	
}
