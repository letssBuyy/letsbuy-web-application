package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;

import java.util.List;

public final class ConverterUtils {

    public static Adversiment[] convertListObj(ListObj<Adversiment> adversimentObj) {
        Adversiment [] vetor = new Adversiment[adversimentObj.getTamanho()];

        for(int i = 0; i < adversimentObj.getTamanho(); i++){
            vetor[i] = adversimentObj.getElemento(i);
        }
        return vetor;
    }

    public static Adversiment[] convertList(List<Adversiment> adversimentObj) {
        Adversiment [] vetor = new Adversiment[adversimentObj.size()];

        for(int i = 0; i < adversimentObj.size(); i++){
            vetor[i] = adversimentObj.get(i);
        }
        return vetor;
    }


}
