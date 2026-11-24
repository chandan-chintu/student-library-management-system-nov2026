package com.example.student_library_management_system.service;

import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.responsedto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponseDto saveStudent(StudentRequestDto studentRequestDto){
        // convert the request dto into model class
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setSem(studentRequestDto.getSem());
        student.setDob(studentRequestDto.getDob());
        student.setAddress(studentRequestDto.getAddress());
        student.setGender(studentRequestDto.getGender());
        student.setEmail(studentRequestDto.getEmail());
        student.setDept(studentRequestDto.getDept());
        student.setMobile(studentRequestDto.getMobile());

        // whenever student adds, card also gets added as part of cascading
        Card card = new Card();
        card.setCardStatus("Active");
        card.setExpiryDate(LocalDate.now().plusYears(3).toString());
        student.setCard(card);

        card.setStudent(student);

        student = studentRepository.save(student);

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setMessage("Student saved successfully!");
        studentResponseDto.setSavedStudent(student);
        return studentResponseDto;
    }

    public Student findStudentById(long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else {
            throw new RuntimeException("Student with id : "+id +" not found!");
        }
    }

    public List<Student> findAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public String deleteStudentById(long id){
        Student existingStudent = findStudentById(id);
        if(existingStudent!=null){
            studentRepository.deleteById(id);
            return "Student with id :"+id+" got deleted successfully!";
        } else {
            throw new RuntimeException("Student with id : "+id +" not found!");
        }
    }
}
