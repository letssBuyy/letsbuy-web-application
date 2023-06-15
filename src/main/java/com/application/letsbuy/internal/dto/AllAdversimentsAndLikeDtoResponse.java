package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class AllAdversimentsAndLikeDtoResponse {

    private Long userId;
    private UserLikeDto adversiments;
    private Boolean isLike;
    private Long likeId;

    public AllAdversimentsAndLikeDtoResponse(Optional<Long> idUser, Adversiment adversiment, List<AdversimentsLike> likeAdversiments) {
        this.userId = idUser.orElse(null);
        this.adversiments = new UserLikeDto (adversiment);
        this.isLike = false;
        for (AdversimentsLike likeAdversiment : likeAdversiments) {
            if (adversiment.getId().equals(likeAdversiment.getAdversiment().getId())) {
                this.isLike = true;
                this.likeId = likeAdversiment.getId();
            }
        }
    }

    public AllAdversimentsAndLikeDtoResponse(Optional<Long> idUser, Adversiment adversiment, List<AdversimentsLike> likeAdversiments, Long quantityTotalAdversiment, Long quantityTotalSolded, Long quantityTotalActive) {
        this.userId = idUser.orElse(null);
        this.adversiments = new UserLikeDto (adversiment, quantityTotalAdversiment, quantityTotalSolded, quantityTotalActive);
        this.isLike = false;
        for (AdversimentsLike likeAdversiment : likeAdversiments) {
            if (adversiment.getId().equals(likeAdversiment.getAdversiment().getId())) {
                this.isLike = true;
                this.likeId = likeAdversiment.getId();
            }
        }
    }
}