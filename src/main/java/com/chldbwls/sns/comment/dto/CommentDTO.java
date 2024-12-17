package com.chldbwls.sns.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDTO {
	
	// 하나의 댓글 내용
	private int commentId;
	private int userId;
	
	private String loginId;
	private String contents;

}
