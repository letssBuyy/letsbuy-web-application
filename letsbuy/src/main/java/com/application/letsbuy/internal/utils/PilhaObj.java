package com.application.letsbuy.internal.utils;

public class PilhaObj<T> {
    private T[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T info) {
        if (!isFull()) {
            pilha[++topo] = info;
        } else {
            throw new IllegalStateException("Pilha cheia!");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            return pilha[topo--];
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return pilha[topo];
        }
        return null;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha esta vazia");
        } else {
            for (int i = topo; i >= 0 ; i--) {
                System.out.println(pilha[i]);
            }
        }
    }

    public int getTopo() {
        return topo;
    }
}
