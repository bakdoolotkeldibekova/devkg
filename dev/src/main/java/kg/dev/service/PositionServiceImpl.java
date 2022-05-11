package kg.dev.service;

import kg.dev.entity.Position;
import kg.dev.repository.PositionRepository;
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
}
