package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ListObj<T>{

    private T[] vetor;
    private int nroElem;

    public ListObj(int tamanhoMaximo) {
        this.vetor = (T[]) new Object[tamanhoMaximo];
        this.nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (this.nroElem == this.vetor.length) {
            System.out.println("Lista cheia");
            return;
        }
        this.vetor[this.nroElem] = elemento;
        this.nroElem++;
    }

    public void exibe() {
        for (int i = 0; i < this.nroElem; i++) {
            System.out.print(this.vetor[i] + " ");
        }
        System.out.println();
    }

    public int busca(T elemento) {
        for (int i = 0; i < this.nroElem; i++) {
            if (this.vetor[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= this.nroElem) {
            return false;
        }
        for (int i = indice; i < this.nroElem - 1; i++) {
            this.vetor[i] = this.vetor[i + 1];
        }
        this.nroElem--;
        return true;
    }

    public boolean removeElemento(T elemento) {
        int indice = this.busca(elemento);
        if (indice == -1) {
            return false;
        }
        return this.removePeloIndice(indice);
    }

    public int getTamanho() {
        return this.nroElem;
    }
    public T getElemento(int indice) {
        if (indice < 0 || indice >= this.nroElem) {
            return null;
        }
       return this.vetor[indice];
    }

    public static ListObj<Adversiment> orderByPrice(ListObj<Adversiment> adversimentObj){

       Adversiment[] vetor = ConverterUtils.convertListObj(adversimentObj);
       ListObj<Adversiment> adversimentOrderly = new ListObj<>(adversimentObj.getTamanho());

        for(int i = 0; i < vetor.length-1; i++){
            for(int j = 1; j < vetor.length-i; j++){

                if(vetor[j-1].getPrice() > vetor[j].getPrice()){
                    Adversiment aux = vetor[j];
                    vetor[j] = vetor[j-1];
                    vetor[j-1] = aux;
                }
            }
        }

        for(int i = 0; i < vetor.length; i++){
            adversimentOrderly.adiciona(vetor[i]);
        }
        return adversimentOrderly;
    }

    public void limpa() {
        this.nroElem = 0;
    }
}
