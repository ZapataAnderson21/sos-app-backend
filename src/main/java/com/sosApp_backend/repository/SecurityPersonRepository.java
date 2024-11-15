package com.sosApp_backend.repository;

import com.sosApp_backend.model.SecurityPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SecurityPersonRepository extends JpaRepository<SecurityPerson, UUID> {

    // Obtener todas las personas de seguridad asociadas a una entidad de seguridad específica
    @Query(value= "SELECT sp FROM SecurityPerson sp WHERE sp.securityEntity.security_entity_id = :entityId ORDER BY sp.full_name", nativeQuery = true)
    List<SecurityPerson> findBySecurityEntityId(@Param("entityId") UUID securityEntityId);

    // Obtener una persona de seguridad específica por su ID
    @Query(value= "SELECT sp FROM SecurityPerson sp WHERE sp.security_person_id = :personId", nativeQuery = true)
    SecurityPerson findByPersonId(@Param("personId") UUID personId);

    // Eliminar una persona de seguridad por su ID
    @Query(value= "DELETE FROM SecurityPerson sp WHERE sp.security_person_id = :personId", nativeQuery = true)
    void deleteByPersonId(@Param("personId") UUID personId);

    // Obtener todas las personas de seguridad (ordenadas por nombre completo)
    @Query(value= "SELECT sp FROM SecurityPerson sp ORDER BY sp.full_name", nativeQuery = true)
    List<SecurityPerson> findAllPersons();

    // Buscar personas de seguridad por su nombre completo (opcional)
    @Query(value= "SELECT sp FROM SecurityPerson sp WHERE LOWER(sp.full_name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<SecurityPerson> findByFullNameContaining(@Param("name") String name);

    // Buscar personas de seguridad por su DNI
    @Query(value= "SELECT sp FROM SecurityPerson sp WHERE sp.dni = :dni", nativeQuery = true)
    SecurityPerson findByDni(@Param("dni") String dni);
}
