package team.spring.trip.tripMain.dao;

import java.util.List;

import team.spring.trip.tripMain.vo.RecommendInput;
import team.spring.trip.tripMain.vo.SeasonPlaceInfo;
import team.spring.trip.tripMain.vo.TripPlaceDetail;
import team.spring.trip.tripMain.vo.TripPlaceInfo;

public interface TripMainDao {

	List<TripPlaceInfo> searchAllPlaces();

	TripPlaceDetail searchDetail(String placeName);

	List<TripPlaceInfo> searchKeyword(String keyword);

	List<TripPlaceInfo> searchCategory(String season);

	List<SeasonPlaceInfo> selectSeasonPlace(String season);

	List<TripPlaceDetail> recommend(RecommendInput recommendInput);

	void insertRecommendLog(String placeName, int userNum, String purpose);

	List<Integer> recommendPlaceLike(int userNum);

	TripPlaceInfo selectOthersRecommend(Integer userNum);

	List<TripPlaceInfo> recentCourse();

}
