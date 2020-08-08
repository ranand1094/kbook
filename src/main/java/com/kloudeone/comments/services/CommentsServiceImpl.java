package com.kloudeone.comments.services;

import java.util.ArrayList;
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
				commentsEntity.setPostedOn(getDateTime());
				commentsEntity = commentsRepository.save(commentsEntity);
				response.put("responseCode", "201");
				response.put("responseMessage", "Added a new comment");
				response.put("postID", commentsEntity.getCommentid());
			} catch (Exception ex) {
				response.put("responseCode", "500");
				response.put("responseMessage", "Error in processing - " + ex.getMessage());
			}
		} else {
			response.put("responseCode", "404");
			response.put("responseMessage", "No such post found");
		}

		return response;
	}

	@Override
	public Map getAllComments(Long postid) {
		Map response = new LinkedHashMap<>();
		Optional<PostsEntity> validatepostId = postsRepository.findById(postid);
		PostsEntity post = new PostsEntity();
		if (validatepostId.isPresent()) {
			post = validatepostId.get();
			response.put("responseCode", "200");
			response.put("responseMessage", "Request Processed Successfully");
			response.put("responseDetails", post);
		}
		else
		{
			response.put("responseCode", "404");
			response.put("responseMessage", "No such post found");
		}
		return response;
	}

	@Override
	public Map addNewReplyComment(Long postid, Long commentid, CommentsModel commentsModel) {
		Map response = new LinkedHashMap<>();
		Optional<PostsEntity> validatepostId = postsRepository.findById(postid);
		if (validatepostId.isPresent()) {
			Optional<CommentsEntity> validateCommentId = commentsRepository.findById(commentid);
			if (validateCommentId.isPresent()) {
				replyCommentsEntity = convertObj(commentsModel, replyCommentsEntity);
				replyCommentsEntity.setPostedOn(getDateTime());
				replyCommentsEntity.setCommentsEntity(validateCommentId.get());
				try {
					replyCommentsEntity = replyCommentsRepository.save(replyCommentsEntity);
					response.put("responseCode", "201");
					response.put("responseMessage", "Added a new reply");
					response.put("replyCommentID", replyCommentsEntity.getReplycommentid());
				} catch (Exception ex) {
					response.put("responseCode", "500");
					response.put("responseMessage", "Error in processing - " + ex.getMessage());
				}
			} else {
				response.put("responseCode", "404");
				response.put("responseMessage", "No such comment found");
			}
		} else {
			response.put("responseCode", "404");
			response.put("responseMessage", "No such post found");

		}
		return response;
	}

}
