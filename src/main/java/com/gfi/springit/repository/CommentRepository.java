package com.gfi.springit.repository;

import com.gfi.springit.domain.Comment;
import com.gfi.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByLink(Link link);
}
