package com.example.demo.Controller;

import com.example.demo.Request.AddStudentRequest;
import com.example.demo.Request.UpdateStudentRequest;
import com.example.demo.Response.StudentResponse;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    private UserService userService;

    @PostMapping
    public void addStudent(@RequestBody AddStudentRequest student) { studentService.addStudent(student, userService.getAuthenticatedUserId()) ;}

    @GetMapping("/{studentId}")
    public StudentResponse getStudent(@PathVariable Long studentId) { return studentService.getStudent(studentId);}

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) { studentService.deleteStudent(studentId);}

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody UpdateStudentRequest studentUpdateRequest) {
        studentService.updateStudent(studentId, studentUpdateRequest.getStudentNo(), studentUpdateRequest.getDepartment());}
}
