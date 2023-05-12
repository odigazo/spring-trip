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

	//글쓰기
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

	//좋아요 +1
	@Override
	public int plusLike(int courseNum) {
		
		return session.update("courseBoard.plusLike",courseNum);
	}

	//좋아요 두번클릭 시 -1
	@Override
	public int deleteLike(int courseNum) {
		// TODO Auto-generated method stub
		return session.update("courseBoard.deleteLike",courseNum);
	}

	//좋아요 순으로 글 목록
	@Override
	public List<CourseBoard> likeList() {
		log.debug("좋아요 순 dao");
		return session.selectList("courseBoard.likeList");
	}
	
	
}
