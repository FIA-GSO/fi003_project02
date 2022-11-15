package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fi003.osp.entity.ClassCourse;

import java.util.ArrayList;
import java.util.Optional;
public interface ClassCourseRepository extends JpaRepository<ClassCourse, Integer> {

    Optional<ClassCourse> findById(int id);
    ArrayList<ClassCourse> findAll();
}
