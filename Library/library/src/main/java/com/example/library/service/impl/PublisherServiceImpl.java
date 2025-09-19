package com.example.library.service.impl;

import com.example.library.dto.PublisherDto;
import com.example.library.mapper.PublisherMapper;
import com.example.library.model.Book;
import com.example.library.model.Publisher;
import com.example.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.repo.PublisherRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    public PublisherDto addPublisher(PublisherDto dto) {
        Publisher publisher = PublisherMapper.PUBLISHER_MAPPER.toEntity(dto);
        return PublisherMapper.PUBLISHER_MAPPER.toDto(publisherRepository.save(publisher));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("error.idNotFound"));

        for (Book book : publisher.getBooks()) {
            book.setPublisher(null);
        }

        publisherRepository.delete(publisher);
    }

    @Override
    public PublisherDto updatePublisher(Long id, PublisherDto dto) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        publisher.setName(dto.getName());
        publisher.setAddress(dto.getAddress());
        publisher.setWebsite(dto.getWebsite());
        return PublisherMapper.PUBLISHER_MAPPER.toDto(publisherRepository.save(publisher));
    }

    @Override
    public List<PublisherDto> getAll() {
        return publisherRepository.findAll().stream().map(PublisherMapper.PUBLISHER_MAPPER::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PublisherDto> getByName(String name) {
        return publisherRepository.findByNameContainingIgnoreCase(name)
                .stream().map(PublisherMapper.PUBLISHER_MAPPER::toDto).collect(Collectors.toList());
    }
}
