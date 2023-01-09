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

    @Override
    public List<Position> getAllByType(String type) {
        return positionRepository.findAllByType(type);
    }

    @Override
    public List<Position> getAllByUrl(String url) {
        return positionRepository.findAllByUrl(url);
    }

    @Override
    public List<Position> getAllByCompany(String company) {
        return positionRepository.findAllByCompany(company);
    }

    @Override
    public List<Position> getAllByLocation(String location) {
        return positionRepository.findAllByLocation(location);
    }

    @Override
    public List<Position> getAllByTitle(String title) {
        return positionRepository.findAllByTitle(title);
    }
}
