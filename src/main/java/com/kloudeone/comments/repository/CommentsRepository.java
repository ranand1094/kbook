package com.kloudeone.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kloudeone.comments.entities.CommentsEntity;

@Repository
public interface CommentsRepository  extends JpaRepository<CommentsEntity, Long>{

}
