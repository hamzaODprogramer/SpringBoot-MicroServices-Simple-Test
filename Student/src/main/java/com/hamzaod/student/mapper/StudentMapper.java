package com.hamzaod.student.mapper;

import com.hamzaod.student.dto.StudentRequest;
import com.hamzaod.student.dto.StudentResponse;
import com.hamzaod.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        if (request == null) {
            return null;
        }
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setBirthDate(request.getBirthDate());
        student.setMajor(request.getMajor());
        student.setSchoolId(request.getSchoolId());
        return student;
    }

    public StudentResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate(),
                student.getMajor(),
                student.getSchoolId()
        );
    }
}
