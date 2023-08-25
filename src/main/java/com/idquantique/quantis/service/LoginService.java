package com.idquantique.quantis.service;

import com.idquantique.quantis.Quantis;
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
                replyToQuery.put("otp", String.format("%06d", positive_rand));
                replyToQuery.put("message", "valid login");
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

}
