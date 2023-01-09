package com.example.manas.service;


import com.example.manas.entity.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAll();
    Position save(Position position);

    List<Position> getAllByType(String type);
    List<Position> getAllByUrl(String url);
    List<Position> getAllByCompany(String company);
    List<Position> getAllByLocation(String location);
    List<Position> getAllByTitle(String title);
}
