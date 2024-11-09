package com.sosApp_backend.repository;

import com.sosApp_backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    // Obtener todos los mensajes asociados a un chat específico
    @Query(value= "SELECT m FROM Message m WHERE m.chat.chat_id = :chatId ORDER BY m.created_at ASC", nativeQuery = true)
    List<Message> findByChatId(@Param("chatId") UUID chatId);

    // Obtener un mensaje específico por su ID
    @Query(value= "SELECT m FROM Message m WHERE m.message_id = :messageId", nativeQuery = true)
    Message findByMessageId(@Param("messageId") UUID messageId);

    // Obtener todos los mensajes (ordenados por fecha de creación)
    @Query(value= "SELECT m FROM Message m ORDER BY m.created_at ASC", nativeQuery = true)
    List<Message> findAllMessages();

    // Eliminar un mensaje por su ID
    @Query(value= "DELETE FROM Message m WHERE m.message_id = :messageId", nativeQuery = true)
    void deleteByMessageId(@Param("messageId") UUID messageId);

    // Obtener mensajes que contengan una palabra clave en el contenido (opcional)
    @Query(value= "SELECT m FROM Message m WHERE LOWER(m.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY m.created_at ASC", nativeQuery = true)
    List<Message> findByContentContaining(@Param("keyword") String keyword);
}
