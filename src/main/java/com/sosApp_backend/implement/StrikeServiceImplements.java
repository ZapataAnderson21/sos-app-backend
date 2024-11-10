package com.sosApp_backend.implement;

import com.sosApp_backend.model.Strike;
import com.sosApp_backend.model.User;
import com.sosApp_backend.repository.StrikeRepository;
import com.sosApp_backend.service.StrikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrikeServiceImplements implements StrikeService {

    @Autowired
    private StrikeRepository strikeRepository;

    @Override
    public String assignStrikeToUser(Strike strike) {
        int strikeCount = strikeRepository.countStrikesByUser(strike.getUser().getUser_id());
        if (strikeCount >= 3) {
            return "El usuario ya tiene 3 strikes. No puede realizar ciertas tareas.";
        }

        strikeRepository.save(strike);
        return "Strike asignado con éxito.";
    }

    @Override
    public List<Strike> getStrikesByUser(User user) {
        return strikeRepository.findAllByUser(user.getUser_id());
    }

    @Override
    public int getStrikeCountByUser(User user) {
        return strikeRepository.countStrikesByUser(user.getUser_id());
    }

}
