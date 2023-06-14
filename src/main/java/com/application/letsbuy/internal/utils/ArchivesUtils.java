package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@UtilityClass
public class ArchivesUtils {

    public byte[] generateCsvBytes(ListObj<Adversiment> adversimentList, Optional<String> nomeArquivo) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);

        for (int i = 0; i < adversimentList.getTamanho(); i++) {
            Adversiment adversiment = adversimentList.getElemento(i);
            writer.format("%s;%s;%s;%.2f;%s;%s;%s;%s;%s;%s%n", adversiment.getId().toString(),
                    adversiment.getTitle() == null ? "N/A" : adversiment.getTitle(),
                    adversiment.getDescription() == null ? "N/A" : adversiment.getDescription(),
                    adversiment.getPrice() == null ? 0.0 : adversiment.getPrice(),
                    adversiment.getPostDate()  == null ? "N/A" : adversiment.getPostDate().toString(),
                    adversiment.getLastUpdate() == null ? "N/A" : adversiment.getLastUpdate().toString(),
                    adversiment.getSaleDate() == null ? "N/A" : adversiment.getSaleDate().toString(),
                    adversiment.getCategory() == null ? "N/A" : adversiment.getCategory().toString(),
                    adversiment.getQuality()  == null ? "N/A" : adversiment.getQuality().toString(),
                    adversiment.getUser().getId()  == null ? "N/A" : adversiment.getUser().getId().toString());
        }
        writer.flush();
        writer.close();

        return outputStream.toByteArray();
    }

    public void gravaRegistro(ByteArrayOutputStream outputStream, String registro) {
        try {
            outputStream.write(registro.getBytes());
            outputStream.write(System.lineSeparator().getBytes());
        } catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo");
        }
    }



}
