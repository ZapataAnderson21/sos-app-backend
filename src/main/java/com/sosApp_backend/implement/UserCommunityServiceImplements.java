package com.sosApp_backend.implement;

import com.sosApp_backend.model.Community;
import com.sosApp_backend.model.User;
import com.sosApp_backend.model.UserCommunity;
import com.sosApp_backend.repository.UserCommunityRepository;
import com.sosApp_backend.service.UserCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserCommunityServiceImplements implements UserCommunityService {

    @Autowired
    private UserCommunityRepository userCommunityRepository;

    @Override
    public String addUserToCommunity(User user, Community community) {
        // Verificar si el usuario ya pertenece a 5 comunidades
        long communityCount = userCommunityRepository.countByUserId(user.getUser_id());
        if (communityCount >= 5) {
            return "El usuario no puede pertenecer a más de 5 comunidades.";
        }

        // Agregar el usuario a la comunidad
        UserCommunity userCommunity = new UserCommunity();
        userCommunity.setUser(user);
        userCommunity.setCommunity(community);

        userCommunityRepository.save(userCommunity);
        return "El usuario se ha unido con éxito.";
    }

    @Override
    public List<UserCommunity> getAllUserCommunities() {
        return userCommunityRepository.findAll();
    }

    @Override
    public UserCommunity getUserCommunityById(UUID id) {
        Optional<UserCommunity> userCommunity = userCommunityRepository.findById(id);
        return userCommunity.orElse(null);
    }

    @Override
    public String deleteUserFromCommunity(UUID id) {
        if (userCommunityRepository.existsById(id)) {
            userCommunityRepository.deleteById(id);
            return "El usuario ha sido eliminado de la comunidad con éxito.";
        }
        return "No se encontró la relación entre usuario y comunidad.";
    }

    @Override
    public List<UserCommunity> getCommunitiesByUser(User user) {
        return userCommunityRepository.findByUserId(user.getUser_id());
    }
}
