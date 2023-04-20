package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.services.UserService;
import com.application.letsbuy.internal.utils.CsvArchiveUtils;
import com.application.letsbuy.internal.utils.ListObj;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/csv")
public class CsvArchiveController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Void> retrieveCsv(@PathVariable Long id) {
        User user = userService.findById(id);
        List<Adversiment> adversimentList = user.getAdversiments();
        if (!adversimentList.isEmpty()) {
            ListObj<Adversiment> adversimentObj = new ListObj<>(adversimentList.size());
            for (Adversiment adversiment : adversimentList) {
                adversimentObj.adiciona(adversiment);
            }
            CsvArchiveUtils.creatCsvArchive(adversimentObj);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
