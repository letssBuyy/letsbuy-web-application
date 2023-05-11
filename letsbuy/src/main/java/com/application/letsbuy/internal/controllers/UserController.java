package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.ImageService;
import com.application.letsbuy.internal.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @ApiOperation("Method used to register users")
    @PostMapping
    public ResponseEntity<UserDtoResponse> register(
            @RequestParam MultipartFile img,
            @RequestBody @Valid UserDto dto, UriComponentsBuilder uriBuilder
    ) {
        System.out.println("ENTREI NA DTO");
        User user = dto.convert();
        user.setProfileImage(imageService.upload(img));
        System.out.println(user);
        userService.save(user);
        System.out.println("DEPOIS DO SAVE");

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDtoResponse(user));
    }

    @ApiOperation("Method used select user adversiments ")
    @GetMapping("/{id}")
    public ResponseEntity<UserAdversimentsDtoResponse> listarUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserAdversimentsDtoResponse(user));
    }

    @ApiOperation("Method used to change user data")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDtoResponse> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userDto) {
        User user = userDto.update(id, userService);
        return ResponseEntity.ok(new UserDtoResponse(user));
    }

    @ApiOperation("Method used to change to user password")
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid PasswordUpdateDto dto) {
        dto.updatePassword(id, userService);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Method used to delete a user")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}

