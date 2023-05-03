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
		int test = like.getUserNum();
		int test2 = like.getCourseNum();
		System.out.println(test+"+"+test2);
		return session.insert("Like.addLike", like);
	}

	//조아요 카운트
	@Override
	public int getLikeCount(Like like) {
		// TODO Auto-generated method stub
		return session.selectOne("Like.getLikeCount", like);
	}
	
	//조아요취소
	@Override
	public int removeLike(Like like) {
		log.debug("좋아요 취소dao");
		log.debug("좋아요취소"+ like);
		return session.delete("Like.removeLike", like);
	}

	// 조아요 췤
	@Override
	public boolean check(Like like) {
		log.debug("좋아요 췤dao");
		
		 Integer likeCount = session.selectOne("Like.check", like);
		 return likeCount != null && likeCount > 0;
	}

	
	
	
}
