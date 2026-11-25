package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/findAllByPage")
    public ResponseEntity<?> findAllStudentsByPage(@RequestParam int pageNo, @RequestParam int pageSize){
        try {
            Page<Student> studentPage = studentService.getStudentByPage(pageNo, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(studentPage);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }

    @GetMapping("/findAllByPageAndSort")
    public ResponseEntity<?> findAllStudentsByPageAndSort(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortByField, @RequestParam String orderBy){
        try {
            Page<Student> studentPage = studentService.getStudentByPageAndSort(pageNo, pageSize, sortByField, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(studentPage);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudentUsingPut(@PathVariable int id, @RequestBody StudentRequestDto studentRequestDto){
        try {
            String response = studentService.updateStudentUsingPut(id, studentRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findStudentByEmail(@RequestParam String email){
        try {
            Student student = studentService.getStudentByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }

    @GetMapping("/findByDept")
    public ResponseEntity<?> findStudentsByDept(@RequestParam String dept){
        try {
            List<Student> studentList = studentService.getStudentByDept(dept);
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred : "+e.getMessage()+"---"+e.getClass());
        }
    }
}
