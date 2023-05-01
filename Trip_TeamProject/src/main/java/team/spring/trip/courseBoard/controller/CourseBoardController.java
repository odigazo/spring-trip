package team.spring.trip.courseBoard.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.spring.trip.courseBoard.service.CourseBoardService;
//import team.spring.trip.courseBoard.vo.CourseBoard;
import team.spring.trip.courseBoard.vo.CourseBoard;

@RestController
@RequestMapping(value="courseBoard",produces="application/json")
public class CourseBoardController {

	@Autowired
	private CourseBoardService courseBoardService;
	
	Logger log = LogManager.getLogger("case3");
	
	//코스 목록
	@GetMapping(value = "/courseList")
	public List<CourseBoard> courseList(@ModelAttribute CourseBoard courseBoard){
		log.debug("testtest"+courseBoard.getCourseNum());
	    courseBoard.setCourseNum(1); //임시
		log.debug("코스 목록 ct");
		log.debug("코스 목록="+courseBoard);
		List<CourseBoard> list = courseBoardService.courseList(courseBoard);
		log.debug(list);
		return list;
	}
}
