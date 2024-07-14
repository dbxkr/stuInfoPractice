package kr.bit.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private long stuId;
    private String name;
    private String address;
    private String school;
    private String major;
    private long classId;
}
