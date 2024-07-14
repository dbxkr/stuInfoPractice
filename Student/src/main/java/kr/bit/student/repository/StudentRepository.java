package kr.bit.student.repository;

import kr.bit.student.dto.StudentDTO;
import kr.bit.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStuClass_ClassId(Long classId);
//    List<Student> findByNameContainingOrderByName(String name);

    @Query("SELECT new kr.bit.student.dto.StudentDTO(s.stuId, s.name, s.address, s.school, s.major, s.stuClass.classId) FROM Student s WHERE s.name LIKE %:name% order by s.name")
    List<StudentDTO> findByNameContainingOrderByName(String name);

    @Query("SELECT new kr.bit.student.dto.StudentDTO(s.stuId, s.name, s.address, s.school, s.major, s.stuClass.classId) FROM Student s WHERE s.stuClass.classId = :classId order by s.name")
    List<StudentDTO> findStudentByStuClassOrderByName(Long classId);
}
