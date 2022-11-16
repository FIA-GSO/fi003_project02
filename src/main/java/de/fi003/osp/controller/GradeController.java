package de.fi003.osp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.fi003.osp.entity.Course;
import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.ClassRepository;
import de.fi003.osp.repository.CourseRepository;
import de.fi003.osp.repository.TeacherRepository;
import de.fi003.osp.utils.Helper;

@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/{grade}")
    public String getCreateDatesPage(Model model, @PathVariable String grade){
        if(!grade.equals("null")){
            Optional<Course> optCourse = courseRepository.findById(Integer.parseInt(grade));
            model.addAttribute("cours", optCourse.get());
            Optional<Teacher> optTeacher = teacherRepository.findById(optCourse.get().getTeacherId());
            model.addAttribute("teacher", optTeacher.get());
        }
        return Helper.checkLogin(teacherRepository, "grade_create");
    }
}
