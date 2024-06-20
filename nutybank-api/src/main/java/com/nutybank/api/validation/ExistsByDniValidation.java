package com.nutybank.api.validation;

import com.nutybank.api.services.client.ClientService;
import com.nutybank.api.services.employee.EmployeeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByDniValidation implements ConstraintValidator<ExistsByDni, String> {

    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;
    @Override
    public boolean isValid(String dni, ConstraintValidatorContext context) {
        if (clientService == null) {
            return true;
        } else if(employeeService == null) {
            return true;
        }

        return !(clientService.existsByDni(dni) || employeeService.existsByDni(dni));
    }
}
