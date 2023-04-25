package team.spring.trip.tripMain.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import team.spring.trip.tripMain.dao.TripMainDao;
import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceInfo;

@Service
public class TripMainService {
	
	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private TripMainDao dao;

	public List<TripPlaceInfo> searchAllPlaces() {
		
		List<TripPlaceInfo> list = dao.searchAllPlaces();
		
		return list;
	}

	public TripPlaceDetail searchDetail(String placeName) {
		
		TripPlaceDetail detail = dao.searchDetail(placeName);
		
		return detail;
	}

	public List<TripPlaceInfo> searchKeyword(String keyword) {
		
		List<TripPlaceInfo> list = dao.searchKeyword(keyword);
		
		return list;
	}

	public List<TripPlaceInfo> searchCategory(String season) {
		
		List<TripPlaceInfo> list = dao.searchCategory(season);
		
		return list;
	}
	


	
}
