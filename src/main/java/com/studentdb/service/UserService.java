package com.studentdb.service;

import com.studentdb.entity.User;
import com.studentdb.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public User register(User u) {
        if (userRepository.existsByUsername(u.getUsername())) throw new RuntimeException("用户名已存在");
        return userRepository.save(u);
    }
    public User login(String username, String password) {
        User u = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("用户不存在"));
        if (!u.getPassword().equals(password)) throw new RuntimeException("密码错误");
        return u;
    }
    @Transactional(readOnly = true)
    public List<User> findAll() { return userRepository.findAll(); }
    @Transactional(readOnly = true)
    public User findById(Long id) { return userRepository.findById(id).orElseThrow(() -> new RuntimeException("用户不存在: " + id)); }
    public void delete(Long id) { userRepository.deleteById(id); }
}
