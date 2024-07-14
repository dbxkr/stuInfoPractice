package kr.bit.student.repository;

import kr.bit.student.entity.StuClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class StuClassRepositoryTest {
    @Autowired
    private StuClassRepository repository;

    @Test
    public void findAll() {
        List<StuClass> all = repository.findAll();
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,10).forEach(i->{
            StuClass stuClass = StuClass.builder().className("class"+i).build();
            repository.save(stuClass);
        });
    }
}
