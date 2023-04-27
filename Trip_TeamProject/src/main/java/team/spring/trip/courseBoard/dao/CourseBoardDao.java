package team.spring.trip.courseBoard.dao;

import java.util.List;

import team.spring.trip.courseBoard.vo.CourseBoard;

public interface CourseBoardDao {

	List<CourseBoard> courseList(CourseBoard courseBoard);

}
