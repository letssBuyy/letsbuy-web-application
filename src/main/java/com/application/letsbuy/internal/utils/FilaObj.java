package com.application.letsbuy.internal.utils;

public class FilaObj<T>{
    private int tamanho;
    private T[] fila;

    public FilaObj(int capaciade) {
        this.fila = (T[]) new Object[capaciade];
        tamanho = 0;
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    public boolean isFull() {
        return this.tamanho == this.fila.length;
    }

    public void insert(T info) {
        if (this.isFull()) throw new IllegalStateException("Fila cheia");
        this.fila[tamanho++] = info;

    }

    public T peek() {
        return this.fila[0];
    }

    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        T value = this.fila[0];
        for (int i = 0; i < tamanho - 1; i++) {
            fila[i] = fila[i + 1];
        }
        tamanho--;
        this.fila[tamanho] = null;
        return value;
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Lista vazia");
        } else {
            for (int i = 0; i < getTamanho(); i++) {
                System.out.println(this.fila[i]);
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
