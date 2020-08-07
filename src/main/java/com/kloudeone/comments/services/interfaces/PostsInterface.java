package com.kloudeone.comments.services.interfaces;

import java.util.Map;

import com.kloudeone.comments.model.PostsModel;

public interface PostsInterface {

	Map addNewPost(PostsModel postsModel);
	Map getPost(Long post);
	Map getAllPosts();

}
