package com.treebars.treebarsbackend.repository;

import com.treebars.treebarsbackend.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
