package com.treebars.treebarsbackend.repository;

import com.treebars.treebarsbackend.entity.Routine;
import com.treebars.treebarsbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByUser(User user);
}
