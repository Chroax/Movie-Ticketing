package com.binar.kampusmerdeka.services;

import com.binar.kampusmerdeka.dto.RoleRequest;
import com.binar.kampusmerdeka.dto.RoleResponse;
import com.binar.kampusmerdeka.model.Roles;
import com.binar.kampusmerdeka.repository.RoleRepository;
import com.binar.kampusmerdeka.service.RoleService;
import com.binar.kampusmerdeka.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class RoleServiceImplTest {
    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Register new role, Positive")
    void testPositiveRegisRole(){
        RoleRequest roleRequest = new RoleRequest("ROLE_ADMIN");
        Roles roles = roleRequest.toRoles();
        Mockito.when(roleRepository.save(roles)).thenReturn(roles);
        var actualValue = roleService.registerRole(roleRequest);
        var expectedValue = "ROLE_ADMIN";

        Assertions.assertEquals(expectedValue, actualValue.getRoleName());
    }

    @Test
    @DisplayName("Get all role, Positive")
    void testPositiveGetAllRole(){
        List<Roles> allRoles = new ArrayList<>();
        allRoles.add(new RoleRequest("ROLE_ADMIN").toRoles());
        allRoles.add(new RoleRequest("ROLE_CUSTOMER").toRoles());
        allRoles.add(new RoleRequest("ROLE_SUPERADMIN").toRoles());

        Mockito.when(roleRepository.findAll()).thenReturn(allRoles);

        var actualValue = roleService.searchAllRole();
        var expectedSize = 3;
        var expectedValue1 = "ROLE_ADMIN";
        var expectedValue2 = "ROLE_CUSTOMER";
        var expectedValue3 = "ROLE_SUPERADMIN";

        Assertions.assertEquals(expectedSize, actualValue.size());
        Assertions.assertEquals(expectedValue1, actualValue.get(0).getRoleName());
        Assertions.assertEquals(expectedValue2, actualValue.get(1).getRoleName());
        Assertions.assertEquals(expectedValue3, actualValue.get(2).getRoleName());
    }

    @Test
    @DisplayName("Update role, Positive")
    void testPositiveUpdateRole(){
        String newRoleName = "ROLE_SUPERADMIN";
        RoleRequest roleRequest = new RoleRequest("ROLE_ADMIN");
        Roles roles = roleRequest.toRoles();
        Integer roleId = 1;

        Optional<Roles> rolesOptional = Optional.of(roles);

        Mockito.when(roleRepository.findById(roleId)).thenReturn(rolesOptional);
        roleRequest.setRoleName(newRoleName);
        Mockito.when(roleRepository.save(rolesOptional.get())).thenReturn(roles);

        var actualValue = roleService.updateRole(roleRequest, roleId);
        Assertions.assertEquals(newRoleName, actualValue.getRoleName());
    }

    @Test
    @DisplayName("Delete role, Positive")
    void testPositiveDeleteRole(){
        RoleRequest roleRequest = new RoleRequest("ROLE_ADMIN");
        Roles roles = roleRequest.toRoles();
        Integer roleId = 1;

        Mockito.when(roleRepository.existsById(roleId)).thenReturn(true);
        Mockito.doNothing().when(roleRepository).deleteById(roleId);
        var actualValue = roleService.deleteRole(roleId);
        var expectedValue = true;

        Assertions.assertEquals(expectedValue, actualValue);
    }
}
