package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.AdversimentsLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdversimentsLikeDtoResponse {

    private Long id;
    private Long userId;
    private UserLikeDto adversiments;

    public AdversimentsLikeDtoResponse(AdversimentsLike adversimentsLike) {
        this.id = adversimentsLike.getId();
        this.userId = adversimentsLike.getUser().getId();
        this.adversiments = new UserLikeDto (adversimentsLike.getAdversiment());
    }

    public static List<AdversimentsLikeDtoResponse> convert(List<AdversimentsLike> adversiments) {
        return adversiments.stream().map(AdversimentsLikeDtoResponse::new).collect(Collectors.toList());
    }
}
