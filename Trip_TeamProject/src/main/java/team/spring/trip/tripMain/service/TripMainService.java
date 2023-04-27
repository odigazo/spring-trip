package team.spring.trip.tripMain.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import team.spring.trip.tripMain.dao.TripMainDao;
import team.spring.trip.tripMain.vo.SeasonPlaceInfo;
import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceDistance;
import team.spring.trip.tripMain.vo.TripPlaceInfo;
import team.spring.trip.user.vo.User;

@Service
public class TripMainService {
	
	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private TripMainDao dao;
	
	@Autowired
	private Gson gson;

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
	
	@Transactional
	public List<TripPlaceDistance> recommendPlace(double longitude, double latitude) {
		
		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();
		
		String season = "";
		if (month < 6 && month > 2) {
			season = "spring";
		}else if (month < 9 && month > 5) {
			season = "summer";
		}else if (month < 12 && month > 8) {
			season = "autumn";
		}else {
			season = "winter";
		}
		
		List<SeasonPlaceInfo> list = dao.selectSeasonPlace(season);
		List<TripPlaceDistance> distanceList = new ArrayList<TripPlaceDistance>();
		List<TripPlaceDistance> resultList = new ArrayList<TripPlaceDistance>();
		
		for (SeasonPlaceInfo place : list) {
			double distanceKiloMeter = distance(latitude, longitude, place.getLatitude(), place.getLongitude(), "kilometer");
			distanceList.add(new TripPlaceDistance(place.getPlaceName(), place.getThumnailUrl(), distanceKiloMeter));
		}
		
		Collections.sort(distanceList, Comparator.comparingDouble(TripPlaceDistance::getDistance));
		
		for (int i = 0; i < 10; i++) {
			resultList.add(distanceList.get(i));
		}
		
		return resultList;
	}
	
	
	private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
		if (unit == "kilometer") {
			dist = dist * 1.609344;
		} else if(unit == "meter"){
			dist = dist * 1609.344;
		} 

		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	


	
}
