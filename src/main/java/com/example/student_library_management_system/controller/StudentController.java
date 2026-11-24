package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {

    /*
User request (either postman or UI) -> dispatcher servlet ->
corresponding API (controller class based on endpoint or url) ->
service (business logic) -> repository (to perform various database operations)
 */

    @Autowired
    StudentService studentService;

    //standard way for returning response - ResponseEntity (it contains http status code and response body)

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        try {
            StudentResponseDto studentResponseDto = studentService.saveStudent(studentRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable long id){
        try {
            Student student = studentService.findStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllStudents(){
        try {
            List<Student> studentList = studentService.findAllStudents();
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable long id){
        try {
            String response = studentService.deleteStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }
}
