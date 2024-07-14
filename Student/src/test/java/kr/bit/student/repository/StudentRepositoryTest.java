package kr.bit.student.repository;

import kr.bit.student.dto.StudentDTO;
import kr.bit.student.entity.StuClass;
import kr.bit.student.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StuClassRepository stuClassRepository;

    @Test
    public void findAll() {
        List<Student> students = studentRepository.findAll();
    }

    @Test
    public void testInsert(){
        Random random = new Random();
        IntStream.rangeClosed(1,100).forEach(i->{
            StuClass stuClass = stuClassRepository.findByClassId(random.nextLong(10)+1);
            Student student = Student.builder().major("major"+i).name("name"+i).address("address"+i).school("school"+i).stuClass(stuClass).build();
            studentRepository.save(student);
        });
    }

    @Test
    public void findByStuClassId(){
        long classId = 1;
        List<Student> students = studentRepository.findByStuClass_ClassId(classId);
        students.forEach(System.out::println);
    }

    @Test
    public void findByStudentName(){
        List<StudentDTO> students = studentRepository.findByNameContainingOrderByName("1");
        students.forEach(System.out::println);
    }
}
