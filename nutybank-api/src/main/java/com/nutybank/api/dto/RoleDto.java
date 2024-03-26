package com.nutybank.api.dto;

import com.nutybank.api.entities.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleDto {

    private Long id;
    private String name;

    public RoleDto() {

    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public static Set<Role> toRoles(Set<RoleDto> rolesDto) {
        return rolesDto.stream()
                .map(roleDto -> new Role(roleDto.getId(),roleDto.getName()))
                .collect(Collectors.toSet());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
