package com.sosApp_backend.repository;

import com.sosApp_backend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {

    // Obtener un chat por el ID de la comunidad
    @Query("SELECT c FROM Chat c WHERE c.community.community_id = :communityId")
    Chat findByCommunityId(@Param("communityId") UUID communityId);

    // Obtener un chat por su ID
    @Query("SELECT c FROM Chat c WHERE c.chat_id = :chatId")
    Chat findByChatId(@Param("chatId") UUID chatId);

    // Obtener todos los chats
    @Query("SELECT c FROM Chat c")
    List<Chat> findAllChats();

    // Eliminar un chat por su ID
    @Query("DELETE FROM Chat c WHERE c.chat_id = :chatId")
    void deleteByChatId(@Param("chatId") UUID chatId);

    // (Opcional) Buscar chats con comunidades que contengan una palabra clave en el nombre
    @Query("SELECT c FROM Chat c WHERE LOWER(c.community.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Chat> findByCommunityNameContaining(@Param("keyword") String keyword);
}
