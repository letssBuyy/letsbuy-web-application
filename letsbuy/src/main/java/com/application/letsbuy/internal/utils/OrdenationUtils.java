package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;

public final class OrdenationUtils {

    public static void orderByPrice(ListObj<> adversimentObj){

        for(int i = 0; i < adversimentObj.length-1; i++){

            for(int j = 1; j < adversimentObj.length-i; j++){

                if(adversimentObj[j-1] > adversimentObj[j]){
                    int aux = adversimentObj[j];
                    adversimentObj[j] = adversimentObj[j-1];
                    adversimentObj[j-1] = aux;
                }
            }
        }


    }
}
