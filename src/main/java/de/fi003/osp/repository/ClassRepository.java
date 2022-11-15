package de.fi003.osp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;
public interface ClassRepository extends JpaRepository<de.fi003.osp.entity.Class, Integer> {

    Optional<de.fi003.osp.entity.Class> findById(int id);
    ArrayList<de.fi003.osp.entity.Class> findAll();
}
