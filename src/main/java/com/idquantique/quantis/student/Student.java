package com.idquantique.quantis.student;

import com.idquantique.quantis.tools.HashTool;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private String password;
    private String otp;
    @Transient
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob, String password, String otp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.otp = otp;
    }

    public Student(String name, String email, LocalDate dob, String password, String otp) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.otp = otp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public String getPassword() {
        String hashedPassword = HashTool.hashString(password);
        return hashedPassword; }

    public void setPassword(String password) {
        String hashedPassword = HashTool.hashString(password);
        this.password = hashedPassword;
    }

    public String getOtp(){
        String hashedOtp = HashTool.hashString(otp);
        return hashedOtp;}

    public void setOtp(String otp){
        String hashedOtp = HashTool.hashString(otp);
        this.otp = hashedOtp;}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", otp=' " + otp +'\''+
                ", age=" + age +
                '}';
    }
}
