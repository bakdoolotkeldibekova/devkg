package com.example.manas.repository;

import com.example.manas.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAllByType(String type);
    List<Position> findAllByUrl(String url);
    List<Position> findAllByCompany(String company);
    List<Position> findAllByLocation(String location);
    List<Position> findAllByTitle(String title);
}
