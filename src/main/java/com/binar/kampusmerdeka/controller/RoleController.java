package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.dto.RoleRequest;
import com.binar.kampusmerdeka.dto.RoleResponse;
import com.binar.kampusmerdeka.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController
{
    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createRole(@RequestBody RoleRequest roleRequest)
    {
        MessageModel messageModel = new MessageModel();

        RoleResponse roleResponse = roleService.registerRole(roleRequest);

        if(roleResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(roleResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new user");
            messageModel.setData(roleResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllRole()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<RoleResponse> rolesGet = roleService.searchAllRole();
            messageModel.setMessage("Success get all role");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(rolesGet);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all role");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
        }
        return ResponseEntity.ok().body(messageModel);
    }

    @PutMapping("/update/{roleId}")
    public ResponseEntity<MessageModel> updateRole(@PathVariable Integer roleId, @RequestBody RoleRequest roleRequest)
    {
        MessageModel messageModel = new MessageModel();
        RoleResponse roleResponse = roleService.updateRole(roleRequest, roleId);

        if(roleResponse.getMessage() != null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage(roleResponse.getMessage());
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update role with id : " + roleId);
            messageModel.setData(roleResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<MessageModel> deleteRole(@PathVariable Integer roleId)
    {
        MessageModel messageModel = new MessageModel();
        Boolean deleteRole = roleService.deleteRole(roleId);
        if(deleteRole)
        {
            messageModel.setMessage("Success delete role by id : " + roleId);
            messageModel.setStatus(HttpStatus.OK.value());
        }
        else
        {
            messageModel.setMessage("Failed delete role by id : " + roleId + ", not found");
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
        }

        return ResponseEntity.ok().body(messageModel);
    }
}
