package com.kloudeone.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kloudeone.comments.entities.PostsEntity;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {

}
