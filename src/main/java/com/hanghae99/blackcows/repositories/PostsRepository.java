package com.hanghae99.blackcows.repositories;

import com.hanghae99.blackcows.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
