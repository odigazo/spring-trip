package team.spring.trip.tripMain.service;

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
import team.spring.trip.tripMain.vo.RecommendInput;
import team.spring.trip.tripMain.vo.SeasonPlaceInfo;
import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceDistance;
import team.spring.trip.tripMain.vo.TripPlaceInfo;

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
	
	@Transactional
	public TripPlaceDetail recommendCal(RecommendInput recommendInput) {
		LocalDate date = LocalDate.parse(recommendInput.getStartDate());
		int month = date.getMonthValue();
		
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
		recommendInput.setSeason(season);
		
		switch (recommendInput.getPurpose()) {
			case "자연 속 여행":
				recommendInput.setKeyword1("계곡");
				recommendInput.setKeyword2("바다");
				recommendInput.setKeyword3("산");
				break;
			case "가족 여행":
				recommendInput.setKeyword1("테마");
				recommendInput.setKeyword2("놀이");
				recommendInput.setKeyword3("공원");			
				break;
			case "역사와 문화 여행":
				recommendInput.setKeyword1("역사");
				recommendInput.setKeyword2("문화");
				recommendInput.setKeyword3("박물관");		
				break;
			case "축제와 이벤트 여행":
				recommendInput.setKeyword1("축제");
				recommendInput.setKeyword2("행사");
				recommendInput.setKeyword3("박람회");				
				break;
			default:
				break;
		}
		
		List<TripPlaceDetail> list = dao.recommend(recommendInput);
		
		List<TripPlaceDetail> recommendList = recommendOnePlace(list, recommendInput);
		
		if (recommendInput.getDistance() != "상관없음") {
			for (TripPlaceDetail place : recommendList) {
				double distanceKiloMeter = distance(recommendInput.getLatitude(), recommendInput.getLongitude(), place.getLatitude(), place.getLongitude(), "kilometer");
				place.setDistance(distanceKiloMeter);
			}
			
			Collections.sort(recommendList, Comparator.comparingDouble(TripPlaceDetail::getDistance));
		}
		dao.insertRecommendLog(recommendList.get(0).getPlaceName(), recommendInput.getUserNum(), recommendInput.getPurpose());
		
		return recommendList.get(0);
	}

	private List<TripPlaceDetail> recommendOnePlace(List<TripPlaceDetail> list, RecommendInput recommendInput) {
		
		List<TripPlaceDetail> list1 = new ArrayList<>();
		List<TripPlaceDetail> list2 = new ArrayList<>();
		List<TripPlaceDetail> list3 = new ArrayList<>();
		
 		for (TripPlaceDetail detail : list) {
			if (detail.getPlaceContents().contains(recommendInput.getKeyword1())) {
				if (detail.getPlaceContents().contains(recommendInput.getKeyword1())) {
					if (detail.getPlaceContents().contains(recommendInput.getKeyword1())) {
						list3.add(detail);
					} else {
						list2.add(detail);						
					}
				}else {
					list1.add(detail);
				}
			}
		}
 		
 		List<TripPlaceDetail> returnList = null;
 		
 		if (list3.size() == 0) {
 			if (list2.size() == 0) {
 				returnList = list1; 				
 			} else { 				
 				returnList = list2;
 			}
 		}else {
 			returnList = list3;
 		}
		return returnList;
	}

	public List<TripPlaceInfo> recommendPlaceLike(int userNum) {
		
		List<Integer> numList = dao.recommendPlaceLike(userNum);
		
		List<TripPlaceInfo> likeRecommendList = new ArrayList<>();
		for (Integer num : numList) {
			TripPlaceInfo info = dao.selectOthersRecommend(num);
			if (info != null) {
				likeRecommendList.add(info);
			}
		}
		
		return likeRecommendList;
	}

	public List<TripPlaceInfo> recentCourse() {
		
		List<TripPlaceInfo> list = dao.recentCourse();
		
		return list;
	}
	


	
}
