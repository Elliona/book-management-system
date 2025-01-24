package com.regiocom.book_management_system.controller;

import com.regiocom.book_management_system.dto.RelationDTO;
import com.regiocom.book_management_system.service.RelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relation")
public class RelationController {
    private final RelationService relationService;

    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    @PostMapping
    public ResponseEntity<RelationDTO> createRelation(@RequestBody RelationDTO relationDTO) {
        RelationDTO updateRelations = relationService.manageRelation(relationDTO);
        return ResponseEntity.ok(updateRelations);
    }
//
//    @PostMapping("/publisher")
//    public String addBookPublisher(@RequestParam Long publisherId, @RequestParam Long bookId) {
//        try {
//            relationService.addPublisherToBook(publisherId, bookId);
//            return "Publisher added to Book successfully!";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//
//    @PostMapping("/series")
//    public String addBookSeries(@RequestParam Long seriesId, @RequestParam Long bookId) {
//        try {
//            relationService.addSeriesToBook(seriesId, bookId);
//            return "Series added to Book successfully!";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }

    @DeleteMapping
    public ResponseEntity<RelationDTO> deleteRelation(@RequestBody RelationDTO relationDTO) {
        RelationDTO updateRelations = relationService.deleteRelation(relationDTO);
        return ResponseEntity.ok(updateRelations);
    }
//    @DeleteMapping("/author")
//    public String deleteBookAuthor(@RequestParam Long authorId, @RequestParam Long bookId) {
//        try {
//            relationService.deleteAuthorFromBook(authorId, bookId);
//            return "Author deleted from Book successfully!";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//
//    @DeleteMapping("/publisher")
//    public String deleteBookPublisher(@RequestParam Long publisherId, @RequestParam Long bookId) {
//        try {
//            relationService.deletePublisherFromBook(publisherId, bookId);
//            return "Publisher deleted from Book successfully!";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//
//    @DeleteMapping("/series")
//    public String deleteBookSeries(@RequestParam Long seriesId, @RequestParam Long bookId) {
//        try {
//            relationService.deleteSeriesFromBook(seriesId, bookId);
//            return "Series deleted from Book successfully!";
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }

}
