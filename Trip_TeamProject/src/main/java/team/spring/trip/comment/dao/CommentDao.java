package team.spring.trip.comment.dao;

import java.util.List;

import team.spring.trip.comment.vo.Comment;

public interface CommentDao {


	int insertComment(Comment comment);

	int updateCommentDao(Comment comment);
	
	int deleteComment(Comment comment);

	List<Comment> getAllComment();
}
