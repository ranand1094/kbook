package com.kloudeone.comments.services.interfaces;

import java.util.Map;

import com.kloudeone.comments.model.CommentsModel;

public interface CommentsInterface {

	Map addNewComment(Long postId,CommentsModel commentsModel);
	Map getAllComments(Long postId);
	Map addNewReplyComment(Long postId,Long commentId,CommentsModel commentsModel);

}
