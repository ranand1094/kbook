package com.kloudeone.comments.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "commentreplies")
public class ReplyCommentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 30)
	private Long replycommentid;
	private String comment;
	private String postedBy;
	private Date postedOn; 
	
	@ManyToOne
	@JoinColumn(name = "commentid",referencedColumnName = "commentid")
	@JsonIgnore
	private CommentsEntity commentsEntity;
	
	
}