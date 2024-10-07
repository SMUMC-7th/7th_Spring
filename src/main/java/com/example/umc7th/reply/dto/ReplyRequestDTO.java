package com.example.umc7th.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReplyRequestDTO {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class CreateReplyDTO {
		private String content;

	}
}
