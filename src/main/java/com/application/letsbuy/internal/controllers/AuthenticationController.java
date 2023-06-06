package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.config.security.TokenService;
import com.application.letsbuy.internal.dto.AuthenticationRequestDto;
import com.application.letsbuy.internal.dto.TokenDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<TokenDto> autenticador(@RequestBody @Valid AuthenticationRequestDto dto) {
        try {
            Authentication authentication = this.authManager.authenticate(dto.convert());
            return new ResponseEntity<>(new TokenDto(this.tokenService.generateToken(authentication), "Bearer"), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
