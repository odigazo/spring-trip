package team.spring.trip.courseBoard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.trip.courseBoard.vo.CourseBoard;

@Repository
public class CourseBoardDaoImpl implements CourseBoardDao {

	@Autowired
	private SqlSession session;
	
	Logger log = LogManager.getLogger("case3");

	//코스 목록
	@Override
	public List<CourseBoard> courseList(CourseBoard courseBoard) {
	
		return session.selectList("courseBoard.courseBoardList");
	}
	
	
}
