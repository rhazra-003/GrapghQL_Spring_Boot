package com.practice.graphql.paintings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.graphql.paintings.entity.Painting;

public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findByTitleContainingIgnoreCase(String title);
}