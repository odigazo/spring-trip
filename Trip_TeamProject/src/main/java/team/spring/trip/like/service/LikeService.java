package team.spring.trip.like.service;

import team.spring.trip.like.vo.Like;

public interface LikeService {

	int addLike(Like like);

	int removeLike(Like like);

}
