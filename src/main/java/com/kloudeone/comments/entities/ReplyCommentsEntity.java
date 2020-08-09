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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private Long replyparentid;
	
	private String comment;
	private String postedBy;
	private LocalDateTime postedOn;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "commentid",referencedColumnName = "commentid")
	@JsonIgnore
 	private CommentsEntity commentsEntity;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "replychildid",referencedColumnName = "replyparentid")
	private  List<ReplyCommentsEntity> replyOfReply;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "replychildid",referencedColumnName = "replyparentid")
	@JsonIgnore
	private  ReplyCommentsEntity replyOfRply;
}
