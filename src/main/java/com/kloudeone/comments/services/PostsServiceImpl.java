package com.kloudeone.comments.services;

import java.util.ArrayList;
import java.util.HashMap;
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
public class PostsServiceImpl implements PostsInterface,HelperInterface {

	@Autowired
	PostsRepository postRepository;
	
 	PostsEntity postsEntity = new PostsEntity();
	
	
	@Override
	public Map addNewPost(PostsModel postsModel) {
		Map response = new HashMap<>();

		try
		{
		postsEntity = convertObj(postsModel,postsEntity);
		postsEntity = postRepository.save(postsEntity);
		response.put("ResponseCode","201");
		response.put("ResponseMessage","Created a new post");
		response.put("PostID",postsEntity.getPostid());
		}
		catch(Exception e) {
			response.put("ResponseCode","500");
			response.put("ResponseMessage","Error in creating the post - "+e.getMessage());
		}
		return response;
	}


	@Override
	public Map getPost(Long post) {
		Map response = new HashMap<>();
		Optional<PostsEntity> posts = postRepository.findById(post);
		
		if(posts.isPresent())
		{
			 postsEntity = posts.get();
			response = convertObj(postsEntity, response);
			response.put("ResponseCode", "200");
			response.put("ResponseMessage", "Request processed successfully");
			
			
		}
		else
		{
			response.put("ResponseCode", "404");
			response.put("ResponseMessage", "No such post found");
		}
		
		return response;

		
		
	}


	@Override
	public Map getAllPosts() {
		Map response = new HashMap<>();
		
		try {
		List<PostsEntity> listOfPosts =  postRepository.findAll();
		if(!listOfPosts.isEmpty())
		{
		response.put("ResponseCode", "200");
		response.put("ResponseMessage", "Request processed successfully");
 		response.put("Comments", listOfPosts);
		}
		else
		{
			response.put("ResponseCode", "404");
			response.put("ResponseMessage", "No Active Posts.");
			}
		
		}
		catch(Exception ex)
		{
			response.put("ResponseCode", "404");
			response.put("ResponseMessage", "No Active Posts.");
			}
		return response;
	}

}
