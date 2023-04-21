package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.AdversimentDto;
import com.application.letsbuy.internal.dto.AdversimentDtoResponse;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/adversiments")
public class AdversimentController {
    private final AdversimentService adversimentService;
    private final UserService userService;

    @ApiOperation("Method used to list adversiments")
    @GetMapping
    public ResponseEntity<List<AdversimentDtoResponse>> retrieveAdversiment() {
        List<Adversiment> adversiments = adversimentService.findAll();
        return ResponseEntity.ok().body(AdversimentDtoResponse.convert(adversiments));
    }

    @ApiOperation("Method used to register adversiments")
    @PostMapping
    public ResponseEntity<AdversimentDtoResponse> createAdversiment(@RequestBody AdversimentDto adversimentDto) {
        Adversiment adversiment = adversimentDto.convert(userService);
        adversimentService.save(adversiment);
        return ResponseEntity.status(201).body(new AdversimentDtoResponse(adversiment));
    }

    @ApiOperation("Method used to find adversiment by id")
    @GetMapping("/{id}")
    public ResponseEntity<AdversimentDtoResponse> findAdversiment(@PathVariable Long id) {
        Adversiment adversiment = adversimentService.findById(id);
        return ResponseEntity.ok().body(new AdversimentDtoResponse(adversiment));
        // retorna as informações do usuario deste anuncio
    }

    @GetMapping("/search-binary-price/{id}/{price}")
    public ResponseEntity<AdversimentDtoResponse> findByPrice(@PathVariable Long id, @PathVariable Double price) {
        User user = userService.findById(id);
        List<Adversiment> adversimentList = user.getAdversiments();
        Adversiment adversiment = adversimentService.searchBinary(adversimentList, price);
        return ResponseEntity.ok().body(new AdversimentDtoResponse(adversiment));
    }

    @ApiOperation("Method used to update adversiment by id")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AdversimentDtoResponse> updateAdversiment(@PathVariable Long id, @RequestBody AdversimentDto adversimentDto) {
        Adversiment adversiment = adversimentDto.update(id, adversimentService);
        return ResponseEntity.ok().body(new AdversimentDtoResponse(adversiment));
        //receber o id do user para poder realizar a alteração e adicionar um boollea de para verificar se foi vendido
    }

    @ApiOperation("Method used to delete adversiment by id")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adversimentService.deleteById(id);
        return ResponseEntity.ok().build();
        //verificar o id do user se ele pertence aquele anucio antes de deletar;
    }
}
