package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/adversiments")
public class AdversimentController {

    private final AdversimentService adversimentService;

    private final UserService userService;

    @ApiOperation("Method used to list adversiments")
    @GetMapping("/listar")
    public ResponseEntity<Page<AllAdversimentsAndLikeDtoResponse>> retrieveAdversiment(@RequestParam Optional<Long> idUser, Pageable pageable) {
        List<AllAdversimentsAndLikeDtoResponse> lista = this.adversimentService.retrieveAdversiments(idUser, pageable);
        Page<AllAdversimentsAndLikeDtoResponse> pages = new PageImpl<>(lista, pageable, lista.size());
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @ApiOperation("Method used to list adversiments android")
    @GetMapping("/androidAdversiment/{idUser}")
    public ResponseEntity<List<AllAdversimentsAndLikeDtoResponse>> retrieveAdversiment(@PathVariable Optional<Long> idUser) {
        List<AllAdversimentsAndLikeDtoResponse> lista = this.adversimentService.retrieveAdversimentsAndroid(idUser);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @ApiOperation("Method used to register adversiments")
    @PostMapping
    public ResponseEntity<ListAdversimentDtoResponse> createAdversiment(@RequestBody @Valid AdversimentDto adversimentDto) {
        Adversiment adversiment = adversimentDto.convert(this.userService);
        this.adversimentService.save(adversiment);
        return new ResponseEntity<>(new ListAdversimentDtoResponse(adversiment), HttpStatus.CREATED);
    }

    @ApiOperation("Method used to find adversiment by id")
    @GetMapping("/{idAdversiment}")
    public ResponseEntity<List<AllAdversimentsAndLikeDtoResponse>> findAdversimentById(@PathVariable Long idAdversiment, @RequestParam Optional<Long> idUser) {
        return new ResponseEntity<>(this.adversimentService.retrieveAdversimentById(idAdversiment, idUser), HttpStatus.OK);
    }

    @ApiOperation("Method used to find adversiment by userId and state")
    @GetMapping("/filters/{id}/{state}")
    public ResponseEntity<List<AdversimentDtoResponse>> findAdversimentByState(@PathVariable Long id, @PathVariable AdversimentEnum state) {
        return new ResponseEntity<>(this.adversimentService.findByState(id, state), HttpStatus.OK);
    }

    @ApiOperation("Method used to find adversiment bought")
    @GetMapping("/boughts/{id}")
        public ResponseEntity<List<ListAdversimentTrackingDtoResponse>> findAdversimentBoughts(@PathVariable Long id) {
        List<ListAdversimentTrackingDtoResponse> boughts = this.adversimentService.findBought(id);
        if (boughts.isEmpty()){
            return new ResponseEntity<>(boughts, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boughts, HttpStatus.OK);
    }

    @ApiOperation("Method used to count to Adversiments")
    @GetMapping("/qtd-anuncio")
    public ResponseEntity<Long> quantityAds() {
        return new ResponseEntity<>(this.adversimentService.quantityAds(), HttpStatus.OK);
    }

    @ApiOperation("Method used to count to Adversiments finalized")
    @GetMapping("/qtd-anuncio-finalizados")
    public ResponseEntity<Long> amountCompletedAds() {
        return new ResponseEntity<>(this.adversimentService.countFinishedAds(), HttpStatus.OK);
    }

    @GetMapping("/find-quantity-selled-by-month")
    public ResponseEntity<List<QuantitySelledByMonthDto>> findQuantitySelledByMonth() {
        return new ResponseEntity<>(this.adversimentService.findQuantitySelledByMonth(), HttpStatus.OK);
    }

    @GetMapping("/find-selled-by-category")
    public ResponseEntity<List<MostSelledCategoriesDto>> findSalesByCategory() {
        List<MostSelledCategoriesDto> listCategorys = adversimentService.findFiveCategoriesAppearTheMost();
        if(listCategorys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listCategorys, HttpStatus.OK);
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
    public ResponseEntity<Resource> exportTxt(@PathVariable Long id, @RequestParam Optional<String> nomeArquivo) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", nomeArquivo.map(s -> s + ".txt").orElse("meus-anuncios.txt"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(this.adversimentService.createTxtResource(id, nomeArquivo));
    }

    @PostMapping(value = "/import-txt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importTxt(@RequestBody MultipartFile arquivo) {

        try {
            if (!arquivo.isEmpty() && Objects.equals(arquivo.getContentType(), "text/plain")) {
                byte[] conteudo = arquivo.getBytes();
                String texto = new String(conteudo);
                adversimentService.importFileTxt(texto);
                return new ResponseEntity<>("Arquivo TXT importado com sucesso", HttpStatus.OK);
            } else {

                return new ResponseEntity<>("Erro: Por favor, envie um arquivo TXT.", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {

            return new ResponseEntity<>("Erro ao processar o arquivo: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/export-csv/{id}")
    public ResponseEntity<Resource> retrieveCsv(@PathVariable Long id, @RequestParam Optional<String> nomeArquivo) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", nomeArquivo.map(s -> s + ".csv").orElse("meus-anuncios.csv"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(this.adversimentService.createCsvResource(id, nomeArquivo));
    }
}
