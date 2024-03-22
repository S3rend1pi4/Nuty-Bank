package com.nutybank.api.services.employee;

import com.nutybank.api.entities.Account;
import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz define un servicio para gestionar empleados.
 */
public interface EmployeeService {

    /**
     * Recupera una página de empleados.
     *
     * @param pageable Objeto que especifíca la paginación y el ordenamiento de los resultados.
     * @return Una página de empleados de acuerdo a los criterios de paginación y ordenamiento especificados.
     */
    Page<Employee> findAll(Pageable pageable);

    /**
     * Busca un empleado por su ID.
     *
     * @param id El ID del empleado a buscar.
     * @return Un {@link Optional} que contiene el empleado encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún empleado con el ID dado.
     */
    Optional<Client> findById(Long id);

    /**
     * Busca un cliente por su nombre de usuario.
     *
     * @param userName El nombre de usuario del cliente a buscar.
     * @return Un {@link Optional} que contiene el cliente encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente con el nombre dado
     */
    Optional<Client> findeByUserName(String userName);

    /**
     * Busca un cliente por su número de identificación (DNI).
     *
     * @param dni El número de identificación.
     * @return Un {@link Optional} que contiene el cliente encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente con el DNI dado.
     */
    Optional<Client> findByDni(String dni);

    /**
     * Busca todas las cuentas asociadas a un cliente.
     *
     * @param userId El ID del cliente para el cual se buscarán las cuentas.
     * @return Una lista de todas las cuentas asociadas al cliente dado.
     */
    List<Account> findAllClientAccounts(Long userId);

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param client El cliente a guardar.
     * @return El cliente guardado.
     */
    Client save(Client client);

    /**
     * Actualiza los detalles de un cliente existente.
     *
     * @param id El ID del cliente a actualizar.
     * @param client Los nuevos detalles del cliente.
     * @return Un {@link Optional} que contiene el cliente actualizado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente con el ID dado.
     */
    Optional<Client> update(Long id, Client client);

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id El ID del cliente a eliminar.
     * @return Un {@link Optional} que contiene el cliente eliminado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente con el ID dado.
     */
    Optional<Client> delete(Long id);
}
