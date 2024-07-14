package kr.bit.student.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuId;

    private String name;
    private String address;
    private String school;
    private String major;

    @JoinColumn(name = "classId")
    @ManyToOne(fetch = FetchType.EAGER)
    private StuClass stuClass;

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", stuClassId=" + (stuClass != null ? stuClass.getClassId() : null) +
                '}';
    }
}
