package com.chldbwls.sns.post.dto;

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
	

}
