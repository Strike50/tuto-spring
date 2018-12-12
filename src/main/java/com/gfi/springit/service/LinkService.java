package com.gfi.springit.service;

import com.gfi.springit.domain.Link;
import com.gfi.springit.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link save(Link link){
        return linkRepository.save(link);
    }

    public List<Link> findAll(){
        return linkRepository.findAll();

    }

    public Optional<Link> findById(Long id){
        return linkRepository.findById(id);
    }
}
