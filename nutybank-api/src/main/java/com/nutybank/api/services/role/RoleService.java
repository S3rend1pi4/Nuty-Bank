package com.nutybank.api.services.role;

import com.nutybank.api.entities.Role;

import java.util.Optional;

/**
 * Esta interfaz define un servicio para gestionar Roles
 */
public interface RoleService {

    /**
     * Busca un rol por su ID.
     * ç
     * @param id El id del rol a buscar.
     * @return Un {@link Optional} que contiene el rol encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún rol con el ID dado.
     */
    Optional<Role> findById(Long id);

    /**
     * Busca un rol por su nombre.
     *
     * @param name El nombre del rol a buscar.
     * @return Un {@link Optional} que contiene el rol encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún rol con el nombre dado
     */
    Optional<Role> findByName(String name);
}
