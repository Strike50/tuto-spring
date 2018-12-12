package com.gfi.springit.service;

import com.gfi.springit.domain.Comment;
import com.gfi.springit.domain.Link;
import com.gfi.springit.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }

    public List<Comment> findByLink(Link link){
        return commentRepository.findByLink(link);
    }
}
