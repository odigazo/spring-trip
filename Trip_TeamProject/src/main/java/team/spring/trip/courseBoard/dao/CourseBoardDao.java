package team.spring.trip.courseBoard.dao;

import java.util.List;

import team.spring.trip.courseBoard.vo.CourseBoard;

public interface CourseBoardDao {

	List<CourseBoard> courseList();

	int insertCourse(CourseBoard courseBoard);

	int selectCourse(CourseBoard courseBoard);

	int plusLike(int courseNum);

	int deleteLike(int courseNum);

}
