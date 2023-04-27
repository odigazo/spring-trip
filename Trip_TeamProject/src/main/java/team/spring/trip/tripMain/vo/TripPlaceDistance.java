package team.spring.trip.tripMain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripPlaceDistance {
	
	private String placeName;
	private String thumnailUrl;
	private double distance;
	
}
