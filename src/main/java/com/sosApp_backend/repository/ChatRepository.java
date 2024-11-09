package com.sosApp_backend.repository;

import com.sosApp_backend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {

    // Obtener un chat por el ID de la comunidad
    @Query(value= "SELECT c FROM Chat c WHERE c.community.community_id = :communityId", nativeQuery = true)
    Chat findByCommunityId(@Param("communityId") UUID communityId);

    // Obtener un chat por su ID
    @Query(value= "SELECT c FROM Chat c WHERE c.chat_id = :chatId", nativeQuery = true)
    Chat findByChatId(@Param("chatId") UUID chatId);

    // Obtener todos los chats
    @Query(value= "SELECT c FROM Chat c", nativeQuery = true)
    List<Chat> findAllChats();

    // Eliminar un chat por su ID
    @Query(value= "DELETE FROM Chat c WHERE c.chat_id = :chatId", nativeQuery = true)
    void deleteByChatId(@Param("chatId") UUID chatId);

    // (Opcional) Buscar chats con comunidades que contengan una palabra clave en el nombre
    @Query(value= "SELECT c FROM Chat c WHERE LOWER(c.community.name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Chat> findByCommunityNameContaining(@Param("keyword") String keyword);
}
