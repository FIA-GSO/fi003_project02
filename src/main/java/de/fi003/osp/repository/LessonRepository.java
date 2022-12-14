package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fi003.osp.entity.Lesson;

import java.util.ArrayList;
import java.util.Optional;
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    Optional<Lesson> findById(int id);
    ArrayList<Lesson> findAll();
    ArrayList<Lesson> findAllByTeacherId(int id);
    ArrayList<Lesson> findAllByClassId(int id);
}