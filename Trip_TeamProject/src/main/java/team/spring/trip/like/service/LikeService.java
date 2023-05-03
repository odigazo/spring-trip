package team.spring.trip.like.service;

import team.spring.trip.like.vo.Like;

public interface LikeService {

	int addLike(Like like);
	int getLikeCount(Like like);

	int removeLike(Like like);
	boolean check(Like like);

}
