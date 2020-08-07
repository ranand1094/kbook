package com.kloudeone.comments.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kloudeone.comments.entities.CommentsEntity;
import com.kloudeone.comments.entities.PostsEntity;
import com.kloudeone.comments.entities.ReplyCommentsEntity;
import com.kloudeone.comments.model.CommentsModel;
import com.kloudeone.comments.repository.CommentsRepository;
import com.kloudeone.comments.repository.PostsRepository;
import com.kloudeone.comments.repository.ReplyCommentsRepository;
import com.kloudeone.comments.services.interfaces.CommentsInterface;
import com.kloudeone.comments.services.interfaces.HelperInterface;

@Service
public class CommentsServiceImpl implements CommentsInterface, HelperInterface {

	@Autowired
	CommentsRepository commentsRepository;

	@Autowired
	PostsRepository postsRepository;

	@Autowired
	ReplyCommentsRepository replyCommentsRepository;

	CommentsEntity commentsEntity = new CommentsEntity();
	ReplyCommentsEntity replyCommentsEntity = new ReplyCommentsEntity();

	@Override
	public Map addNewComment(Long postid, CommentsModel commentsModel) {

		Map response = new LinkedHashMap<>();

		Optional<PostsEntity> validateId = postsRepository.findById(postid);
		if (validateId.isPresent()) {
			commentsEntity = convertObj(commentsModel, commentsEntity);
			try {
				commentsEntity.setPosts(validateId.get());
				commentsEntity = commentsRepository.save(commentsEntity);
				response.put("ResponseCode", "200");
				response.put("ResponseMessage", "200");
				response.put("PostID", commentsEntity.getCommentid());
			} catch (Exception ex) {
				response.put("ResponseCode", "500");
				response.put("ResponseMessage", "Error in processing - " + ex.getMessage());
			}
		} else {
			response.put("ResponseCode", "404");
			response.put("ResponseMessage", "No such post found");
		}

		return response;
	}

	@Override
	public Map getAllComments() {

		Map response = new HashMap<>();
		List<CommentsEntity> listOfComments = new ArrayList<CommentsEntity>();
		listOfComments = commentsRepository.findAll();
		response.put("ResponseCode", "200");
		response.put("ResponseMessage", "200");
		response.put("Comments", listOfComments);
		return response;
	}

	@Override
	public Map addNewReplyComment(Long commentid, CommentsModel commentsModel) {
		Map response = new LinkedHashMap<>();

		Optional<CommentsEntity> validateId = commentsRepository.findById(commentid);
		if (validateId.isPresent()) {
			replyCommentsEntity.setCommentsEntity(validateId.get());
			replyCommentsEntity = convertObj(commentsModel, replyCommentsEntity);
			try {
				replyCommentsEntity = replyCommentsRepository.save(replyCommentsEntity);
				response.put("responseCode", "200");
				response.put("responseMessage", "200");
				response.put("replyCommentID", replyCommentsEntity.getReplycommentid());
			} catch (Exception ex) {
				response.put("ResponseCode", "500");
				response.put("ResponseMessage", "Error in processing - " + ex.getMessage());
			}
		} else {
			response.put("ResponseCode", "404");
			response.put("ResponseMessage", "No such comment found");
		}
		return response;
	}

}
