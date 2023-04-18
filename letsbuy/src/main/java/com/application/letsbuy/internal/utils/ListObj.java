package com.application.letsbuy.internal.utils;

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

    public void limpa() {
        this.nroElem = 0;
    }
}
