package com.nutybank.api.Repositories;

import com.nutybank.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Esta interfaz representa un repositorio para administrar entidades {@link Role}.
 * Extiende de la interfaz {@link org.springframework.data.jpa.repository.JpaRepository},
 * que proporciona funcionalidad CRUD para la entidad {@link Role}
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Recupera un {@link Optional} que contiene el rol asociado con el nombre dado.
     *
     * @param name El nombre del rol a buscar.
     * @return Un {@link Optional} que contiene el {@link Role} asociado con el nombre dado,
     * o un {@link Optional} vacío si no se encuentra ningún rol.
     */
    Optional<Role> findByName(String name);
}
