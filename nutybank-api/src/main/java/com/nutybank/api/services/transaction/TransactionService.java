package com.nutybank.api.services.transaction;

import com.nutybank.api.dto.TransactionDto;
import com.nutybank.api.entities.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz define un servicio para gestionar transacciones.
 */
public interface TransactionService {

    /**
     * Busca todas las transacciones en la base de datos.
     *
     * @return Una lista de todas las transacciones encontradas en la base de datos.
     */
    List<Transaction> findAll();

    /**
     * Busca una transacción por el nombre del cliente asociado.
     *
     * @param clientName El nombre del cliente asociado con la transacción a buscar.
     * @return Un {@link Optional} que contiene la transacción encontrada,
     * o un {@link Optional} vacío si no se encuentra ninguna transacción para el nombre de cliente dado.
     */
    List<Transaction> findByClientName(String clientName);

    /**
     * Busca una transacción por el DNI del cliente asociado.
     *
     * @param dni El DNI del cliente asociado con la transacción a buscar.
     * @return Un {@link Optional} que contiene la transacción encontrada,
     * o un {@link Optional} vacío si no se encuentra ninguna transacción para el nombre de cliente dado.
     */
    List<Transaction> findByClientDni(String dni);

    /**
     * Busca transacciones realizadas en una fecha específica.
     *
     * @param date La fecha en la que se realizaron las transacciones a buscar.
     * @return Una lista de transacciones realizadas en la fecha especificada.
     */
    List<Transaction> findByDate(Date date);

    /**
     * Guarda una nueva transacción en la base de datos.
     *
     * @param transaction la transacción a guardar.
     * @return La transacción guardada.
     */
    Transaction save(Transaction transaction);

    /**
     * Elimina una transacción de la base de datos.
     *
     * @param id El ID de la transacción a eliminar.
     * @return Un {@link Optional} que contiene la transacción eliminada,
     * o un {@link Optional} vacío si no encuentra ninguna transacción con el ID de transacción dado.
     */
    Optional<Transaction> delete(Long id);
}
