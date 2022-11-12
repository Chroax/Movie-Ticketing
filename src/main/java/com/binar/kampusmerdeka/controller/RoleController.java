package com.binar.kampusmerdeka.controller;

import com.binar.kampusmerdeka.dto.MessageModel;
import com.binar.kampusmerdeka.dto.RoleRequest;
import com.binar.kampusmerdeka.dto.RoleResponse;
import com.binar.kampusmerdeka.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/role", produces = {"application/json"})
public class RoleController
{
    @Autowired
    RoleService roleService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Add Role",
                            description = "Menambahkan role baru",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Register new role\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"role_id\": 1,\n"
                                    + "      \"role_name\": ADMIN\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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
            messageModel.setMessage("Register new role");
            messageModel.setData(roleResponse);
        }

        return ResponseEntity.ok().body(messageModel);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Add Role",
                            description = "Menambahkan role baru",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"Success get all role\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"role_id\": 1,\n"
                                    + "      \"role_name\": ADMIN\n"
                                    + "    }\n"
                                    + "    {\n"
                                    + "      \"role_id\": 2,\n"
                                    + "      \"role_name\": CUSTOMER\n"
                                    + "    },\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Update Role",
                            description = "Melakukan update role",
                            value = "{\n"
                                    + "  \"responseCode\": 200,\n"
                                    + "  \"responseMessage\": \"SUpdate role with id : 1\",\n"
                                    + "  \"data\": [\n"
                                    + "    {\n"
                                    + "      \"role_id\": 1,\n"
                                    + "      \"role_name\": ADMIN\n"
                                    + "    }\n"
                                    + "  ]\n"
                                    + "}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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

    @Operation(responses = {
            @ApiResponse(responseCode = "200", content = @Content(examples = {
                    @ExampleObject(name = "Delete Role",
                            description = "Pastikan id role valid.",
                            value = "{\"responseCode\": 200, \"responseMessage\": \"Success delete role by id : 1\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))})
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
