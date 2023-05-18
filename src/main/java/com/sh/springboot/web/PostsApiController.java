package com.sh.springboot.web;

import com.sh.springboot.posts.Posts;
import com.sh.springboot.web.dto.PostsListResponseDto;
import com.sh.springboot.web.dto.PostsResponseDto;
import com.sh.springboot.web.dto.PostsSaveRequestDto;
import com.sh.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sh.springboot.posts.PostsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts") //게시판 등록
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);

    }
    @PutMapping("/api/v1/posts/{id}") // 게시판 수정
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}") // 게시판 상세조회
    public PostsResponseDto findById (@PathVariable Long id){
        return  postsService.findById(id);
    }

    @GetMapping("/api/v1/all") // 모든 게시판 조회
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }

    @DeleteMapping("/api/v1/posts/{id}") // 게시판 삭제
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/titleSearch/{title}") // 게시판 제목 검색
    public List<Posts> findByTitleContaining (@PathVariable String title) {
        return postsService.findByTitleContaining(title);
    }

    @GetMapping("/api/v1/contentSearch/{content}") // 게시판 내용 검색
    public List<Posts> findByContentContaining (@PathVariable String content) {
        return postsService.findByContentContaining(content);
    }

    @GetMapping("/api/v1/authorSearch/{author}") // 게시판 작성자 검색
    public List<Posts> findByAuthorContaining (@PathVariable String author) {
        return postsService.findByAuthorContaining(author);
    }
}
