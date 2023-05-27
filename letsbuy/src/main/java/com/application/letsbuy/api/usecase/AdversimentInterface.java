package com.application.letsbuy.api.usecase;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdversimentInterface {
    void save(Adversiment adversiment);
    void deleteById(Long id);
    Adversiment findById(Long id);
    List<Adversiment> findAll();
    Adversiment searchBinary(List<Adversiment> adversimentList, Double price);
    Adversiment openContest(Long id);
    Adversiment insertImages(Long id, List<MultipartFile> images);
    void likeAdversiment(Long idUser, Long idAdversiment);
    void deslike(Long id);
    List<AdversimentsLike> findAllAdversimentsLike();
    List<AdversimentsLike> findByAdversimentsLike(Long id);
}

