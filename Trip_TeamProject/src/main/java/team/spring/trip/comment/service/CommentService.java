package team.spring.trip.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import team.spring.trip.comment.vo.Comment;

@Service
public interface CommentService {

	//댓쓰기
	int createComment(Comment comment);

	//댓수정
	int editComment(Comment comment);
	
	//댓삭제
	List<Comment> deleteCommentSv(Comment comment);
	
	//댓리스트
	List<Comment> allComment(Comment comment);
}
