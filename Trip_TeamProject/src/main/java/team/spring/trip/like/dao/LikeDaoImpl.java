package team.spring.trip.like.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.trip.like.vo.Like;

@Repository
public class LikeDaoImpl implements LikeDao {

	@Autowired
	private SqlSession session;

	Logger log = LogManager.getLogger("case3");
	
	//조아요
	@Override
	public int addLike(Like like) {
		log.debug("좋아요 dao");
		return session.insert("like.addLike", like);
	}

	//조아요취소
	@Override
	public int removeLike(Like like) {
		log.debug("좋아요 취소dao");
		return session.delete("like.removeLike", like);
	}
	
	
}
