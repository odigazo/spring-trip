package team.spring.trip.comment.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.trip.comment.vo.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SqlSession session;

	Logger log = LogManager.getLogger("case3");

	//댓쓰기
	@Override
	public int insertComment(Comment comment) {
		log.debug("댓글쓰기 dao");
		return session.insert("comment.insertComment", comment);
	}

	//댓수정
	@Override
	public int updateCommentDao(Comment comment) {
		log.debug("댓글수정 dao");
		int count = session.update("comment.updateComment", comment);
		log.debug(count);
		return count;
	}

	//댓삭제
	@Override
	public int deleteComment(Comment comment) throws Exception {
		log.debug("댓삭제 dao");
		return session.update("comment.deleteComment", comment);
	}

	//댓글리스트
	@Override
	public List<Comment> getAllComment(Comment comment) {
		log.debug("댓글리스트 dao");
		return session.selectList("comment.commentList", comment);
	}

	
	
	
}
