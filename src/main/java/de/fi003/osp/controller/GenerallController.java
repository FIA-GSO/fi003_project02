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
@RequestMapping("")
public class GenerallController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("pageTitle"," - Application");
        return Helper.checkLogin(teacherRepository, "redirect:/class");
    }

    @GetMapping("/class")
    public String getClassOverview(Model model) {
        model.addAttribute("pageTitle","Klassenübersicht - Application");
        ArrayList<de.fi003.osp.entity.Class> classList = classRepository.findAll();
        model.addAttribute("list", classList);
        return Helper.checkLogin(teacherRepository, "class_select");
    }

    @GetMapping("/{className}/grades")
    public String getClassGrades(Model model, @PathVariable String className){
        model.addAttribute("pageTitle","Notenübersicht - Application");
        Optional<de.fi003.osp.entity.Class> optClass = classRepository.findByName(className);
        if(!optClass.isPresent()){
            return "404";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Teacher> user = teacherRepository.findUserByEmail(auth.getName());
        ArrayList<Course> courseList = courseRepository.findAllByTeacherId(user.get().getId());
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (Course course : courseList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(course.getId()));
            map.put("name", course.getName());
            Optional<Teacher> optTeacher = teacherRepository.findById(course.getTeacherId());
            map.put("teacherId", optTeacher.get().getName());
            map.put("startDatetime", Helper.convertTime(course.getStartDatetime()));
            map.put("endDatetime", Helper.convertTime(course.getEndDatetime()));
            list.add(map);
        }
        model.addAttribute("courses", list);
        model.addAttribute("class", optClass.get());
        model.addAttribute("entries", "Einträge (" + courseList.size() + ")");
        return Helper.checkLogin(teacherRepository, "grade_entries");
    }

    @GetMapping("/{className}/weekly")
    public String getClassWekly(Model model, @PathVariable String className){
        model.addAttribute("pageTitle","Wochenübersicht - Application");
        Optional<de.fi003.osp.entity.Class> optClass = classRepository.findByName(className);
        if(!optClass.isPresent()){
            return "404";
        }
        model.addAttribute("class", optClass.get());
        return Helper.checkLogin(teacherRepository, "calendar_weekly_entries");
    }
}
