package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Admin;
import com.quiz.quizapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin login(String username, String password) {
        return adminRepository
                .findByUsernameAndPassword(username, password)
                .orElse(null);
    }
}
