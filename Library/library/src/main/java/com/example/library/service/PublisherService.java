package com.example.library.service;

import com.example.library.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
     PublisherDto addPublisher(PublisherDto dto);
     void deleteById(Long id);
     PublisherDto updatePublisher(Long id, PublisherDto dto);
     List<PublisherDto> getAll();
     List<PublisherDto> getByName(String name);
}
