package com.regiocom.book_management_system.service;

import com.regiocom.book_management_system.dto.SeriesDTO;
import com.regiocom.book_management_system.mapper.SeriesMapper;
import com.regiocom.book_management_system.model.Series;
import com.regiocom.book_management_system.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;

    public SeriesService(SeriesRepository seriesRepository, SeriesMapper seriesMapper) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
    }

    public List<SeriesDTO> getAllSeries() {
        return seriesRepository.findAll().stream()
                .map(seriesMapper::seriesToSeriesDTO)
                .collect(Collectors.toList());
    }

    public SeriesDTO getSeriesById(Long seriesId) {
        return seriesRepository.findById(seriesId)
                .map(seriesMapper::seriesToSeriesDTO)
                .orElseThrow(() -> new RuntimeException("Series not found"));
    }

    public SeriesDTO saveSeries(SeriesDTO seriesDTO) {
        Series series = seriesMapper.seriesDTOToSeries(seriesDTO);
        Series savedSeries = seriesRepository.save(series);
        return seriesMapper.seriesToSeriesDTO(savedSeries);
    }

    public SeriesDTO updateSeries(Long seriesId, SeriesDTO seriesDTO) {
        Series existingSeries = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        seriesMapper.updateSeriesFromDTO(seriesDTO, existingSeries);
        Series updatedSeries = seriesRepository.save(existingSeries);
        return seriesMapper.seriesToSeriesDTO(updatedSeries);
    }

    public void deleteSeries(Long seriesId) {
        seriesRepository.deleteById(seriesId);
    }

}
