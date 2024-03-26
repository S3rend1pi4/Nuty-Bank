package com.nutybank.api.services.account;

import com.nutybank.api.dto.AccountDto;
import com.nutybank.api.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz representa un servicio para gestionar cuentas de usuario.
 */
public interface AccountService {


    List<Account> findAll();

    /**
     * Abre una nueva cuenta para el usuario con el ID dado.
     *
     * @param userId El ID del usuario para el cual se abrirá la cuenta.
     * @return La cuenta recién abierta.
     */
    @Transactional
    AccountDto openAccount(Long userId, AccountDto accountDto);

    /**
     * Encuentra la cuenta asociada con el nombre de usuario dado.
     *
     * @param userName El nombre de usuario para el cual se buscará la cuenta.
     * @return La cuenta asociada con el nombre de usuario dado, o null si no se encuentra ninguna cuenta.
     */
    Account findAccountByUserName(String userName);

    /**
     * Encuentra la cuenta asociada con el ID dado.
     *
     * @param id El ID de la cuenta a buscar.
     * @return La cuenta asociada con el ID dado, o null si no se encuentra ninguna cuenta.
     */
    Account findById(Long id);

    /**
     * Realiza un depósito en la cuenta con el ID dado.
     *
     * @param id       El ID de la cuenta en la que se realizará el depósito.
     * @param quantity La cantidad a depositar.
     * @return La cuenta después de realizar el depósito.
     */
    @Transactional
    Optional<Account> deposit(Long id, double quantity);

    /**
     * Consulta la cuenta actual.
     *
     * @return La cuenta actualmente consultada.
     */
    Account consultAccount();

}
