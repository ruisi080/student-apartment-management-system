package com.studentdb;

import com.studentdb.entity.User;
import com.studentdb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("123456");
            admin.setRole(User.Role.ADMIN);
            admin.setName("管理员");
            userRepository.save(admin);

            User student = new User();
            student.setUsername("student");
            student.setPassword("123456");
            student.setRole(User.Role.STUDENT);
            student.setName("学生用户");
            userRepository.save(student);

            User repair = new User();
            repair.setUsername("repair");
            repair.setPassword("123456");
            repair.setRole(User.Role.MAINTENANCE);
            repair.setName("维修员");
            userRepository.save(repair);

            System.out.println("=== 初始账号已创建: admin/student/repair，密码均为 123456 ===");
        }
    }
}
