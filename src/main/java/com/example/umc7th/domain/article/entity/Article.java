package com.example.umc7th.domain.article.entity;
import java.util.List;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Article extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "like_num")
	private int likeNum;

	@OneToMany(mappedBy = "article")
	private List<Reply> replies;

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void increaseLike() {
		this.likeNum++;
	}
}