package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.AdversimentInterface;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
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
            adversimentRepository.deleteById(id);
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
            throw new AdversimentNotFoundException();
        } else {
            return adversimentRepository.findAll();
        }
    }
}