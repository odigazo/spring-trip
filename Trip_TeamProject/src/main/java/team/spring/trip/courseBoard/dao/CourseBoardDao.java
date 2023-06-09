package team.spring.trip.courseBoard.dao;

import java.util.List;

import team.spring.trip.comment.vo.Comment;
import team.spring.trip.courseBoard.vo.CourseBoard;

public interface CourseBoardDao {

	List<CourseBoard> courseList();

	int insertCourse(CourseBoard courseBoard);

	int selectCourse(CourseBoard courseBoard);

	int plusLike(int courseNum);

	int deleteLike(int courseNum);

	List<CourseBoard> likeList();

	List<CourseBoard> myList(int userNum);

	List<Comment> myComments(int userNum);

	int updateStatus(CourseBoard courseboard);

}
