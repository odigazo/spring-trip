package team.spring.trip.tripMain.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import team.spring.trip.tripMain.service.TripMainService;
import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceInfo;

@RestController
@RequestMapping(value="mainPage")
public class TripMainController {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private TripMainService service;
	@Autowired
	private Gson gson;
	
	@GetMapping
	public String searchAllPlaces() {
		
		List<TripPlaceInfo> list = service.searchAllPlaces();
		
		String places = gson.toJson(list);
		
		return places;
	}
	
	@GetMapping(value="/detail")
	public String searchDetail(String placeName) {
		
		TripPlaceDetail detail = service.searchDetail(placeName);
		
		String tripDetail = gson.toJson(detail);
		
		return tripDetail;
	}
	
	
}
