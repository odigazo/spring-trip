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
	public Comment insertComment(@ModelAttribute Comment comment, Model model) {
		log.debug("");
		Comment result = commentService.createComment(comment);
		return result;
	}

	
	//댓글 수정
	@PutMapping(value ="/updateComment")
	public int updateComment(Comment comment, Model model) {
		log.debug("update컨트롤러");
		
		int result = commentService.editComment(comment);
		return result;
	}
	
	
	//댓글삭제
	@PutMapping(value = "/deleteComment")
	public int deleteComment(Comment comment, Model model) {
		int result = commentService.deleteCommentSv(comment);
		return result;
	}
	
	//댓글리스트
	@GetMapping(value = "/commentList")
	public String commentList(Model model) {
		List<Comment> comment = commentService.allComment();
		model.addAttribute("comment", comment);
		return null;
	}
	
	
}
