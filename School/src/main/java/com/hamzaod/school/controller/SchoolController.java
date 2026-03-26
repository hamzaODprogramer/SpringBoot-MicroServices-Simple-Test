package com.hamzaod.school.controller;

import com.hamzaod.school.dto.SchoolRequest;
import com.hamzaod.school.dto.SchoolResponse;
import com.hamzaod.school.dto.SchoolWithStudentsResponse;
import com.hamzaod.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    @GetMapping
    public ResponseEntity<List<SchoolResponse>> getAllSchools() {
        return ResponseEntity.ok(service.getAllSchools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponse> getSchoolById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSchoolById(id));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<SchoolWithStudentsResponse> getSchoolWithStudents(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSchoolWithStudents(id));
    }

    @PostMapping
    public ResponseEntity<SchoolResponse> createSchool(@RequestBody SchoolRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSchool(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolResponse> updateSchool(@PathVariable Long id, @RequestBody SchoolRequest request) {
        return ResponseEntity.ok(service.updateSchool(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        service.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
