package com.regiocom.book_management_system.service;

import com.regiocom.book_management_system.dto.PublisherDTO;
import com.regiocom.book_management_system.mapper.PublisherMapper;
import com.regiocom.book_management_system.model.Publisher;
import com.regiocom.book_management_system.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::publisherToPublisherDTO)
                .collect(Collectors.toList());
    }

    public PublisherDTO getPublisherById(Long publisherId) {
        return publisherRepository.findById(publisherId)
                .map(publisherMapper::publisherToPublisherDTO)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public PublisherDTO savePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.publisherDTOToPublisher(publisherDTO);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return publisherMapper.publisherToPublisherDTO(savedPublisher);
    }

    public PublisherDTO updatePublisher(Long publisherId, PublisherDTO publisherDTO) {
        Publisher existingPublisher = publisherRepository.findById(publisherId)
        .orElseThrow(() -> new RuntimeException("Publisher not found"));

        publisherMapper.updatePublisherFromDTO(publisherDTO, existingPublisher);
        Publisher updatedPublisher = publisherRepository.save(existingPublisher);
        return publisherMapper.publisherToPublisherDTO(updatedPublisher);
    }

    public void deletePublisher(Long publisherId) {
        publisherRepository.deleteById(publisherId);
    }

}
