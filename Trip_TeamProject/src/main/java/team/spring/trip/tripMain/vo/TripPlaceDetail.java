package team.spring.trip.tripMain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripPlaceDetail {

	private String placeName;
	private String placeAddress;
	private double latitude;
	private double longitude;
	private String placeContents;
	private String imageUrl;
	
}
