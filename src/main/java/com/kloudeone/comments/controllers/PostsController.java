package com.kloudeone.comments.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kloudeone.comments.model.PostsModel;
import com.kloudeone.comments.services.PostsServiceImpl;

@RestController
@RequestMapping("/kbook/posts")
public class PostsController {

	@Autowired
	private PostsServiceImpl postService;

	@GetMapping("/")
	public Map getAll() {
		return postService.getAllPosts();
	}
	
	@GetMapping("/{postid}")
	public Map getPost(@PathVariable Long postid) {
		return postService.getPost(postid);
	}

	@PostMapping("/new")
	public Map addNewPost(@RequestBody PostsModel postsModel) {
		return postService.addNewPost(postsModel);
	}
	
}