package com.idquantique.quantis.controller;

import com.idquantique.quantis.QuantisException;
import com.idquantique.quantis.service.LoginService;
import com.idquantique.quantis.service.OTPService;
import com.idquantique.quantis.student.OtpResetTask;
import com.idquantique.quantis.student.ResponseData;
import com.idquantique.quantis.student.Student;
import com.idquantique.quantis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    private final OTPService otpService;

    private final LoginService loginService;


    @Autowired
    public StudentController(StudentService studentService, OTPService otpService, LoginService loginService) {
        this.studentService = studentService;
        this.otpService = otpService;
        this.loginService =loginService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestBody(required = false) Student student) {
        studentService.updateStudent(studentId, student.getName(), student.getEmail());
    }

    @GetMapping(path = "otp")
    public String getOtp() throws QuantisException {
       return otpService.generateOTP();
    }


    @PostMapping(path="login")
    public ResponseEntity<ResponseData> login(@RequestBody(required = true) Student student) throws QuantisException {
        //Initialize object called responseData
        ResponseData responseData = new ResponseData();

        //initialize response
        HashMap<String, String> response = loginService.login(student.getEmail(), student.getPassword());

        //get http status
        HttpStatus httpStatus = HttpStatus.OK;

        // set response body by params
        responseData.setMessage(response.get("message"));
        responseData.setOtp(response.get("otp"));
        student.setOtp(response.get("otp"));
        responseData.setStatus(String.valueOf(httpStatus));

        return new ResponseEntity<>(responseData, httpStatus);
    }

    @PostMapping(path="register")
    public void signup(@RequestBody(required = true) Student student){
        loginService.signup(student);

    }




}

