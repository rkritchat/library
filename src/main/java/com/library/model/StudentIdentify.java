package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student_identify")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentIdentify {
    @Id
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private int id;
    private String username;
    private String password;
    private String role;
}
