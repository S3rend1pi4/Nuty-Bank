package com.nutybank.api.services.role;

import com.nutybank.api.repositories.RoleRepository;
import com.nutybank.api.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
