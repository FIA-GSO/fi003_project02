package de.fi003.osp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fi003.osp.entity.Course;
import de.fi003.osp.entity.Lesson;
import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.ClassRepository;
import de.fi003.osp.repository.CourseRepository;
import de.fi003.osp.repository.LessonRecordRepository;
import de.fi003.osp.repository.LessonRepository;
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

    @Autowired
    private LessonRecordRepository lessonRecordRepository;

    @Autowired LessonRepository lessonRepository;

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
        ArrayList<Lesson> lessonList = lessonRepository.findAllByClassId(optClass.get().getId());
        
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (Lesson lesson : lessonList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(lesson.getId()));
            Optional<Course> optCourse = courseRepository.findById(lesson.getCourseId());
            map.put("name", optCourse.get().getName());
            Optional<Teacher> optTeacher = teacherRepository.findById(lesson.getTeacherId());
            map.put("teacherId", optTeacher.get().getName());
            map.put("startDatetime", Helper.convertTime(lesson.getStartDatetime()));
            map.put("endDatetime", Helper.convertTime(lesson.getEndDatetime()));
            list.add(map);
        }
        model.addAttribute("courses", list);
        model.addAttribute("class", optClass.get());
        model.addAttribute("entries", "Einträge (" + lessonList.size() + ")");
        return Helper.checkLogin(teacherRepository, "grade_entries");
    }

    @GetMapping("/{className}/weekly")
    public String getClassWekly(Model model, @PathVariable String className){
        model.addAttribute("pageTitle","Wochenübersicht - Application");
        Optional<de.fi003.osp.entity.Class> optClass = classRepository.findByName(className);
        if(!optClass.isPresent()){
            return "404";
        }
        ArrayList<Lesson> lessons = lessonRepository.findAll();
        for (ListIterator<Lesson> iter = lessons.listIterator(); iter.hasNext(); ) {
            Lesson element = iter.next();
            if(element.getClassId() != optClass.get().getId()){
                iter.remove();
                continue;
            }
        }
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (Lesson lesson : lessons) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(lesson.getId()));
            map.put("class", optClass.get().getName());
            Optional<Course> optCourse = courseRepository.findById(lesson.getCourseId());
            map.put("course", optCourse.get().getName());
            map.put("room", lesson.getRoomCode());
            map.put("startTime", Helper.convertTime(lesson.getStartDatetime()));
            map.put("endTime", Helper.convertTime(lesson.getEndDatetime()));
            map.put("kw", "42");
            list.add(map);
        }
        model.addAttribute("lessons", list);
        model.addAttribute("class", optClass.get());
        return Helper.checkLogin(teacherRepository, "calendar_weekly_entries");
    }
}
