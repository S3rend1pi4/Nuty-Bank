package com.nutybank.api.repositories;

import com.nutybank.api.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Esta interfaz representa un repositorio para administrar entidades {@link Client}.
 * Extiende la interfaz {@link org.springframework.data.jpa.repository.JpaRepository},
 * que proporciona funcionalidad CRUD para la entidad {@link Client}.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Recupera un {@link Optional} que contiene el cliente asociado con el DNI dado.
     *
     * @param dni El DNI del cliente a buscar.
     * @return Un {@link Optional} que contiene el {@link Client} asociado con el DNI dado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente.
     */
    Optional<Client> findByDni(String dni);

    /**
     * Recupera un {@link Optional} que contiene el cliente asociado con el nombre dado.
     *
     * @param name El nombre del cliente a buscar.
     * @return Un {@link Optional} que contiene el {@link Client} asociado con el nombre dado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente.
     */
    Optional<Client> findByName(String name);

    boolean existsByDni(String dni);
}
