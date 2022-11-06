package com.binar.kampusmerdeka.service;

import com.binar.kampusmerdeka.dto.RoleRequest;
import com.binar.kampusmerdeka.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse registerRole(RoleRequest roleRequest);
    RoleResponse updateRole(RoleRequest roleRequest, Integer roleId);
    Boolean deleteRole(Integer roleId);
    List<RoleResponse> searchAllRole();
}
