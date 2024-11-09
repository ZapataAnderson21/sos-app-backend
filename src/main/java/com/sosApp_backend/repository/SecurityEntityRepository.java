package com.sosApp_backend.repository;

import com.sosApp_backend.model.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SecurityEntityRepository extends JpaRepository<SecurityEntity, UUID> {

    // Obtener todas las entidades de seguridad asociadas a un departamento específico
    @Query(value= "SELECT s FROM SecurityEntity s WHERE LOWER(s.department) = LOWER(:department) ORDER BY s.province, s.district", nativeQuery = true)
    List<SecurityEntity> findByDepartment(@Param("department") String department);

    // Obtener una entidad de seguridad específica por su ID
    @Query(value= "SELECT s FROM SecurityEntity s WHERE s.security_entity_id = :entityId", nativeQuery = true)
    SecurityEntity findByEntityId(@Param("entityId") UUID entityId);

    // Eliminar una entidad de seguridad por su ID
    @Query(value= "DELETE FROM SecurityEntity s WHERE s.security_entity_id = :entityId", nativeQuery = true)
    void deleteByEntityId(@Param("entityId") UUID entityId);

    // Obtener todas las entidades de seguridad (ordenadas por departamento y provincia)
    @Query(value= "SELECT s FROM SecurityEntity s ORDER BY s.department, s.province, s.district", nativeQuery = true)
    List<SecurityEntity> findAllEntities();

    // Buscar entidades de seguridad por teléfono (opcional)
    @Query(value= "SELECT s FROM SecurityEntity s WHERE s.phone_number LIKE CONCAT('%', :phone, '%')", nativeQuery = true)
    List<SecurityEntity> findByPhoneNumberContaining(@Param("phone") String phone);

    // Obtener entidades de seguridad por provincia (opcional)
    @Query(value= "SELECT s FROM SecurityEntity s WHERE LOWER(s.province) = LOWER(:province) ORDER BY s.district", nativeQuery = true)
    List<SecurityEntity> findByProvince(@Param("province") String province);
}
