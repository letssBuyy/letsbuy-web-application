package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public final class AdversimentUtils {
    private AdversimentUtils() {
    }
    public static Adversiment mockAdversiment() {
        Adversiment adversiment = new Adversiment();
        adversiment.setId(1000L);
        adversiment.setUser(UserUtils.mockUser());
        adversiment.setTitle("IPHONE 14");
        adversiment.setDescription("Celular novo, mal foi usado.");
        adversiment.setPrice(7000.0);
        adversiment.setPostDate(LocalDate.now());
        adversiment.setLastUpdate(LocalDate.now());
        adversiment.setSaleDate(LocalDate.now());
        adversiment.setCategory(CategoryEnum.ELECTRONICS);
        adversiment.setQuality(QualityEnum.SEMI_NEW);
        return adversiment;
    }

    public static void gravaRegistro(String registro, String archiveName) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(archiveName, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo");
        }
    }

    public static void gravaArquivoTxt(List<Adversiment> lista, Optional<String> nomeArq) {

        String archiveName = nomeArq.orElse("adversiments");
        archiveName += ".txt";
        int contaRegistroDado = 0;
        Double valorTotal = 0.0;

        String header = "00ANUNCIO2023";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        gravaRegistro(header, archiveName);

        String corpo;
        for (Adversiment a : lista) {
            corpo = "01";
            corpo += String.format("%010d",a.getId());
            corpo += String.format("%-50.50s",a.getTitle());
            corpo += String.format("%-255.255s",a.getDescription());
            corpo += String.format("%10.2f",a.getPrice());
            corpo += String.format("%-10.10s",a.getPostDate());
            corpo += String.format("%-10.10s",a.getLastUpdate());
            corpo += String.format("%-18.18s",a.getCategory());
            corpo += String.format("%-9.9s",a.getQuality());
            corpo += String.format("%09d",a.getUser().getId());
            gravaRegistro(corpo, archiveName);
            contaRegistroDado++;
            valorTotal += a.getPrice();
        }

        String trailer = "02";
        trailer += String.format("%010d",contaRegistroDado);
        trailer += String.format("%16.2f", valorTotal);
        gravaRegistro(trailer, archiveName);
    }
}
