package com.studentdb.controller;

import com.studentdb.entity.User;
import com.studentdb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User u) { return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(u)); }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            User u = userService.login(body.get("username"), body.get("password"));
            return ResponseEntity.ok(Map.of("success", true, "user", u, "message", "登录成功"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll() { return ResponseEntity.ok(userService.findAll()); }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) { return ResponseEntity.ok(userService.findById(id)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { userService.delete(id); return ResponseEntity.noContent().build(); }
}
