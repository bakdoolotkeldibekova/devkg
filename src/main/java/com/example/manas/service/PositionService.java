package com.example.manas.service;


import com.example.manas.entity.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAll();
    Position save(Position position);
}
