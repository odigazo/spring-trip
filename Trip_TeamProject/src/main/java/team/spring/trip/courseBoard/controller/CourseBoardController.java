package team.spring.trip.courseBoard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.trip.comment.vo.Comment;
import team.spring.trip.courseBoard.service.CourseBoardService;
//import team.spring.trip.courseBoard.vo.CourseBoard;
import team.spring.trip.courseBoard.vo.CourseBoard;

@RestController
@RequestMapping(value = "courseBoard", produces = "application/json")
public class CourseBoardController {

	@Autowired
	private CourseBoardService courseBoardService;

	Logger log = LogManager.getLogger("case3");

	// 코스 목록
	@GetMapping(value = "/courseList")
	public List<CourseBoard> courseList() {
//		log.debug("testtest"+courseBoard.getCourseNum());
//	    courseBoard.setCourseNum(1); //임시
//		log.debug("코스 목록 ct");
//		log.debug("코스 목록="+courseBoard);
		List<CourseBoard> list = courseBoardService.courseList();
		log.debug(list);
		return list;
	}

	@GetMapping(value = "/myList")
	public Map<String, String> myList(@RequestParam(value = "userNum", required = false) int userNum) throws JsonProcessingException {

		List<CourseBoard> list1 = courseBoardService.myList(userNum);
		List<Comment> list2 = courseBoardService.myComments(userNum);
		Map<String, String> map = new HashMap<String, String>();
		int count=0;
		for(CourseBoard cb : list1) {
			if(cb.getTravelStatus().startsWith("N")) {
				count++;
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String boardlist = mapper.writeValueAsString(list1);
		String commentlist = mapper.writeValueAsString(list2);

		map.put("boardlist", boardlist);
		map.put("commentlist", commentlist);
		map.put("count",Integer.toString(count));
		log.debug(list1);
		log.debug(list2);
		return map;
	}

	// 좋아요 순 게시글
	@GetMapping(value = "/likeList")
	public List<CourseBoard> likeList() {

		List<CourseBoard> list = courseBoardService.likeList();
		log.debug("좋아요 순으로 " + list);
		return list;
	}

	// 글쓰기
	@PostMapping(value = "/insertCourse")
	public int insertCourse(@RequestBody Map<String, Object> param) {
//		log.debug(param.get("userNum"));
//		log.debug(param.get("courseTitle"));
//		for(String s : (List<String>)param.get("courseContents")) {
//			log.debug(s);
//		}
		CourseBoard courseboard = new CourseBoard();
		courseboard.setUserNum((int) param.get("userNum"));
		courseboard.setCourseTitle((String) param.get("courseTitle"));
		StringBuilder sb = new StringBuilder();
		for (String s : (List<String>) param.get("courseContents")) {
			sb.append(s + "\n");
		}
		courseboard.setCourseContents(sb.toString());
		log.debug("유저번호" + courseboard.getUserNum());
		log.debug("코스 insert");

		int result = courseBoardService.insertCourse(courseboard);
		log.debug(result);
		return result;
	}
	
	@PutMapping(value = "/updateCourse")
	public int updateCourse(@RequestBody Map<String, Object> param) {
		log.debug(param.get("courseNum"));
//		log.debug(param.get("courseTitle"));
//		for(String s : (List<String>)param.get("courseContents")) {
//			log.debug(s);
//		}
		CourseBoard courseboard = new CourseBoard();
		courseboard.setCourseNum((int)param.get("courseNum"));
		log.debug("코스 insert");

		int result = courseBoardService.updateCourse(courseboard);
		log.debug(result);
		
		return result;
	}
	
	// 코스 조회
	@GetMapping(value = "/selectCourse")
	public int selectCourse(@ModelAttribute CourseBoard courseBoard) {
		log.debug("코스 조회 ct");
		log.debug("코스 조회=" + courseBoard);

		int result = courseBoardService.selectCourse(courseBoard);
		return result;
	}
}
