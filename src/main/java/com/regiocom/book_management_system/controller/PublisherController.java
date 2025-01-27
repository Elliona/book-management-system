package com.regiocom.book_management_system.controller;

import com.regiocom.book_management_system.dto.PublisherDTO;
import com.regiocom.book_management_system.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/search")
    public List<PublisherDTO> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/search/{publisherId}")
    public PublisherDTO getPublisherById(@PathVariable long publisherId) {
        return publisherService.getPublisherById(publisherId);
    }

    @PostMapping("/new")
    public PublisherDTO savePublisher(@RequestBody PublisherDTO publisherDTO) {
        return publisherService.savePublisher(publisherDTO);
    }

    @PatchMapping("/edit/{publisherId}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable long publisherId, @RequestBody PublisherDTO publisherDTO) {
        PublisherDTO updatedPublisher = publisherService.updatePublisher(publisherId, publisherDTO);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/delete/{publisherId}")
    public void deletePublisher(@PathVariable long publisherId) {
        publisherService.deletePublisher(publisherId);
    }

}
