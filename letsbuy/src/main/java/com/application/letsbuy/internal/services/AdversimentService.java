package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.AdversimentInterface;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.exceptions.AdversimentNoContentException;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.utils.ConverterUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdversimentService implements AdversimentInterface {
    private AdversimentRepository adversimentRepository;

    @Override
    public void save(Adversiment adversiment) {
        adversimentRepository.save(adversiment);
    }

    @Override
    public void deleteById(Long id) {
        if (adversimentRepository.findById(id).isPresent()) {
            Adversiment adversiment = adversimentRepository.findById(id).get();
            adversiment.setIsActive(AdversimentEnum.INACTIVE);
        } else {
            throw new AdversimentNotFoundException();
        }
    }

    @Override
    public Adversiment findById(Long id) {
        Optional<Adversiment> retrieveAdversimentById = adversimentRepository.findById(id);
        if (retrieveAdversimentById.isPresent()) {
            return retrieveAdversimentById.get();
        } else {
            throw new AdversimentNotFoundException();
        }
    }

    @Override
    public List<Adversiment> findAll() {
        if (adversimentRepository.findAll().isEmpty()) {
            throw new AdversimentNoContentException();
        } else {
            return adversimentRepository.findAll();
        }
    }

    @Override
    public Adversiment openContest(Long id) {
        if (adversimentRepository.findById(id).isPresent()) {
            Adversiment adversiment = adversimentRepository.findById(id).get();
            adversiment.setContest(AdversimentEnum.ACTIVE);
            return adversiment;
        } else {
            throw new AdversimentNotFoundException();
        }
    }

    @Override
    public Adversiment searchBinary(List<Adversiment> adversimentList, Double price) {
        if (adversimentList.isEmpty()) {
            throw new AdversimentNoContentException();
        }
        Adversiment[] vetor = ConverterUtils.convertList(adversimentList);

        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 1; j < vetor.length - i; j++) {

                if (vetor[j - 1].getPrice() > vetor[j].getPrice()) {
                    Adversiment aux = vetor[j];
                    vetor[j] = vetor[j - 1];
                    vetor[j - 1] = aux;
                }
            }
        }
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (price.equals(vetor[meio].getPrice())) {
                Long adversimentId = vetor[meio].getId();
                Adversiment adversiment = findById(adversimentId);
                System.out.println(adversiment);
                return adversiment;

            } else if (price > vetor[meio].getPrice()) {
                inicio = meio + 1;

            } else {
                fim = meio - 1;
            }
        }
        throw new AdversimentNotFoundException();
    }
}

