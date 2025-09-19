package com.example.library.controller;

import com.example.library.dto.PublisherDto;
import com.example.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @PostMapping("/addPublisher")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<PublisherDto> addPublisher(@RequestBody PublisherDto dto) {
        return ResponseEntity.ok(publisherService.addPublisher(dto));
    }

    @DeleteMapping("/deletePublisherById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletePublisherById(@PathVariable Long id) {
        publisherService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatePublisherById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PublisherDto> updatePublisherById(@PathVariable Long id, @RequestBody PublisherDto dto) {
        return ResponseEntity.ok(publisherService.updatePublisher(id, dto));
    }

    @GetMapping("/getAllPublishers")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<List<PublisherDto>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAll());
    }

    @GetMapping("/getPublishersByName/search")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<List<PublisherDto>> getPublishersByName(@RequestParam String name) {
        return ResponseEntity.ok(publisherService.getByName(name));
    }
}
