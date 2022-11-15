package de.fi003.osp.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.TeacherRepository;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String id) {
        Optional<Teacher> optTeacher = teacherRepository.findById(Integer.parseInt(id));
        return ResponseEntity.ok(optTeacher.get());
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Teacher> register(@RequestBody Teacher userProv) throws IOException {
        Optional<Teacher> userOptional = teacherRepository.findUserByEmail(userProv.getEmail());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Teacher user = new Teacher();
        user.setEmail(userProv.getEmail());
        user.setPassword(passwordEncoder.encode(userProv.getPassword()));
        user.setName(userProv.getName());
        user.setLogin(userProv.getLogin());
        user.setPhone(userProv.getPhone());
        user.setVorname(userProv.getVorname());

        Teacher created = teacherRepository.save(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, @RequestBody Teacher user) {
        Optional<Teacher> userOptional = teacherRepository.findUserByEmail(user.getEmail());
        if(!userOptional.isPresent()){
            return "redirect:/login";
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return "redirect:/";
    }
}
