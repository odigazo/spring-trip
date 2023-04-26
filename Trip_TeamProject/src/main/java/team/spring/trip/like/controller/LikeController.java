package team.spring.trip.like.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.spring.trip.like.service.LikeService;
import team.spring.trip.like.service.LikeServiceImpl;
import team.spring.trip.like.vo.Like;

@RestController
@RequestMapping(value="like",produces="application/json")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	Logger log = LogManager.getLogger("case3");
	
	//좋아요
	@PostMapping(value="/addLike")
	public int addLike(Like like){
		log.debug("좋아요 ct");
		int result = likeService.addLike(like);
		return result;
	}
	
	//좋아요 취소
	@DeleteMapping(value = "/removeLike")
	public int removeLike(Like like) {
		log.debug("좋아요취소 ct");
		int result = likeService.removeLike(like);
		return result;
	}
}
