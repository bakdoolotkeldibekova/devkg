package com.example.manas.service;

import com.example.manas.entity.Position;
import com.example.manas.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }
}
