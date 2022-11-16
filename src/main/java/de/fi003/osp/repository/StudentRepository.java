package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fi003.osp.entity.Student;

import java.util.ArrayList;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findById(int id);
    ArrayList<Student> findAll();
    ArrayList<Student> findAllByClassId(int classId);
}