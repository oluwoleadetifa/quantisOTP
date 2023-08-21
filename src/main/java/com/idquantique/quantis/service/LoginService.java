package com.idquantique.quantis.service;

import com.idquantique.quantis.QuantisException;
import com.idquantique.quantis.repository.LoginRepository;
import com.idquantique.quantis.repository.StudentRepository;
import com.idquantique.quantis.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {this.loginRepository = loginRepository;}

    private OTPService otpService;

    public String login(String email, String password) throws QuantisException {
        Optional<Student> studentOptional = loginRepository.findStudentByEmail(email);
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();
            String savedPassword = student.getPassword();

            if (savedPassword.equals(password)){
                String response = otpService.generateOTP();
                return "valid login";}
            else{ return "invalid password";}
        }
        return "email doesn't match records";
    }

    public void signup(Student student){
        Optional<Student> studentOptional = loginRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        loginRepository.save(student);
    }

}
