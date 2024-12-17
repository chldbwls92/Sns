package com.chldbwls.sns.post.dto;

import java.util.List;

import com.chldbwls.sns.comment.dto.CommentDTO;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CardDTO {
	// 하나의 카드를 작성하기 위해 필요한 부분
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	
	private String loginId;
	
	// 갯수 세기 위한 멤버변수 추가
	private int likeCount;
	private boolean isLike;
	
	List<CommentDTO> commentList;

}
