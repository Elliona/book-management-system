package com.regiocom.book_management_system.controller;

import com.regiocom.book_management_system.dto.SeriesDTO;
import com.regiocom.book_management_system.service.SeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping
    public List<SeriesDTO> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/{seriesId}")
    public SeriesDTO getSeries(@PathVariable Long seriesId) {
        return seriesService.getSeriesById(seriesId);
    }

    @PostMapping
    public SeriesDTO addSeries(@RequestBody SeriesDTO seriesDTO) {
        return seriesService.saveSeries(seriesDTO);
    }

    @PatchMapping("/{seriesId}")
    public ResponseEntity<SeriesDTO> updateSeries(@PathVariable Long seriesId, @RequestBody SeriesDTO seriesDTO) {
        SeriesDTO updateSeries = seriesService.updateSeries(seriesId, seriesDTO);
        return ResponseEntity.ok(updateSeries);
    }

    @DeleteMapping("/{seriesId}")
    public void deleteSeries(@PathVariable Long seriesId) {
        seriesService.deleteSeries(seriesId);
    }

}
