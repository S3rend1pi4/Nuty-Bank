package com.nutybank.api.repositories;

import com.nutybank.api.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Esta interfaz representa un repositorio para administrar entidades {@link Account}.
 * Extiende la interfaz {@link org.springframework.data.jpa.repository.JpaRepository},
 * que proporciona funcionalidad CRUD para la entidad {@link Account}.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Recupera un {@link Optional} que contiene la cuenta asociada con el ID de cliente dado.
     *
     * @param clientId El ID del cliente a buscar
     * @return Un {@link Optional} que contiene la  {@link Account} asociada con el ID de cliente dado,
     * o un {@link Optional} vacío si no se encuentra ninguna cuenta.
     */
    Optional<Account> findByClientId(Long clientId);

    Optional<Account> findAccountsByClientName(String userName);

    /**
     * Comprueba si existe una cuenta para el ID de cliente dado.
     *
     * @param clientId El ID del cliente a comprobar.
     * @return {@code true} si existe una cuenta para el ID de cliente dado, {@code false} de lo contrario.
     */
    boolean existsByClientId(Long clientId);
}
