package team.spring.trip.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.trip.tripMain.dao.TripMainDao;
import team.spring.trip.tripMain.vo.TripPlaceDetail;

@Service
public class CourseService {
	
	@Autowired
	private TripMainDao dao;
	
	public String transform(String answer) {

		return answer;
	}

	public List<String> getAddr(String addr) {
		
		return dao.getAddr(addr);
	}

	public TripPlaceDetail getlat(String name) {
		
		return dao.getlat(name);
	}

	public TripPlaceDetail getlong(String name) {
		// TODO Auto-generated method stub
		return dao.getlong(name);
	}
}
