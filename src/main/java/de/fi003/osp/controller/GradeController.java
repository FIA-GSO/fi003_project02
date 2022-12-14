package de.fi003.osp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fi003.osp.entity.Class;
import de.fi003.osp.entity.Course;
import de.fi003.osp.entity.Lesson;
import de.fi003.osp.entity.LessonRecord;
import de.fi003.osp.entity.Student;
import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.ClassRepository;
import de.fi003.osp.repository.CourseRepository;
import de.fi003.osp.repository.LessonRecordRepository;
import de.fi003.osp.repository.LessonRepository;
import de.fi003.osp.repository.StudentRepository;
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

    @Autowired
    private LessonRecordRepository lessonRecordRepository;

    @Autowired 
    private LessonRepository lessonRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{grade}")
    public String getCreateDatesPage(Model model, @PathVariable String grade){
        if(!grade.equals("null")){
            Optional<Lesson> optLesson = lessonRepository.findById(Integer.parseInt(grade));
            model.addAttribute("lesson", optLesson.get());
            Optional<Course> optCourse = courseRepository.findById(optLesson.get().getCourseId());
            model.addAttribute("cours", optCourse.get());
            Optional<de.fi003.osp.entity.Class> optClass = classRepository.findById(optLesson.get().getCourseId());
            model.addAttribute("class", optClass.get());
            Optional<Teacher> optTeacher = teacherRepository.findById(optLesson.get().getTeacherId());
            model.addAttribute("teacher", optTeacher.get());
            ArrayList<Student> students = studentRepository.findAllByClassId(optLesson.get().getClassId());
            model.addAttribute("students", students);
            model.addAttribute("date", Helper.convertTime(optLesson.get().getStartDatetime()) + " - " + Helper.convertTime(optLesson.get().getEndDatetime()));
        }

        return Helper.checkLogin(teacherRepository, "grade_create");
    }

    @GetMapping("/create/{grade}")
    public String createGrade(Model model,@PathVariable String grade){
        model.addAttribute("pageTitle","Erstellen ??bersicht - Application");
        ArrayList<de.fi003.osp.entity.Class> classes = classRepository.findAll();
        ArrayList<Teacher> teachers = teacherRepository.findAll();
        ArrayList<Course> courses = courseRepository.findAll();
        model.addAttribute("classes", classes);
        model.addAttribute("teachers", teachers);
        model.addAttribute("courses", courses);
        return Helper.checkLogin(teacherRepository, "grade_entry");
    }

    @GetMapping("/weekly/create/{id}")
    public String createWeeklyGrade(Model model, @PathVariable int id){
        model.addAttribute("pageTitle","Erstellen Wochen??bersicht - Application");
        ArrayList<Course> courses = courseRepository.findAll();
        ArrayList<Class> classes = classRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("classes", classes);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Teacher> user = teacherRepository.findUserByEmail(auth.getName());
        model.addAttribute("teacher", user.get());
        return "calendar_weekly_create";
    }

    @PostMapping("/weekly/create")
    public ResponseEntity<List<Lesson>> createLessons(@RequestBody ArrayList<Lesson> lessons){
        List<Lesson> saved = lessonRepository.saveAll(lessons);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/create")
    public ResponseEntity<Lesson> createCourse(@RequestBody Lesson lesson){
        Lesson created = lessonRepository.save(lesson);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/records/{lesson}/{student}")
    public ResponseEntity<String> createRecord(@PathVariable String lesson, @PathVariable String student, @RequestBody ArrayList<LessonRecord> lessonRecords){
        if(!lessonRecords.isEmpty()){
            System.out.println(lessonRecords);
            ArrayList<LessonRecord> old = lessonRecordRepository.findAll();
            for (LessonRecord record : lessonRecords) {
                for (ListIterator<LessonRecord> iter = old.listIterator(); iter.hasNext(); ) {
                    LessonRecord element = iter.next();
                    if(element.getTeacherId() == record.getTeacherId() 
                    && element.getStudentId() == record.getStudentId()
                    && element.getLessonId() == record.getLessonId()){
                        lessonRecordRepository.delete(element);
                        iter.remove();
                    }
                }
            }
            lessonRecordRepository.saveAll(lessonRecords);
        }
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/records/{lesson}/{student}")
    public ResponseEntity<ArrayList<LessonRecord>> getRecords(@PathVariable String lesson,@PathVariable String student){
        ArrayList<LessonRecord> all = lessonRecordRepository.findAll();
        Optional<Lesson> optLessen = lessonRepository.findById(Integer.parseInt(lesson));
        Optional<Teacher> optTeacher = teacherRepository.findById(optLessen.get().getTeacherId());
        Optional<Student> optStudent = studentRepository.findById(Integer.parseInt(student));
        for (ListIterator<LessonRecord> iter = all.listIterator(); iter.hasNext(); ) {
            LessonRecord element = iter.next();
            if(element.getLessonId() != optLessen.get().getId()){
                iter.remove();
                continue;
            }
            if(element.getStudentId() != optStudent.get().getId()){
                iter.remove();
                continue;
            }
            if(element.getTeacherId() != optTeacher.get().getId()){
                iter.remove();
                continue;
            }
        }
        return ResponseEntity.ok(all);
    }
}
