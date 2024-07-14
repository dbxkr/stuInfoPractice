package kr.bit.student.repository;

import kr.bit.student.entity.StuClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuClassRepository extends JpaRepository<StuClass, Integer> {
    StuClass findByClassId(long classId);
}
