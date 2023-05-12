package team.spring.trip.courseBoard.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.spring.trip.courseBoard.dao.CourseBoardDao;
import team.spring.trip.courseBoard.vo.CourseBoard;

@Service
@Transactional
public class CourseBoardServiceImpl implements CourseBoardService {

	@Autowired
	private CourseBoardDao courseBoardDao;
	
	Logger log = LogManager.getLogger("case3");

	//코스 목록
	@Override
	public List<CourseBoard> courseList() {

		return courseBoardDao.courseList();
	}

	@Override
	public int insertCourse(CourseBoard courseBoard) {
		
		return courseBoardDao.insertCourse(courseBoard);
	}

	//코스 조회
	@Override
	public int selectCourse(CourseBoard courseBoard) {
		log.debug("코스 조회service");
		return courseBoardDao.selectCourse(courseBoard);
	}

	//좋아요
	@Override
	public int plusLike(int courseNum) {
		
		return courseBoardDao.plusLike(courseNum);
	}

	//좋아요 취소
	@Override
	public int deleteLike(int courseNum) {
		// TODO Auto-generated method stub
		return courseBoardDao.deleteLike(courseNum);
	}

	//좋아요 순으로 글 목록
	@Override
	public List<CourseBoard> likeList() {
		// TODO Auto-generated method stub
		return courseBoardDao.likeList();
	}
	

	


	
	
}
