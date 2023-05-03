package team.spring.trip.like.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.spring.trip.courseBoard.service.CourseBoardService;
import team.spring.trip.courseBoard.vo.CourseBoard;
import team.spring.trip.like.service.LikeService;
import team.spring.trip.like.service.LikeServiceImpl;
import team.spring.trip.like.vo.Like;

@RestController
@RequestMapping(value="like",produces="application/json")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private CourseBoardService courseBoardService;
	
	Logger log = LogManager.getLogger("case3");
	
	//좋아요
	@PostMapping(value="/addLike")
	public List<CourseBoard> addLike(Like like){
	    log.debug("좋아요 ct");
	    log.debug("좋아요"+ like);
	    int result=0;
	    boolean check = likeService.check(like);
	    //같은게 없으면 check=true 같은게 있으면 false
	    if(!check) {
	    	likeService.addLike(like);
	    	result = courseBoardService.plusLike(like.getCourseNum());
		    if(result==1) {
		    	log.debug("좋아요 증가 성공");
		    }
	    }else {
	    	likeService.removeLike(like);
	    	result = courseBoardService.deleteLike(like.getCourseNum());
		    if(result==1) {
		    	log.debug("좋아요 감소 성공");
		    }
	    }    

	    
//	    int likeCount = likeService.getLikeCount(like);
//	    return ResponseEntity.ok(likeCount);
	    List<CourseBoard> list = courseBoardService.courseList();
	    
	    
	    
	    return list;
	}
	
	//좋아요 취소
	@DeleteMapping(value = "/removeLike")
	public int removeLike(Like like) {
		log.debug("좋아요취소 ct");
		int result = likeService.removeLike(like);
		return result;
	}
}
