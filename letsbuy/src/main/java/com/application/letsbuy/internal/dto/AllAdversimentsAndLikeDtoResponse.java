package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllAdversimentsAndLikeDtoResponse {

    private Long userId;
    private UserLikeDto adversiments;
    private Boolean isLike;
    private Long likeId;

    public AllAdversimentsAndLikeDtoResponse(Long idUser, Adversiment adversiment, List<AdversimentsLike> likeAdversiments) {
        this.userId = idUser;
        this.adversiments = new UserLikeDto (adversiment);
        this.isLike = false;
        for (int i = 0; i < likeAdversiments.size(); i++) {
            if (adversiment.getId().equals(likeAdversiments.get(i).getAdversiment().getId())) {
                this.isLike = true;
                this.likeId = likeAdversiments.get(i).getId();
            }
        }
    }
}
