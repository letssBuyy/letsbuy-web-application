package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.config.security.TokenService;
import com.application.letsbuy.internal.dto.AuthenticationRequestDto;
import com.application.letsbuy.internal.dto.TokenDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    @ApiOperation("Authenticate user login")
    @PostMapping
    public ResponseEntity<TokenDto> autenticador(@RequestBody AuthenticationRequestDto dto) {
        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword());
        UsernamePasswordAuthenticationToken dataLogin = dto.convert();
        System.out.println(dataLogin);
        try {
            Authentication authentication = authManager.authenticate(dataLogin);
            String token = tokenService.generateToken(authentication);
            return  ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
