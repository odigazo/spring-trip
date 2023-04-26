package team.spring.trip.comment;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.spring.trip.comment.service.CommentService;
import team.spring.trip.comment.vo.Comment;

@RestController
@RequestMapping(value="comment",produces="application/json")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	Logger log = LogManager.getLogger("case3");
	
	//댓글쓰기
	@PostMapping(value = "/insertComment")
	public List<Comment> insertComment(@ModelAttribute Comment comment) {
		log.debug("쓰기 controller");
		log.debug(comment.getUserNum());
		comment.setCourseNum(1);
		int result = commentService.createComment(comment);
		List<Comment> list = commentService.allComment();
		return list;
	}

	
	//댓글 수정
	@PutMapping(value ="/updateComment")
	public List<Comment> updateComment(@ModelAttribute Comment comment) {
		log.debug("update컨트롤러");
		log.debug(comment.getContents());
		log.debug(comment.getCommentNum());
		int result = commentService.editComment(comment);
		if(result==1) {
			List<Comment> list = commentService.allComment();
			return list;
		}
		return null;
	}
	
	
	//댓글삭제
	@PutMapping(value = "/deleteComment")
	public int deleteComment(@ModelAttribute Comment comment) {
		log.debug("댓글 삭제ct");
		log.debug(comment.getUserNum());
		int result = commentService.deleteCommentSv(comment);
		return result;
	}
	
	//댓글리스트
	@GetMapping(value = "/commentList")
	public List<Comment> commentList() {
		List<Comment> list = commentService.allComment();
		return list;
	}
	
	
}
