package team.spring.trip.like.dao;

import team.spring.trip.like.vo.Like;

public interface LikeDao {

	int addLike(Like like);

	int removeLike(Like like);

}
