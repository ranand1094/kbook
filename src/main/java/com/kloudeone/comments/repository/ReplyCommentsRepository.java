package com.kloudeone.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kloudeone.comments.entities.ReplyCommentsEntity;

@Repository
public interface ReplyCommentsRepository extends JpaRepository<ReplyCommentsEntity, Long> {

}
