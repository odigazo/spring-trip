package team.spring.trip.like.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.trip.like.dao.LikeDao;
import team.spring.trip.like.vo.Like;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao likeDao;
	
	Logger log = LogManager.getLogger("case3");
	
	//조아요
	@Override
	public int addLike(Like like) {
		log.debug("좋아요 sv");
		int result = likeDao.addLike(like);

		return result;
	}

	//조아요취소
	@Override
	public int removeLike(Like like) {
		log.debug("좋아요취소 sv");
		int result = likeDao.removeLike(like);
		return result;
	}
	
}
