package com.bhanu.service;

import com.bhanu.entity.ShortLink;
import com.bhanu.entity.User;
import com.bhanu.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ShortLinkService {

    @Autowired
    private ShortLinkRepository repository;

    public ShortLink createLink(String url, User user){

        String code = UUID.randomUUID().toString().substring(0,6);

        ShortLink link = new ShortLink();

        link.setOriginalUrl(url);
        link.setShortCode(code);
        link.setCreatedAt(LocalDateTime.now());
        link.setUser(user);

        return repository.save(link);
    }

    public List<ShortLink> getUserLinks(Long userId){

        return repository.findByUserId(userId);
    }

    public ShortLink updateLink(Long id, String alias, LocalDateTime expiry){

        ShortLink link = repository.findById(id).orElseThrow();

        link.setCustomAlias(alias);
        link.setExpiresAt(expiry);

        return repository.save(link);
    }

    public void deleteLink(Long id){

        repository.deleteById(id);
    }
}
