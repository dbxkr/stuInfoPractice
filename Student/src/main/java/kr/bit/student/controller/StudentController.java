package kr.bit.student.controller;

import kr.bit.student.dto.StudentDTO;
import kr.bit.student.entity.StuClass;
import kr.bit.student.entity.Student;
import kr.bit.student.repository.StuClassRepository;
import kr.bit.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/student/info")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StuClassRepository stuClassRepository;

    @PostMapping("/list")
    public List<StudentDTO> getStudents(@RequestBody Map<String, String> map) {
        return studentRepository.findByNameContainingOrderByName(map.get("name"));
    }

    @PostMapping("/class")
    public List<StudentDTO> getStudentsByClass(@RequestBody Map<String, Long> map) {
        return studentRepository.findStudentByStuClassOrderByName(map.get("class"));
    }

    @PostMapping("/addinfo")
    public Map<String, String> saveStudentInfo(@RequestBody StudentDTO studentDTO) {
        Map<String, String> data = new HashMap<>();
        System.out.println(studentDTO);
        StuClass stuClass = stuClassRepository.findByClassId(studentDTO.getClassId());
        if(stuClass == null) {
            data.put("msg", "그런 반은 없다 애송이");
            return data;
        }
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setMajor(studentDTO.getMajor());
        student.setSchool(studentDTO.getSchool());
        student.setStuClass(stuClass);
        if(studentDTO.getStuId() != 0){
            student.setStuId(studentDTO.getStuId());
        }
        studentRepository.save(student);
        data.put("msg","성공적으로 반영되었습니다.");
        return data;
    }


}
