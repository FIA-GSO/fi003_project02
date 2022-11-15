package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fi003.osp.entity.Course;

import java.util.ArrayList;
import java.util.Optional;
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findById(int id);
    ArrayList<Course> findAll();

    ArrayList<Course> findAllByTeacherId(int id);
}