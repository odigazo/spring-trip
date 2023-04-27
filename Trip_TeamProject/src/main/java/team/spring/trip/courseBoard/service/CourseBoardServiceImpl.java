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
	public List<CourseBoard> courseList(CourseBoard courseBoard) {
		log.debug("코스 목록service");
		return courseBoardDao.courseList(courseBoard);
	}
	

	
}
