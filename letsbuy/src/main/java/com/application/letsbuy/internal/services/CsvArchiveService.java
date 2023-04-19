package com.application.letsbuy.internal.services;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.utils.AdversimentUtils;
import com.application.letsbuy.internal.utils.ListObj;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

@Service
public class CsvArchiveService {

    public static void creatCsvArchive(ListObj<Adversiment> adversimentList) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArquivo = "adversiments";

        nomeArquivo += ".csv";

        try {
            arq = new FileWriter(nomeArquivo);
            saida = new Formatter(arq);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo " + nomeArquivo);
            System.exit(1);
        }
        try {
            for (int i = 0; i < adversimentList.getTamanho(); i++) {
                Adversiment adversiment = adversimentList.getElemento(i);
                saida.format("%s;%s;%s;%.2f;%s;%s;%s;%s;%s;%s\n", adversiment.getId().toString(),
                        adversiment.getTitle(),
                        adversiment.getDescription(),
                        adversiment.getPrice(),
                        adversiment.getPostDate().toString(),
                        adversiment.getLastUpdate().toString(),
                        adversiment.getSaleDate().toString(),
                        adversiment.getCategory().toString(),
                        adversiment.getQuality().toString(),
                        adversiment.getUser().getId().toString());
            }
        } catch (FormatterClosedException e) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }    }


    public static void main(String[] args) {
      Adversiment adversiment = AdversimentUtils.createAdversiment();
      ListObj<Adversiment> list = new ListObj<>(2);
      list.adiciona(adversiment);
      creatCsvArchive(list);
    }
}
