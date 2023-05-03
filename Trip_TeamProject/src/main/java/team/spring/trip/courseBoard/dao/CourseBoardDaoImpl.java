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
	public List<CourseBoard> courseList() {
		log.debug("코스 목록 dao");
		return session.selectList("courseBoard.courseBoardList");
	}

	@Override
	public int insertCourse(CourseBoard courseBoard) {
		int test = courseBoard.getUserNum();
		System.out.println(test);
		return session.insert("courseBoard.insertCourse",courseBoard);
	}

	@Override
	public int selectCourse(CourseBoard courseBoard) {
		log.debug("코스 조회 dao");
		
		return session.selectOne("courseBoard.selectCourse");
	}

	@Override
	public int plusLike(int courseNum) {
		
		return session.update("courseBoard.plusLike",courseNum);
	}

	@Override
	public int deleteLike(int courseNum) {
		// TODO Auto-generated method stub
		return session.update("courseBoard.deleteLike",courseNum);
	}
	
	
}
