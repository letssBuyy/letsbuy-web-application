package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUrl(String url);
    Boolean existsByUrl(String url);
}
