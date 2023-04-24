package team.spring.trip.tripMain.dao;

import java.util.List;

import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceInfo;

public interface TripMainDao {

	List<TripPlaceInfo> searchAllPlaces();

	TripPlaceDetail searchDetail(String placeName);
	
	

}
