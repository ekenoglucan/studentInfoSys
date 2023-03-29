package com.example.demo.Response;

import com.example.demo.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponse {

    private Long Id;
    private String studentNo;
    private String department;

    public StudentResponse(Student student) {
        this.Id = student.getId();
        this.studentNo = student.getStudentNo();
        this.department = student.getDepartment();
    }
}
