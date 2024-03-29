package com.idquantique.quantis.service;

import com.idquantique.quantis.Quantis;
import com.idquantique.quantis.QuantisException;
import com.idquantique.quantis.repository.LoginRepository;
import com.idquantique.quantis.repository.StudentRepository;
import com.idquantique.quantis.student.OtpResetTask;
import com.idquantique.quantis.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.Timer;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository, StudentRepository studentRepository) {this.loginRepository = loginRepository; this.studentRepository = studentRepository;}


    public HashMap<String, String> login(String email, String password) throws QuantisException {
        // Instantiate optional class that finds student object by email
        Optional<Student> studentOptional = loginRepository.findStudentByEmail(email);

        // Object that instantiates response to login query
        HashMap<String, String> replyToQuery = new HashMap<String, String>();

        // check for user in database using email
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();
            String savedPassword = student.getPassword();

            // if the password is correct decision block
            if (savedPassword.equals(password)){
                Quantis quantis = new Quantis(Quantis.QuantisDeviceType.QUANTIS_DEVICE_PCI, 0);
                Integer positive_rand = Math.abs(quantis.ReadInt());
                replyToQuery.put("otp", "OTP GENERATED!!");
                replyToQuery.put("message", "valid login");

                System.out.println("LOGIN SUCCESS DATA: " + student);
                student.setOtp(String.format("%06d", positive_rand));
                System.out.println("LOGIN SUCCESS DATA 2: " + student);
                studentRepository.save(student);

                startTimerToResetOtp(student.getEmail());
                }
            else{ replyToQuery.put("message", "invalid password");
                }
            return replyToQuery;
        }

        replyToQuery.put("message", "email doesn't match records");
        return replyToQuery;
    }

    public void signup(Student student){
        Optional<Student> studentOptional = loginRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        loginRepository.save(student);
    }

    private void startTimerToResetOtp(String email){
        Timer timer = new Timer();
        timer.schedule(new OtpResetTask(studentRepository, email), 5 * 60 * 1000);
    }

    public void validateOtp(Student student){
        System.out.println("OTP VALIDATION: " + student);
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent() && student.getOtp().equals(studentOptional.get().getOtp())){
            System.out.println("IT WORKS!!!!");
            System.out.println("OTP" + student);
        }
        else{
            System.out.println("IT DON'T WORK!");
        }

//        if (user != null && student.getOtp().equals(user.get())) {
//            user.setOtp(null); // Reset OTP to null
//            userRepository.save(user);
//            return ResponseEntity.ok("Pass");
//        } else {
//            return ResponseEntity.badRequest().body("Invalid OTP");
//        }
    }
}
