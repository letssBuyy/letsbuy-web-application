package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.dto.AdversimentFilterDto;
import com.application.letsbuy.internal.dto.AllAdversimentsAndLikeDtoResponse;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.utils.FilaObj;
import com.application.letsbuy.internal.utils.PilhaObj;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchService {

    private final AdversimentService adversimentService;

    private final AdversimentRepository adversimentRepository;

    public List<AllAdversimentsAndLikeDtoResponse> searchAdversiments(Optional<Long> idUser, Optional<String> title, Pageable pageable) {
        Page<Adversiment> adversiments;
        if(title.isPresent()) {
            adversiments = adversimentRepository.findByTitleContainsIgnoreCaseAndIsActive(title, AdversimentEnum.ACTIVE, pageable);

        } else {
            adversiments = adversimentRepository.findByIsActive(AdversimentEnum.ACTIVE, pageable);
        }

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

    public List<AllAdversimentsAndLikeDtoResponse> searchAdversimentsFilter(List<AllAdversimentsAndLikeDtoResponse> results, AdversimentFilterDto filter) {

        List<AllAdversimentsAndLikeDtoResponse> filteredResultsCity = filterCity(results, filter.getCity());
        List<AllAdversimentsAndLikeDtoResponse> filteredResultsPriceMin = filterPriceMin(filteredResultsCity, filter.getPriceMin());
        List<AllAdversimentsAndLikeDtoResponse> filteredResultsPriceMax = filterPriceMax(filteredResultsPriceMin, filter.getPriceMax());
        List<AllAdversimentsAndLikeDtoResponse> filteredResultsQuality = filterQuality(filteredResultsPriceMax, filter.getQuality());
        List<AllAdversimentsAndLikeDtoResponse> filteredResultsCategory = filterCategory(filteredResultsQuality, filter.getCategory());
        List<AllAdversimentsAndLikeDtoResponse> filteredResultsColor = filterColor(filteredResultsCategory, filter.getColor());

        return switch (filter.getFilter()) {
            case 1 -> orderByMostRecent(filteredResultsColor);
            case 2 -> orderByOldest(filteredResultsColor);
            case 3 -> orderByLowestPrice(filteredResultsColor);
            case 4 -> orderByHighestPrice(filteredResultsColor);
            default -> filteredResultsColor;
        };
    }

    private List<AllAdversimentsAndLikeDtoResponse> filterCity(List<AllAdversimentsAndLikeDtoResponse> results, String city) {

        if (city != null && !city.isEmpty()) {
            List<AllAdversimentsAndLikeDtoResponse> filteredResults = new ArrayList<>();
            for (AllAdversimentsAndLikeDtoResponse a : results) {
                    if (a.getAdversiments().getUserSellerLikeDto().getCity() != null && a.getAdversiments().getUserSellerLikeDto().getCity().equalsIgnoreCase(city)) {
                        filteredResults.add(a);
                    }
            }
            return filteredResults;
        }
        return results;
    }

    private List<AllAdversimentsAndLikeDtoResponse> filterPriceMin(List<AllAdversimentsAndLikeDtoResponse> results, Double priceMin) {

        if (priceMin != null && priceMin > 0) {
            List<AllAdversimentsAndLikeDtoResponse> filteredResults = new ArrayList<>();
            for (AllAdversimentsAndLikeDtoResponse a : results) {
                if (a.getAdversiments().getPrice() >= priceMin) {
                    filteredResults.add(a);
                }
            }
            return filteredResults;
        }
        return results;
    }

    private List<AllAdversimentsAndLikeDtoResponse> filterPriceMax(List<AllAdversimentsAndLikeDtoResponse> results, Double priceMax) {

        if (priceMax != null && priceMax > 0) {
            List<AllAdversimentsAndLikeDtoResponse> filteredResults = new ArrayList<>();
            for (AllAdversimentsAndLikeDtoResponse a : results) {
                if (a.getAdversiments().getPrice() <= priceMax) {
                    filteredResults.add(a);
                }
            }
            return filteredResults;
        }
        return results;
    }

    private List<AllAdversimentsAndLikeDtoResponse> filterQuality(List<AllAdversimentsAndLikeDtoResponse> results, QualityEnum quality) {

        if (quality != null) {
            List<AllAdversimentsAndLikeDtoResponse> filteredResults = new ArrayList<>();
            for (AllAdversimentsAndLikeDtoResponse a : results) {
                if (a.getAdversiments().getQuality().equals(quality)) {
                    filteredResults.add(a);
                }
            }
            return filteredResults;
        }
        return results;
    }

    private List<AllAdversimentsAndLikeDtoResponse> filterCategory(List<AllAdversimentsAndLikeDtoResponse> results, CategoryEnum category) {

        if (category != null) {
            List<AllAdversimentsAndLikeDtoResponse> filteredResults = new ArrayList<>();
            for (AllAdversimentsAndLikeDtoResponse a : results) {
                if (a.getAdversiments().getCategory().equals(category)) {
                    filteredResults.add(a);
                }
            }
            return filteredResults;
        }
        return results;
    }

    private List<AllAdversimentsAndLikeDtoResponse> filterColor(List<AllAdversimentsAndLikeDtoResponse> results, AdversimentColorEnum color) {

        if (color != null) {
            List<AllAdversimentsAndLikeDtoResponse> filteredResults = new ArrayList<>();
            for (AllAdversimentsAndLikeDtoResponse a : results) {
                if (a.getAdversiments().getColor().equals(color)) {
                    filteredResults.add(a);
                }
            }
            return filteredResults;
        }
        return results;
    }

    private List<AllAdversimentsAndLikeDtoResponse> orderByLowestPrice(List<AllAdversimentsAndLikeDtoResponse> filteredResultsColor) {
        return quicksortMenor(filteredResultsColor, 0, filteredResultsColor.size() - 1);
    }

    private List<AllAdversimentsAndLikeDtoResponse> orderByHighestPrice(List<AllAdversimentsAndLikeDtoResponse> filteredResultsColor) {
        return quicksortMaior(filteredResultsColor, 0, filteredResultsColor.size() - 1);
    }

    public static List<AllAdversimentsAndLikeDtoResponse> quicksortMenor(List<AllAdversimentsAndLikeDtoResponse> lista, int esquerda, int direita) {
        if (esquerda < direita) {
            int pivo = particionarMenor(lista, esquerda, direita);
            quicksortMenor(lista, esquerda, pivo - 1);
            quicksortMenor(lista, pivo + 1, direita);
        }
        return lista;
    }

    public static int particionarMenor(List<AllAdversimentsAndLikeDtoResponse> lista, int esquerda, int direita) {
        double pivo = lista.get(direita).getAdversiments().getPrice();
        int i = esquerda - 1;

        for (int j = esquerda; j < direita; j++) {
            if (lista.get(j).getAdversiments().getPrice() < pivo) {
                i++;
                Collections.swap(lista, i, j);
            }
        }
        Collections.swap(lista, i + 1, direita);
        return i + 1;
    }

    public static List<AllAdversimentsAndLikeDtoResponse> quicksortMaior(List<AllAdversimentsAndLikeDtoResponse> produtos, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionarMaior(produtos, inicio, fim);
            quicksortMaior(produtos, inicio, indicePivo - 1);
            quicksortMaior(produtos, indicePivo + 1, fim);
        }
        return produtos;
    }

    public static int particionarMaior(List<AllAdversimentsAndLikeDtoResponse> produtos, int inicio, int fim) {
        AllAdversimentsAndLikeDtoResponse pivo = produtos.get(fim);
        int indicePivo = inicio;
        for (int i = inicio; i < fim; i++) {
            if (produtos.get(i).getAdversiments().getPrice() >= pivo.getAdversiments().getPrice()) {
                trocarMaior(produtos, i, indicePivo);
                indicePivo++;
            }
        }
        trocarMaior(produtos, indicePivo, fim);
        return indicePivo;
    }

    public static void trocarMaior(List<AllAdversimentsAndLikeDtoResponse> produtos, int i, int j) {
        AllAdversimentsAndLikeDtoResponse temp = produtos.get(i);
        produtos.set(i, produtos.get(j));
        produtos.set(j, temp);
    }

    private List<AllAdversimentsAndLikeDtoResponse> orderByMostRecent(List<AllAdversimentsAndLikeDtoResponse> filteredResultsColor) {
        PilhaObj<AllAdversimentsAndLikeDtoResponse> pilhaObj = new PilhaObj(filteredResultsColor.size());

        for (AllAdversimentsAndLikeDtoResponse a : filteredResultsColor) {
            pilhaObj.push(a);
        }

        List<AllAdversimentsAndLikeDtoResponse> listOrdened = new ArrayList<>();

        for (int i = pilhaObj.getTopo(); i >= 0; i--) {
            listOrdened.add(pilhaObj.pop());
        }

        return listOrdened;
    }

    private List<AllAdversimentsAndLikeDtoResponse> orderByOldest(List<AllAdversimentsAndLikeDtoResponse> filteredResultsColor) {
        FilaObj<AllAdversimentsAndLikeDtoResponse> filaObj = new FilaObj<>(filteredResultsColor.size());

        for (AllAdversimentsAndLikeDtoResponse allAdversimentsAndLikeDtoResponse : filteredResultsColor) {
            filaObj.insert(allAdversimentsAndLikeDtoResponse);
        }

        List<AllAdversimentsAndLikeDtoResponse> listOrdened = new ArrayList<>();
        int tamanho = filaObj.getTamanho();
        for (int i = 0; i < tamanho; i++) {
            listOrdened.add(filaObj.peek());
            filaObj.poll();
        }

        return listOrdened;
    }
}

