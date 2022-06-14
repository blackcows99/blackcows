package com.hanghae99.blackcows.repositories;

import com.hanghae99.blackcows.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("Select this_ from Posts this_ join this_.comment as c where c.id=:id")
    public Posts findByCommentId(@Param("id") long commnetid);
}
