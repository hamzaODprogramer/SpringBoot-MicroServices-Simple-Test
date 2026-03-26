package com.hamzaod.school.mapper;

import com.hamzaod.school.dto.SchoolRequest;
import com.hamzaod.school.dto.SchoolResponse;
import com.hamzaod.school.model.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public School toEntity(SchoolRequest request) {
        if (request == null) {
            return null;
        }
        School school = new School();
        school.setName(request.getName());
        school.setAddress(request.getAddress());
        school.setEmail(request.getEmail());
        school.setPhone(request.getPhone());
        return school;
    }

    public SchoolResponse toResponse(School school) {
        if (school == null) {
            return null;
        }
        return new SchoolResponse(
                school.getId(),
                school.getName(),
                school.getAddress(),
                school.getEmail(),
                school.getPhone()
        );
    }
}
