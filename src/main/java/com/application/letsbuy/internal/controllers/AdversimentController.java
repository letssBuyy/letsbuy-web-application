package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.UserService;
import com.application.letsbuy.internal.utils.AdversimentUtils;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/adversiments")
public class AdversimentController {

    private final AdversimentService adversimentService;

    private final UserService userService;

    @ApiOperation("Method used to list adversiments")
    @GetMapping("/listar")
    public ResponseEntity<List<AllAdversimentsAndLikeDtoResponse>> retrieveAdversiment(@RequestParam Optional<Long> idUser) {
        return new ResponseEntity<>(this.adversimentService.retrieveAdversiments(idUser), HttpStatus.OK);
    }

    @ApiOperation("Method used to register adversiments")
    @PostMapping
    public ResponseEntity<ListAdversimentDtoResponse> createAdversiment(@RequestBody @Valid AdversimentDto adversimentDto) {
        Adversiment adversiment = adversimentDto.convert(this.userService);
        this.adversimentService.save(adversiment);
        return new ResponseEntity<>(new ListAdversimentDtoResponse(adversiment), HttpStatus.CREATED);
    }

    @ApiOperation("Method used to find adversiment by id")
    @GetMapping("/{idAdversiment}/{idUser}")
    public ResponseEntity<List<AllAdversimentsAndLikeDtoResponse>> findAdversimentById(@PathVariable Long idAdversiment, @PathVariable Long idUser) {
        return new ResponseEntity<>(this.adversimentService.retrieveAdversimentById(idAdversiment, idUser), HttpStatus.OK);
    }

    @GetMapping("/search-binary-price/{id}/{price}")
    public ResponseEntity<ListAdversimentDtoResponse> findByPrice(@PathVariable Long id, @PathVariable Double price) {
        User user = userService.findById(id);
        List<Adversiment> adversimentList = user.getAdversiments();
        Adversiment adversiment = adversimentService.searchBinary(adversimentList, price);
        return new ResponseEntity<>(new ListAdversimentDtoResponse(adversiment), HttpStatus.OK);
    }

    @ApiOperation("Method used to update adversiment by id")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ListAdversimentDtoResponse> updateAdversiment(@PathVariable Long id, @RequestBody @Valid AdversimentDto adversimentDto) {
        return new ResponseEntity<>(new ListAdversimentDtoResponse(adversimentDto.update(id, this.adversimentService)), HttpStatus.OK);
    }

    @ApiOperation("Method used to delete adversiment by id")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adversimentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Method used to open contest")
    @PatchMapping("/contest/{id}")
    @Transactional
    public ResponseEntity<AdversimentDtoResponse> contest(@PathVariable Long id) {
        return new ResponseEntity<>(new AdversimentDtoResponse(this.adversimentService.openContest(id)), HttpStatus.OK);
    }

    @ApiOperation("Method to perform the like of a specific adversiments")
    @PostMapping("/like/{idUser}/{idAdversiment}")
    public ResponseEntity<Void> likeAdversiments(@PathVariable Long idUser, @PathVariable Long idAdversiment) {
        this.adversimentService.likeAdversiment(idUser, idAdversiment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/like/{idUser}")
    public ResponseEntity<List<AdversimentsLikeDtoResponse>> retriveAdversimentsLike(@PathVariable Long idUser) {
        return new ResponseEntity<>(AdversimentsLikeDtoResponse.convert(this.adversimentService.findByAdversimentsLike(idUser)), HttpStatus.OK);
    }

    @ApiOperation("Method to perform the deslike of a specific adversiments")
    @DeleteMapping("/deslike/{idAdversimentLike}")
    public ResponseEntity<Void> deslikeAdversiments(@PathVariable Long idAdversimentLike) {
        this.adversimentService.deslike(idAdversimentLike);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/export-txt/{id}")
    public ResponseEntity<Void> exportTxt(@PathVariable Long id){
        List<Adversiment> adversiments = this.userService.findById(id).getAdversiments();
        AdversimentUtils.gravaArquivoTxt(adversiments, "adversiments");
        if(adversiments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/import-txt/{nomeArq}")
    public ResponseEntity<String> importTxt(@PathVariable String nomeArq) {
        adversimentService.importFileTxt(nomeArq);
        return new ResponseEntity<>("Arquivo TXT importado!", HttpStatus.OK);
    }

    @GetMapping("/csv/{id}")
    public ResponseEntity<Void> retrieveCsv(@PathVariable Long id, @RequestParam Optional<String> nomeArquivo) {
        this.adversimentService.createCsvArchive(id, nomeArquivo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
