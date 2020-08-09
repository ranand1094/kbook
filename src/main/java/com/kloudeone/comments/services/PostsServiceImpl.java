package com.kloudeone.comments.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kloudeone.comments.entities.PostsEntity;
import com.kloudeone.comments.model.PostsModel;
import com.kloudeone.comments.repository.PostsRepository;
import com.kloudeone.comments.services.interfaces.HelperInterface;
import com.kloudeone.comments.services.interfaces.PostsInterface;

@Service
public class PostsServiceImpl implements PostsInterface, HelperInterface {

	@Autowired
	PostsRepository postRepository;
	PostsEntity postsEntity = new PostsEntity();

	@Override
	public Map addNewPost(PostsModel postsModel) {
		Map response = new LinkedHashMap<>();
		try {
			postsEntity = convertObj(postsModel, postsEntity);
			postsEntity.setPostedOn(getDateTime());
			postsEntity = postRepository.save(postsEntity);
			response.put("responseCode", "201");
			response.put("responseMessage", "Created a new post");
			response.put("postID", postsEntity.getPostid());
		} catch (Exception e) {
			response.put("responseCode", "500");
			response.put("responseMessage", "Error in creating the post - " + e.getMessage());
		}
		return response;
	}

	@Override
	public Map getPost(Long post) {
		Map response = new LinkedHashMap<>();
		Map subResponse = new LinkedHashMap<>();

		Optional<PostsEntity> posts = postRepository.findById(post);
		if (posts.isPresent()) {
			postsEntity = posts.get();
 			response.put("responseCode", "202");
			response.put("responseMessage", "Request processed successfully");
			response.put("postId", postsEntity.getPostid());
			response.put("message", postsEntity.getMessage());
			response.put("postedBy", postsEntity.getPostedBy());
			response.put("postedOn", postsEntity.getPostedOn());
			response.put("comments",postsEntity.getComments());
 		} else {
			response.put("responseCode", "404");
			response.put("responseMessage", "No such post found");
		}
		return response;
	}

	@Override
	public Map getAllPosts() {
		Map response = new LinkedHashMap<>();
		try {
			List<PostsEntity> listOfPosts = postRepository.findAll();
			if (!listOfPosts.isEmpty()) {
				
 				response.put("responseCode", "200");
				response.put("responseMessage", "Request processed successfully");
				response.put("listOfPosts", listOfPosts);
			} else {
				response.put("responseCode", "404");
				response.put("responseMessage", "No Active Posts.");
			}
		} catch (Exception ex) {
			response.put("responseCode", "500");
			response.put("responseMessage", "No Active Posts.");
		}
		return response;
	}
}