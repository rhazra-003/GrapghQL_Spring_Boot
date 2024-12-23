package com.practice.graphql.paintings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.graphql.paintings.entity.Painter;

public interface PainterRepository extends JpaRepository<Painter, Long> {

}