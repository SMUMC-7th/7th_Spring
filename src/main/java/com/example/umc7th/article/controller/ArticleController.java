package com.example.umc7th.article.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.global.config.apiPayload.CustomResponse;

import lombok.RequiredArgsConstructor;

@RestController // Restful API를 제공하는 컨트롤러를 뜻하며, 반환되는 데이터를 JSON 형태의 데이터를 반환한다.
@RequiredArgsConstructor // final로 선언된 필드에 대한 생성자를 자동으로 생성해서 의존성을 주입함(생성자 주입)

public class ArticleController { // 게시글 관련된 API 요청을 처리하는 컨트롤러 역할을 하는 클래스이다.
	private final ArticleQueryService articleQueryService;
	private final ArticleCommandService articleCommandService;

	// 생성이므로 POST method 사용
	@PostMapping("/articles") // POST/articles의 HTTP 요청에 대해 실행되는 메서드
	// 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
	public CustomResponse<ArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {

		// service에서 게시글 생성한 게시글 가져오기
		 Article article = articleCommandService.createArticle(dto);

		 //Article을 ArticleResponseDTO로 변환
		ArticleResponseDTO responseDTO = new ArticleResponseDTO(article);

		// CustomResponse에 article을 담아 성공했다고 응답하기
		return CustomResponse.onSuccess(responseDTO);
	}

	// 생성이므로 GET method 사용
	// 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
	@GetMapping("/articles/{articleId}")
	// @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
	public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
		// articleId로 게시글 조회
		Article article = articleQueryService.getArticle(articleId);

		// ArticleResponseDTO로 변환하여 응답
		ArticleResponseDTO responseDto = new ArticleResponseDTO(article);
		return CustomResponse.onSuccess(responseDto);
	}

	@GetMapping("/articles")
	public CustomResponse<List<ArticleResponseDTO>> getArticles() {
		List<Article> articles = articleQueryService.getArticles();
		List<ArticleResponseDTO> responseDtos = articles.stream()
			.map(ArticleResponseDTO::new) // Article 엔티티를 ArticleResponseDTO로 변환
			.toList();
		return CustomResponse.onSuccess(responseDtos);
	}
}
