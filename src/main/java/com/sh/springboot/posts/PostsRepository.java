package com.sh.springboot.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostsRepository extends JpaRepository <Posts, Long> {
    @Query ("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    List<Posts> findByTitleContaining(String searchKeyword); //제목 검색
    List<Posts> findByContentContaining(String searchKeyword); // 내용 검색
    List<Posts> findByAuthorContaining(String searchKeyword); // 작성자 검색

}
