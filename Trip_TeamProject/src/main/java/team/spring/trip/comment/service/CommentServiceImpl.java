package team.spring.trip.comment.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.trip.comment.dao.CommentDao;
import team.spring.trip.comment.vo.Comment;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	Logger log = LogManager.getLogger("case3");
	
	//댓쓰기
	@Override
	public Comment createComment(Comment comment) {
		log.debug("댓쓰기 서비스");
		int result = commentDao.insertComment(comment);
		if (result != 0) {
			return null;
			//return commentDao.(comment);
		} else {
		 return null;
		}
	}

	//댓 수정
	@Override
	public int editComment(Comment comment) {
		log.debug("수정 서비스");
		
		int result = commentDao.updateCommentDao(comment);
		return result;
	}

	//댓삭제
	@Override
	public int deleteCommentSv(Comment comment) {
		log.debug("삭제 서비스");
		
		int result = commentDao.deleteComment(comment);
		return result;
	}

	//댓글리스트
	@Override
	public List<Comment> allComment() {
		// TODO Auto-generated method stub
		return commentDao.getAllComment();
	}
	

	
}


