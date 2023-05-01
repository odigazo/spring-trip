package team.spring.trip.courseBoard.service;

import java.util.List;

import team.spring.trip.courseBoard.vo.CourseBoard;

public interface CourseBoardService {

	List<CourseBoard> courseList(CourseBoard courseBoard);

	int insertCourse(CourseBoard courseBoard);

}
