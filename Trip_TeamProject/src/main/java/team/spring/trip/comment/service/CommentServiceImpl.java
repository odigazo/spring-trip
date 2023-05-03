package team.spring.trip.comment.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.spring.trip.comment.dao.CommentDao;
import team.spring.trip.comment.vo.Comment;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	Logger log = LogManager.getLogger("case3");
	
	//댓쓰기
	@Override
	public int createComment(Comment comment) {
		log.debug("댓쓰기 서비스");
		int result = commentDao.insertComment(comment);
		return result;
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
	public List<Comment> deleteCommentSv(Comment comment) {
		log.debug("삭제 서비스");
		
		int result = 0;
		List<Comment> list = null;
		try {
			result = commentDao.deleteComment(comment);
			if (result == 1) {
				list = commentDao.getAllComment(comment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	//댓글리스트
	@Override
	public List<Comment> allComment(Comment comment) {
		log.debug("댓글 리스트 service");
		return commentDao.getAllComment(comment);
	}
	

	
}


