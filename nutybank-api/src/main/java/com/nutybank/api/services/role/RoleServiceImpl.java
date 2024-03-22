package com.nutybank.api.services.role;

import com.nutybank.api.entities.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {


    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return Optional.empty();
    }
}
