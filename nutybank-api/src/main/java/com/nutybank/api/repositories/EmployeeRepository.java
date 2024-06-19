package com.nutybank.api.repositories;

import com.nutybank.api.entities.Client;
import com.nutybank.api.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Esta interfaz representa un repositorio para administrar la entidad {@link Employee}.
 * Extiende de la interfaz {@link org.springframework.data.jpa.repository.JpaRepository},
 * que proporciona funcionalidad CRUD para entidad {@link Employee}.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Recupera un {@link Optional} que contiene el empleado asociado con el DNI dado.
     *
     * @param dni El DNI del empleado a buscar.
     * @return Un {@link Optional} que contiene el {@link Employee} asociado con el DNI dado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente asociado.
     */
    Optional<Employee> findByDni(String dni);

    /**
     * Recupera un {@link Optional} que contiene el empleado asociado con el nombre dado.
     *
     * @param name El nombre del empleado a buscar.
     * @return Un {@link Optional} que contiene el {@link Employee} asociado con el nombre dado,
     * o un {@link Optional} vacío si no se encuentra ningún cliente asociado.
     */
    Optional<Employee> findByName(String name);

    boolean existsByDni(String dni);

    //Optional<Employee> findByUserName(String userName);
}
