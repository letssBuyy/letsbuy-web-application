package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.UserDto;
import com.application.letsbuy.internal.dto.UserDtoResponse;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ApiOperation("Method used to register users")
    @PostMapping
    public ResponseEntity<UserDtoResponse> register(@RequestBody UserDto dto, UriComponentsBuilder uriBuilder) {
        User user = dto.convert();
        userService.save(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDtoResponse(user));
    }

    @ApiOperation("Method used to change user data")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDtoResponse> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        User user = userDto.update(id, userService);
        return ResponseEntity.ok(new UserDtoResponse(user));
    }

    @ApiOperation("Method used to delete a user")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
