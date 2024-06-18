package com.nutybank.api.services.employee;

import com.nutybank.api.dto.EmployeeDto;
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
    Optional<Employee> findById(Long id);

    /**
     * Busca un empleado por su nombre de usuario.
     *
     * @param employeeName El nombre de usuario del empleado a buscar.
     * @return Un {@link Optional} que contiene el empleado encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún empleado con el nombre dado
     */
    Optional<Employee> findeByEmployeeName(String employeeName);

    /**
     * Busca un empleado por su número de identificación (DNI).
     *
     * @param dni El número de identificación.
     * @return Un {@link Optional} que contiene el empleado encontrado,
     * o un {@link Optional} vacío si no se encuentra ningún empleado con el DNI dado.
     */
    Optional<Employee> findByDni(String dni);

    /**
     * Guarda un nuevo empleado en la base de datos.
     *
     * @param employee El empleado a guardar.
     * @return El empleado guardado.
     */
    Employee save(Employee employee);

    /**
     * Actualiza los detalles de un empleado existente.
     *
     * @param id El ID del empleado a actualizar.
     * @param employee Los nuevos detalles del empleado.
     * @return Un {@link Optional} que contiene el empleado actualizado,
     * o un {@link Optional} vacío si no se encuentra ningún empleado con el ID dado.
     */
    Optional<Employee> update(Long id, EmployeeDto employeeDto);

    /**
     * Elimina un empleado de la base de datos.
     *
     * @param id El ID del empleado a eliminar.
     * @return Un {@link Optional} que contiene el empleado eliminado,
     * o un {@link Optional} vacío si no se encuentra ningún empleado con el ID dado.
     */
    Optional<Employee> delete(Long id);
    boolean existsByDni(String dni);
}
