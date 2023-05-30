package com.sh.springboot.posts;

import com.sh.springboot.web.dto.PostsListResponseDto;
import com.sh.springboot.web.dto.PostsResponseDto;
import com.sh.springboot.web.dto.PostsSaveRequestDto;
import com.sh.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    } // 저장

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    } // 수정
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    } // 상세조회

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    } // 전체 조회

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    } // 삭제

    @Transactional
    public List<Posts> findByTitleContaining (String title) {
        return postsRepository.findByTitleContaining(title);
    }

    @Transactional
    public List<Posts> findByContentContaining (String content){
        return postsRepository.findByContentContaining(content);
    }

    @Transactional
    public List<Posts> findByAuthorContaining(String author) {
        return postsRepository.findByAuthorContaining(author);
    }
}

