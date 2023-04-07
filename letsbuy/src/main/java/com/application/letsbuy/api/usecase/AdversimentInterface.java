package com.application.letsbuy.api.usecase;

import com.application.letsbuy.internal.entities.Adversiment;

import java.util.List;

public interface AdversimentInterface {
    void save(Adversiment adversiment);
    void deleteById(Long id);
    Adversiment findById(Long id);
    List<Adversiment> findAll();
}
