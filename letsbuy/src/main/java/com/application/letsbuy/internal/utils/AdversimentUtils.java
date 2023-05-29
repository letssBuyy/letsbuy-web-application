package com.application.letsbuy.internal.utils;

import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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


    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // try-catch para gravar o registro e finalizar
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo");
        }
    }

    public static void gravaArquivoTxt(List<Adversiment> lista, String nomeArq) {
        nomeArq += ".txt";
        int contaRegistroDado = 0;

        // Monta o registro de header
        String header = "00ANUNCIO2023";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de dados ou registros de corpo
        String corpo;
        for (Adversiment a : lista) {
            corpo = "01";
            corpo += String.format("%10.10s",a.getId());
            corpo += String.format("%-50.50s",a.getTitle());
            corpo += String.format("%-255.255s",a.getDescription());
            corpo += String.format("%010.2f",a.getPrice());
            corpo += String.format("%-10.10s",a.getPostDate());
            corpo += String.format("%-10.10s",a.getLastUpdate());
            corpo += String.format("%-10.10s",a.getSaleDate());
            corpo += String.format("%-18s",a.getCategory());
            corpo += String.format("%-9s",a.getQuality());
            corpo += String.format("%09.9d",a.getUser());
            gravaRegistro(corpo, nomeArq);
            contaRegistroDado++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d",contaRegistroDado);
        gravaRegistro(trailer, nomeArq);
    }

    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String ra, nome, curso, disciplina;
        Double media;
        int qtdFalta;
        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;

        nomeArq += ".txt";

        List<Aluno> listaLida = new ArrayList<>();
        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
            System.exit(1);
        }

        // try-catch para leitura do arquivo
        try {
            registro = entrada.readLine(); // le o primeiro registro do arquivo

            while (registro != null) {
                // 01234567890
                // 00NOTA20231
                tipoRegistro = registro.substring(0,2);     // obtem os 2 primeiros caracteres do registro
                // substring - primeiro argumento é onde começa a substring dentro da string
                // e o segundo argumento é onde termina a substring + 1
                // Verifica se o tipoRegistro é um header, ou um trailer, ou um registro de dados
                if (tipoRegistro.equals("00")) {
                    System.out.println("é um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,6));
                    System.out.println("Ano/semestre: " + registro.substring(6,11));
                    System.out.println("Data e hora da gravação do arquivo: " + registro.substring(11,30));
                    System.out.println("Versão do documento de layout: " + registro.substring(30,32));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("é um registro de trailer");
                    qtdRegDadoGravado = Integer.parseInt(registro.substring(2,12));
                    if (contaRegDadoLido == qtdRegDadoGravado) {
                        System.out.println("Quantidade de registros lidos compatível com " +
                                "quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos incompatível com " +
                                "quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("é um registro de dados");
                    curso = registro.substring(2,7).trim();
                    ra = registro.substring(7,15);
                    nome = registro.substring(15,65).trim();
                    disciplina = registro.substring(65,105).trim();
                    media = Double.valueOf(registro.substring(105,110).replace(',','.'));
                    qtdFalta = Integer.parseInt(registro.substring(110,113));
                    Aluno a = new Aluno(ra, nome, curso, disciplina, media, qtdFalta);

                    // para importar esse dado para o banco de dados
                    // repository.save(a);

                    // no nosso caso, não estamos conectados a banco de dados
                    // então vamos add o objeto na listaLida
                    listaLida.add(a);

                    // contabiliza que leu mais um registro de dados
                    contaRegDadoLido++;
                }
                else {
                    System.out.println("tipo de registro inválido");
                }
                // le o proximo registro do arquivo
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
        }

        // Vamos exibir a lista lida
        System.out.println("\nLista contendo os dados lidos do arquivo:");
        for (Aluno a : listaLida) {
            System.out.println(a);
        }

        // Para importar a lista toda para o banco de dados:
        // repository.saveAll(listaLida);

    }

}
