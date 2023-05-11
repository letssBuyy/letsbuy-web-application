package com.application.letsbuy.api.usecase;

import com.application.letsbuy.internal.entities.Adversiment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdversimentInterface {
    void save(Adversiment adversiment);
    void deleteById(Long id);
    Adversiment findById(Long id);
    Adversiment insertImages(List<MultipartFile> images,Adversiment adversiment);
    List<Adversiment> findAll();
    Adversiment searchBinary(List<Adversiment> adversimentList, Double price);
    Adversiment openContest(Long id);
}

