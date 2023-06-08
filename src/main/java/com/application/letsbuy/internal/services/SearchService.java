package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.AllAdversimentsAndLikeDtoResponse;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchService {

    private final AdversimentService adversimentService;
    private final AdversimentRepository adversimentRepository;
    public List<AllAdversimentsAndLikeDtoResponse> searchAdversiments(Optional<Long> idUser, String title) {
        List<Adversiment> adversiments = adversimentRepository.findByTitleContainsIgnoreCaseAndIsActive(title, AdversimentEnum.ACTIVE);

        List<AdversimentsLike> likedAdversiments = new ArrayList<>();
        if (idUser.isPresent()) {
            likedAdversiments = adversimentService.findByAdversimentsLike(idUser.get());
        }
        List<AllAdversimentsAndLikeDtoResponse> allAdversimentslikes = new ArrayList<>();
        for (Adversiment adversiment : adversiments) {
            allAdversimentslikes.add(new AllAdversimentsAndLikeDtoResponse(idUser, adversiment, likedAdversiments));
        }
        return allAdversimentslikes;
    }

}
