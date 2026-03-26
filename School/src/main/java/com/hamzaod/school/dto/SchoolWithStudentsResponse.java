package com.hamzaod.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolWithStudentsResponse {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private List<StudentSummary> students;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentSummary {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
    }
}
