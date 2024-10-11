package com.example.umc7th.reply.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// JPA가 해당 클래스가 Entity라는 것을 인식하도록 해주는 Annotation
@Entity
// 테이블 이름을 지정하기 위해 @Table 사용
@Table(name = "reply")
// 빌더 패턴 사용
@Builder
// 기본 생성자 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 모든 인자를 가지는 생성자 추가 (private으로 선언하여 객체 생성을 Builder 패턴 제한)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// Getter 생성
@Getter
@Setter
public class Reply extends BaseEntity {
	// 해당 필드(Long id)를 PK(Primary key)로 지정
	@Id
	// PK의 생성 전략 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Column의 이름을 지정하기 위해 사용
	@Column(name = "content")
	private String content;

	// N:1 매핑, fetchType을 LAZY로 변경 (default = EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	// 해당 article을 article_id라는 이름으로 Column 추가 (실제 객체가 아닌 Long id를 저장하기에 이름을 article_id로 지정)
	@JoinColumn(name = "article_id")
	private Article article;
}
