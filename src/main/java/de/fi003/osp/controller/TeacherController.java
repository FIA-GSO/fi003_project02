package de.fi003.osp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.TeacherRepository;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String id) {
        Optional<Teacher> optTeacher = teacherRepository.findById(Integer.parseInt(id));
        return ResponseEntity.ok(optTeacher.get());
    }

    @PostMapping("/login")
    public String login(@RequestBody String user){
        return user;

    }

    @PostMapping("/register")
    public String register(@RequestBody String user){
        return user;

    }
}
