package com.example.demo.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Exception.BusinessException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Request.AddStudentRequest;
import com.example.demo.Response.StudentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class StudentService {

    private StudentRepository studentRepository;
    private UserRepository userRepository;

    public StudentResponse getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()
        -> new BusinessException(ErrorCode.resource_missing,studentId + "numarali ogrenci bulunamadi"));
        return new StudentResponse(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId).orElseThrow(()
                -> new BusinessException(ErrorCode.resource_missing,studentId + "numarali ogrenci bulunamadi"));
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Long studentId, String studentNo, String department) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (!student.isPresent()) {
            throw new BusinessException(ErrorCode.resource_missing,studentId + "numarali ogrenci bulunamadi"); }
        if (studentNo.isEmpty() || department.isEmpty()) {
            throw new BusinessException(ErrorCode.resource_missing,"yetersiz bilgi!");}

        Student newStudent = student.get();
        newStudent.setStudentNo(studentNo);
        newStudent.setDepartment(department);
        studentRepository.save(newStudent);
    }

    public void addStudent(AddStudentRequest student, Long authenticatedUserId) {
        userRepository.findById(authenticatedUserId).orElseThrow(()
                -> new BusinessException(ErrorCode.account_missing, "Kullanıcı bulunamadı"));
        if (student.getStudentNo().isEmpty() || student.getDepartment().isEmpty()) {
            throw new BusinessException(ErrorCode.resource_missing,"Missing information!");
        }

        Student newStudent = new Student();
        newStudent.setStudentNo(student.getStudentNo());
        newStudent.setDepartment(student.getDepartment());

        studentRepository.save(newStudent);
    }
}
