package com.kloudeone.comments.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kloudeone.comments.model.CommentsModel;
import com.kloudeone.comments.model.PostsModel;
import com.kloudeone.comments.services.CommentsServiceImpl;
import com.kloudeone.comments.services.PostsServiceImpl;

@RestController
@RequestMapping("/kbook/comments")
public class CommentsController {

	@Autowired
	private CommentsServiceImpl commentsService;

	@GetMapping("/{postid}")
	public Map getPostComment(@PathVariable Long postid) {
		return commentsService.getAllComments(postid);
	}
	
	@PostMapping("/{postid}")
	public Map addNewComment(@PathVariable Long postid, @RequestBody CommentsModel commentsModel) {
		return commentsService.addNewComment(postid, commentsModel);
	}

	@PostMapping("/{postid}/reply/{commentid}")
	public Map addNewReplyComment(@PathVariable Long postid, @PathVariable Long commentid,
			@RequestBody CommentsModel commentsModel) {
		return commentsService.addNewReplyComment(postid, commentid, commentsModel);
	}

}