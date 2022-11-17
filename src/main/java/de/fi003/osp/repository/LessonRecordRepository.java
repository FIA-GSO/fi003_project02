package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.fi003.osp.entity.LessonRecord;

import java.util.ArrayList;
import java.util.Optional;
public interface LessonRecordRepository extends JpaRepository<LessonRecord, Integer> {

    Optional<LessonRecord> findById(int id);
    ArrayList<LessonRecord> findAll();

    ArrayList<LessonRecord> findAllByTeacherIdAndStundetIdAndLessonId(int teacherId, int studentId, int lessonId);
}