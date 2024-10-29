package com.example.umc7th.article.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.global.config.apiPayload.CustomResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController // Restful API를 제공하는 컨트롤러를 뜻하며, 반환되는 데이터를 JSON 형태의 데이터를 반환한다.
@RequiredArgsConstructor // final로 선언된 필드에 대한 생성자를 자동으로 생성해서 의존성을 주입함(생성자 주입)

@Tag(name = "게시글 API")
public class ArticleController { // 게시글 관련된 API 요청을 처리하는 컨트롤러 역할을 하는 클래스이다.
	private final ArticleQueryService articleQueryService;
	private final ArticleCommandService articleCommandService;

	@PostMapping("/articles") // POST/articles의 HTTP 요청에 대해 실행되는 메서드
	@Operation(summary = "게시글 생성 API", description = "게시글을 생성하는 API")
	// 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
	public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
		// service에서 게시글 생성한 게시글 가져오기
		 Article article = articleCommandService.createArticle(dto); // save 메서드는 그 클래스의 객체인 상태로 반환함

		// CustomResponse에 article을 담아 성공했다고 응답하기
		return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article)); // 응답용 DTO 객체를 생성
	}

	// 생성이므로 GET method 사용
	// 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
	@GetMapping("/articles/{articleId}")
	@Operation(summary = "게시글 조회 API", description = "게시글을 하나 조회하는 API")
	// @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
	public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
		// articleId로 게시글 조회
		Article article = articleQueryService.getArticle(articleId);

		return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
	}

	@GetMapping("/articles")
	@Operation(summary = "게시글 전체 조회 API", description = "게시글 전체 조회하는 API")
	public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
		List<Article> articles = articleQueryService.getArticles();
		return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
	}

	@PutMapping("/articles/{articleId}")
	@Operation(summary = "게시글 수정 API", description = "게시글을 수정하는 API")
	public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> updateArticle(
		@PathVariable("articleId") Long articleId,
		@RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
		Article updatedArticle = articleCommandService.updateArticle(articleId, dto);
		return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(updatedArticle));
	}

	@DeleteMapping("/articles/{articleId}")
	@Operation(summary = "게시글 삭제 API", description = "게시글을 삭제하는 API")
	public CustomResponse<String> deleteArticle(@PathVariable("articleId") Long articleId) {
		articleCommandService.deleteArticle(articleId);
		return CustomResponse.onSuccess("게시글이 성공적으로 삭제되었습니다.");
	}

	// 오프셋 기반 페이지네이션: 생성 날짜 기준으로 페이지네이션
	@GetMapping("/articles/paginated")
	@Operation(summary = "게시글 페이지네이션 조회 API", description = "오프셋 기반으로 게시글 목록을 페이지네이션하여 조회합니다.")
	public CustomResponse<Page<ArticleResponseDTO.ArticlePreviewDTO>> getArticlesWithPagination(
		@RequestParam("page") int page,
		@RequestParam("size") int size) {
		Page<Article> articles = articleQueryService.getArticlesWithPagination(page, size);
		return CustomResponse.onSuccess(articles.map(ArticleResponseDTO.ArticlePreviewDTO::from));
	}
}
