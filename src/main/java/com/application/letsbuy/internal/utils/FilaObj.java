package com.application.letsbuy.internal.utils;

public class FilaObj<T> {

    private T[] fila;
    private int tamanho;


    public FilaObj(int capaciade) {
        this.fila = (T[]) new Object[capaciade];
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        return getTamanho() == 0;
    }

    public boolean isFull() {
        return getTamanho() == fila.length;
    }

    public void insert(T info) {
        if (isFull()) {
            throw new IllegalStateException("Fila cheia!");
        }
        fila[tamanho++] = info;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return fila[0];
    }

    public T poll() {

        if (isEmpty()) {
            return null;
        }

        T aux = fila[0];

        for (int i = 0; i < getTamanho() - 1; i++) {
            fila[i] = fila[i + 1];
        }

        tamanho--;
        fila[tamanho] = null;
        return aux;
    }

    public void exibe() {

        if (isEmpty()) {
            System.out.println("Fila vazia!");
        } else {
            for (int i = 0; i < getTamanho(); i++) {
                System.out.print(fila[i] + "\t");
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
