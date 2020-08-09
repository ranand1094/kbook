package com.kloudeone.comments.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="comments")
@Getter
@Setter
public class CommentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 30)
	private Long commentid;
	
	private String comment;
	private String postedBy;
	private LocalDateTime postedOn;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "postid",referencedColumnName = "postid")
	@JsonIgnore
  	private PostsEntity posts;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "commentid",referencedColumnName = "commentid")
 	private List<ReplyCommentsEntity> replyComments;
 	
}
