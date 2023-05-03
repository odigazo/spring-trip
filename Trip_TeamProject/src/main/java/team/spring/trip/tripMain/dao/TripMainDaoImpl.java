package team.spring.trip.tripMain.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.trip.tripMain.vo.RecommendInput;
import team.spring.trip.tripMain.vo.SeasonPlaceInfo;
import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceInfo;

@Repository
public class TripMainDaoImpl implements TripMainDao{

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;

	@Override
	public List<TripPlaceInfo> searchAllPlaces() {
		
		List<TripPlaceInfo> list = session.selectList("tripMain.searchAllPlaces");
		
		return list;
	}

	@Override
	public TripPlaceDetail searchDetail(String placeName) {
		
		TripPlaceDetail detail = session.selectOne("tripMain.searchDetail", placeName);
		
		return detail;
	}

	@Override
	public List<TripPlaceInfo> searchKeyword(String keyword) {
		
		List<TripPlaceInfo> list = session.selectList("tripMain.searchKeyword", keyword);
		
		return list;
	}

	@Override
	public List<TripPlaceInfo> searchCategory(String season) {
		
		List<TripPlaceInfo> list = session.selectList("tripMain.searchCategory", season);	
		
		return list;
	}

	@Override
	public List<SeasonPlaceInfo> selectSeasonPlace(String season) {
		
		List<SeasonPlaceInfo> list = session.selectList("tripMain.selectSeasonPlace", season);
		
		return list;
	}

	@Override
	public List<TripPlaceDetail> recommend(RecommendInput recommendInput) {
		
		List<TripPlaceDetail> list = session.selectList("tripMain.recommendPlace", recommendInput);
		
		return list;
	}

	@Override
	public void insertRecommendLog(String placeName, int userNum, String purpose) {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("purpose", purpose);
		map.put("placeName", placeName);
		map.put("userNum", userNum);
		session.insert("tripMain.insertRecommendPlace", map);
		
	}

	@Override
	public List<Integer> recommendPlaceLike(int userNum) {
		
		List<Integer> list = session.selectList("tripMain.selectLikeClickUser", userNum);
		
		return list;
	}

	@Override
	public TripPlaceInfo selectOthersRecommend(Integer userNum) {
		
		TripPlaceInfo info = session.selectOne("tripMain.otherRecommendPlace", userNum);
		
		return info;
	}

	@Override
	public List<TripPlaceInfo> recentCourse() {
		
		List<TripPlaceInfo> list = session.selectList("tripMain.recentCourse");
		
		
		return list;
	}
	
	
}
