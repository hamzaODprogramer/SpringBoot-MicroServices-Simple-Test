package com.hamzaod.school.service;

import com.hamzaod.school.dto.SchoolRequest;
import com.hamzaod.school.dto.SchoolResponse;
import com.hamzaod.school.dto.SchoolWithStudentsResponse;
import com.hamzaod.school.exception.EmailAlreadyExistsException;
import com.hamzaod.school.exception.SchoolNotFoundException;
import com.hamzaod.school.mapper.SchoolMapper;
import com.hamzaod.school.model.School;
import com.hamzaod.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;
    private final SchoolMapper mapper;
    private final RestTemplate restTemplate;

    private static final String STUDENT_SERVICE_URL = "http://localhost:8090";

    @Transactional(readOnly = true)
    public List<SchoolResponse> getAllSchools() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public SchoolResponse getSchoolById(Long id) {
        School school = repository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException("School not found with id: " + id));
        return mapper.toResponse(school);
    }

    @Transactional(readOnly = true)
    public SchoolWithStudentsResponse getSchoolWithStudents(Long id) {
        School school = repository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException("School not found with id: " + id));

        ResponseEntity<List<SchoolWithStudentsResponse.StudentSummary>> response = restTemplate.exchange(
                STUDENT_SERVICE_URL + "/api/students/school/{schoolId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SchoolWithStudentsResponse.StudentSummary>>() {},
                id
        );

        List<SchoolWithStudentsResponse.StudentSummary> students = response.getBody();

        return new SchoolWithStudentsResponse(
                school.getId(),
                school.getName(),
                school.getAddress(),
                school.getEmail(),
                school.getPhone(),
                students
        );
    }

    @Transactional
    public SchoolResponse createSchool(SchoolRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        School school = mapper.toEntity(request);
        School saved = repository.save(school);
        return mapper.toResponse(saved);
    }

    @Transactional
    public SchoolResponse updateSchool(Long id, SchoolRequest request) {
        School school = repository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException("School not found with id: " + id));

        if (!school.getEmail().equals(request.getEmail()) 
                && repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        school.setName(request.getName());
        school.setAddress(request.getAddress());
        school.setEmail(request.getEmail());
        school.setPhone(request.getPhone());

        School updated = repository.save(school);
        return mapper.toResponse(updated);
    }

    @Transactional
    public void deleteSchool(Long id) {
        if (!repository.existsById(id)) {
            throw new SchoolNotFoundException("School not found with id: " + id);
        }
        repository.deleteById(id);
    }

}
