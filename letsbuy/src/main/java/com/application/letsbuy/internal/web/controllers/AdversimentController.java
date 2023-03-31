package com.application.letsbuy.internal.web.controllers;

import com.application.letsbuy.internal.dto.AdversimentDto;
import com.application.letsbuy.internal.dto.AdversimentDtoResponse;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.services.AdversimentService;
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

    @ApiOperation("Method used to list adversiments")
    @GetMapping
    public ResponseEntity<List<AdversimentDtoResponse>> list(){

        List<Adversiment> adversiments = adversimentService.findAll();

        return ResponseEntity.status(200).body(AdversimentDtoResponse.convert(adversiments));

    }

    @ApiOperation("Method used to register adversiments")
    @PostMapping
    public ResponseEntity<AdversimentDtoResponse> createAdversiment(@RequestBody AdversimentDto adversimentDto){

        Adversiment adversiment = adversimentDto.convert();

        adversimentService.save(adversiment);

        return ResponseEntity.status(201).body(new AdversimentDtoResponse(adversiment));

    }

    @ApiOperation("Method used to find adversiment by id")
    @GetMapping("{/id}")
    public ResponseEntity<AdversimentDtoResponse> findAdversiment(@PathVariable Long id){

        Adversiment adversiment = adversimentService.findById(id);

        return ResponseEntity.status(200).body(new AdversimentDtoResponse(adversiment));

    }

    @ApiOperation("Method used to update adversiment by id")
    @PutMapping("{/id}")
    @Transactional
    public ResponseEntity<AdversimentDtoResponse> updateAdversiment(@PathVariable Long id, @RequestBody AdversimentDto adversimentDto){

       Adversiment adversiment = adversimentDto.update(id, adversimentService);

       return ResponseEntity.status(200).body(new AdversimentDtoResponse(adversiment));
    }

    @ApiOperation("Method used to delete adversiment by id")
    @DeleteMapping("{/id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adversimentService.deleteById(id);
        return ResponseEntity.status(200).build();
    }


}
