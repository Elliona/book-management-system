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

    @DeleteMapping
    public ResponseEntity<RelationDTO> deleteRelation(@RequestBody RelationDTO relationDTO) {
        RelationDTO updateRelations = relationService.deleteRelation(relationDTO);
        return ResponseEntity.ok(updateRelations);
    }

}
