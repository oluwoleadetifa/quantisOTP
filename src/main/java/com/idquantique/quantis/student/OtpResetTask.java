package com.idquantique.quantis.student;

import com.idquantique.quantis.repository.StudentRepository;

import java.util.TimerTask;

public class OtpResetTask extends TimerTask {
    private StudentRepository studentRepository;
    private String email;

    public OtpResetTask(StudentRepository studentRepository, String email) {
        this.studentRepository = studentRepository;
        this.email = email;
    }

    @Override
    public void run() {
        Student user = studentRepository.findStudentByEmail(email).orElse(null);
        if (user != null) {
            user.setOtp(null);
            studentRepository.save(user);
        }
    }
}

