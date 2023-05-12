package team.spring.trip.courseBoard.service;

import java.util.List;

import team.spring.trip.courseBoard.vo.CourseBoard;

public interface CourseBoardService {

	List<CourseBoard> courseList();

	int insertCourse(CourseBoard courseBoard);

	int selectCourse(CourseBoard courseBoard);

	int plusLike(int courseNum);

	int deleteLike(int courseNum);

	List<CourseBoard> likeList();

}
