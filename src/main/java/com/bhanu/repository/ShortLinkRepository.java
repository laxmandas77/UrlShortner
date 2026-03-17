package com.bhanu.repository;

import com.bhanu.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortLinkRepository extends JpaRepository<ShortLink,Long> {

    Optional<ShortLink> findByShortCode(String code);

    List<ShortLink> findByUserId(Long userId);
}