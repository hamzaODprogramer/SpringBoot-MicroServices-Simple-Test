package com.hamzaod.student.service;

import com.hamzaod.student.dto.StudentRequest;
import com.hamzaod.student.dto.StudentResponse;
import com.hamzaod.student.exception.EmailAlreadyExistsException;
import com.hamzaod.student.exception.StudentNotFoundException;
import com.hamzaod.student.mapper.StudentMapper;
import com.hamzaod.student.model.Student;
import com.hamzaod.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return mapper.toResponse(student);
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        Student student = mapper.toEntity(request);
        Student saved = repository.save(student);
        return mapper.toResponse(saved);
    }

    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        if (!student.getEmail().equals(request.getEmail()) 
                && repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setBirthDate(request.getBirthDate());
        student.setMajor(request.getMajor());
        student.setSchoolId(request.getSchoolId());

        Student updated = repository.save(student);
        return mapper.toResponse(updated);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsBySchoolId(Long schoolId) {
        return repository.findBySchoolId(schoolId).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
