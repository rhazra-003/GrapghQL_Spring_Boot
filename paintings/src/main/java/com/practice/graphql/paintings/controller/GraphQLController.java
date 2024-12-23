package com.practice.graphql.paintings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.practice.graphql.paintings.entity.Painter;
import com.practice.graphql.paintings.entity.Painting;
import com.practice.graphql.paintings.repository.PainterRepository;
import com.practice.graphql.paintings.repository.PaintingRepository;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private PainterRepository painterRepository;

    @Autowired
    private PaintingRepository paintingRepository;

    @QueryMapping
    public List<Painter> painters() {
        return painterRepository.findAll();
    }

    @QueryMapping
    public List<Painting> paintings() {
        return paintingRepository.findAll();
    }

    @QueryMapping
    public List<Painting> searchPaintingsByTitle(@Argument String title) {
        return paintingRepository.findByTitleContainingIgnoreCase(title);
}

    @MutationMapping
    public Painter addPainter(@Argument String name, @Argument String country) {
        Painter painter = new Painter();
        painter.setName(name);
        painter.setCountry(country);
        return painterRepository.save(painter);
    }

    @MutationMapping
    public Painting addPainting(@Argument String title, @Argument Long painterId) {
        Painter painter = painterRepository.findById(painterId).orElseThrow(() -> new RuntimeException("Painter not found"));
        Painting painting = new Painting();
        painting.setTitle(title);
        painting.setPainter(painter);
        return paintingRepository.save(painting);
    }

    @MutationMapping
    public Painter updatePainter(@Argument Long id, @Argument String name) {
        Painter painter = painterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Painter with ID " + id + " not found"));
        painter.setName(name);
        return painterRepository.save(painter);
    }

    @MutationMapping
    public Painting deletePainting(@Argument Long id) {
        Painting painting = paintingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Painting with ID " + id + " not found"));
        paintingRepository.delete(painting);
        return painting;
    }

}