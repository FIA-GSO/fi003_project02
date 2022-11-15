package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fi003.osp.entity.Teacher;

import java.util.ArrayList;
import java.util.Optional;
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findById(int id);
    ArrayList<Teacher> findAll();
}