package de.fi003.osp.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.fi003.osp.entity.Teacher;
import de.fi003.osp.repository.TeacherRepository;
public class Helper {
    
    public static String checkLogin(TeacherRepository teacherRepository, String redirect){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Teacher> user = teacherRepository.findUserByEmail(auth.getName());
        if(!user.isPresent()){
            return "redirect:/login";
        }
        return redirect;
    }

    public static String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd");
        return format.format(date);
    }
}
