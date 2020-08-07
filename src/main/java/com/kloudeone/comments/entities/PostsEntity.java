package com.kloudeone.comments.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "posts")
@Getter
@Setter

public class PostsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 30)
	private Long postid;
	private String message;
	private String postedBy;
	private Date postedOn;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "postid",referencedColumnName = "postid")
	private List<CommentsEntity> comments;
	
}
