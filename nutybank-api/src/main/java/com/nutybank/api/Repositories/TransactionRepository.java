package com.nutybank.api.Repositories;

import com.nutybank.api.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Esta interfaz representa un repositorio para administrar entidades {@link Transaction}.
 * Extiende la interfaz {@link org.springframework.data.jpa.repository.JpaRepository},
 * que proporciona funcionalidad CRUD para la entidad {@link Transaction}.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Recupera un {@link Optional} que contiene la transacción asociada con el nombre del cliente dado.
     *
     * @param clientName El nombre del cliente asociado con la transacción a buscar.
     * @return Un {@link Optional} que contiene la {@link Transaction} asociada con el nombre del cliente dado,
     * o un {@link Optional} vacío si no se encuentra ninguna transacción.
     */
    Optional<Transaction> findByClientTransactionName(String clientName);

    /**
     * Recupera un {@link Optional} que contiene la transacción asociada con el DNI del cliente dado.
     *
     * @param dni El DNI del cliente asociado con la transacción a buscar.
     * @return Un {@link Optional} que contiene la {@link Transaction} asociada con el DNI del cliente dado,
     * o un {@link Optional} vacío si no se encuentra ninguna transacción.
     */
    Optional<Transaction> findByClientTransactionDni(String dni);
}
