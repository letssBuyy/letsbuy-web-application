package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.AdversimentInterface;
import com.application.letsbuy.internal.dto.AdversimentsLikeDtoResponse;
import com.application.letsbuy.internal.dto.AllAdversimentsAndLikeDtoResponse;
import com.application.letsbuy.internal.dto.UserDto;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import com.application.letsbuy.internal.entities.Image;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.exceptions.AdversimentNoContentException;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.exceptions.AdversimentsLikeNotFoundException;
import com.application.letsbuy.internal.exceptions.ImageNotFoundException;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.repositories.AdversimentsLikeRepository;
import com.application.letsbuy.internal.repositories.ImageRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import com.application.letsbuy.internal.utils.ArchivesUtils;
import com.application.letsbuy.internal.utils.ConverterUtils;
import com.application.letsbuy.internal.utils.ListObj;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdversimentService implements AdversimentInterface {

    private AdversimentRepository adversimentRepository;
    private AdversimentsLikeRepository adversimentsLikeRepository;
    private final UserService userService;
    private UserRepository userRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @Override
    public void save(Adversiment adversiment) {
        adversimentRepository.save(adversiment);
    }

    @Override
    public void deleteById(Long id) {
        if (adversimentRepository.findById(id).isPresent()) {
            Adversiment adversiment = adversimentRepository.findById(id).get();
            adversiment.setIsActive(AdversimentEnum.INACTIVE);
        } else {
            throw new AdversimentNotFoundException();
        }
    }

    @Override
    public Adversiment findById(Long id) {
        return this.adversimentRepository.findById(id).orElseThrow(AdversimentNotFoundException::new);
    }

    @Override
    public List<Adversiment> findAll() {
        if (adversimentRepository.findAll().isEmpty()) {
            throw new AdversimentNoContentException();
        }
        return adversimentRepository.findAll();
    }

    public List<AllAdversimentsAndLikeDtoResponse> retrieveAdversimentById(Long idAdversiment, Long idUser) {
        Adversiment adversiment = findById(idAdversiment);
        List<AdversimentsLike> likedAdversiments = findByAdversimentsLike(idUser);;
        Long quantityTotalAdversiment = countTotalAdversimentsByUser(adversiment.getUser().getId());
        Long quantityAdversimentSolded = countAdversimentSolded(adversiment.getUser().getId());
        Long quantityAdversimentActive = countAdversimentActive(adversiment.getUser().getId());
        List<AllAdversimentsAndLikeDtoResponse> allAdversimentslikes = new ArrayList<>();
        allAdversimentslikes.add(new AllAdversimentsAndLikeDtoResponse(idUser, adversiment, likedAdversiments, quantityTotalAdversiment, quantityAdversimentSolded, quantityAdversimentActive));
        return allAdversimentslikes;
    }

    public List<AllAdversimentsAndLikeDtoResponse> retrieveAdversiments(Optional<Long> idUser) {
        List<Adversiment> adversiments = findAll();
        List<AdversimentsLike> likedAdversiments = new ArrayList<>();
        if (idUser.isPresent()) {
            likedAdversiments = findByAdversimentsLike(idUser.get());
        }
        List<AllAdversimentsAndLikeDtoResponse> allAdversimentslikes = new ArrayList<>();
        for (Adversiment adversiment : adversiments) {
            allAdversimentslikes.add(new AllAdversimentsAndLikeDtoResponse(idUser, adversiment, likedAdversiments));
        }
        return allAdversimentslikes;
    }

    @Override
    public Adversiment openContest(Long id) {
        Adversiment adversiment = this.adversimentRepository.findById(id).orElseThrow(AdversimentNotFoundException::new);
        adversiment.setContest(AdversimentEnum.ACTIVE);
        return adversiment;
    }

    @Override
    public void likeAdversiment(Long idUser, Long idAdversiment) {
        User user = this.userService.findById(idUser);
        Adversiment adversiment = findById(idAdversiment);
        this.adversimentsLikeRepository.save(new AdversimentsLike(user, adversiment));
    }

    @Override
    public List<AdversimentsLike> findAllAdversimentsLike() {
        return adversimentsLikeRepository.findAll();
    }

    @Override
    public List<AdversimentsLike> findByAdversimentsLike(Long id) {
        return adversimentsLikeRepository.findByUserId(id);
    }

    @Override
    public void deslike(Long id) {
        AdversimentsLike adversimentsLiked = this.adversimentsLikeRepository.findById(id).orElseThrow(AdversimentNotFoundException::new);
        this.adversimentsLikeRepository.deleteById(adversimentsLiked.getId());
    }

    @Override
    public Adversiment insertImages(Long id, List<MultipartFile> images) {
        Adversiment adversiment = adversimentRepository.findById(id).orElseThrow(AdversimentNotFoundException::new);
        List<Image> listImages = images.stream()
                .map(img -> {
                    Image image = new Image();
                    image.setAdversiment(adversiment);
                    image.setUrl(imageService.upload(img));
                    return imageRepository.save(image);
                })
                .collect(Collectors.toList());
        adversiment.setImages(listImages);
        return adversimentRepository.save(adversiment);
    }

    public Long countTotalAdversimentsByUser(Long id) {
        return this.adversimentRepository.countByUserId(id);
    }

    public Long countAdversimentSolded(Long id) {
        return this.adversimentRepository.countByUserIdAndIsActive(id, AdversimentEnum.SALLED);
    }

    public Long countAdversimentActive(Long id) {
        return this.adversimentRepository.countByUserIdAndIsActive(id, AdversimentEnum.ACTIVE);
    }


    @Override
    public Adversiment updateImages(Long id, List<MultipartFile> images) {
        Adversiment adversiment = adversimentRepository.findById(id).orElseThrow(AdversimentNotFoundException::new);
        AtomicInteger i = new AtomicInteger();
        adversiment.getImages().forEach(img -> {
            imageService.delete(img.getUrl());
            img.setUrl(imageService.upload(images.get(i.getAndIncrement())));
        });
        return adversiment;
    }

    @Override
    public void deleteImage(String url) {
        this.imageRepository.findByUrl(url).ifPresentOrElse(
                image -> {
                    this.imageRepository.deleteById(image.getId());
                    this.imageService.delete(image.getUrl());
                }, () -> {
                    throw new ImageNotFoundException();
                });
    }

    public void importFileTxt(String nomeArq) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;

        // User atributes
        String name, email, cpf, phoneNumber;
        LocalDate birthDate;
        String password = "Camila@01";

        // Adversiment atributes
        String title, description;
        Double price;
        LocalDate postDate, lastUpdate, saleDate;
        CategoryEnum category;
        QualityEnum quality;
        AdversimentColorEnum color = AdversimentColorEnum.GOLD;
        Long userId;

        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;

        nomeArq += ".txt";

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
            System.exit(1);
        }

        // try-catch para leitura do arquivo
        try {
            registro = entrada.readLine(); // le o primeiro registro do arquivo

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("01")) {
                    name = registro.substring(2, 52).trim();
                    System.out.println(name);
                    email = registro.substring(52, 102).trim();
                    System.out.println(email);
                    cpf = registro.substring(102, 113).trim();
                    System.out.println(cpf);
                    birthDate = LocalDate.parse(registro.substring(113, 123));
                    System.out.println(birthDate);
                    phoneNumber = registro.substring(123, 134).trim();
                    System.out.println(phoneNumber);
                    System.out.println(password);

                    User user = new User(name, email, cpf, password, birthDate, phoneNumber);
                    UserDto userDto = new UserDto(user);
                    User userNew = userDto.convert();
                    userService.save(userNew);

                } else if (tipoRegistro.equals("02")) {
                    title = registro.substring(2, 52).trim();
                    description = registro.substring(52, 307).trim();
                    price = Double.valueOf(registro.substring(307, 317).replace(',', '.'));
                    postDate = LocalDate.parse(registro.substring(317, 327));
                    lastUpdate = LocalDate.parse(registro.substring(327, 337));
                    saleDate = LocalDate.parse(registro.substring(337, 347));
                    category = CategoryEnum.valueOf(registro.substring(347, 365).trim());
                    quality = QualityEnum.valueOf(registro.substring(365, 374).trim());
                    userId = Long.parseLong(registro.substring(374, 383).trim());

                    User user = userService.findById(userId);
                    Adversiment adversiment = new Adversiment(user, title, description, price, postDate, lastUpdate, saleDate, color, category, quality);
                    save(adversiment);
                } else {
                    System.out.println("tipo de registro inválido");
                }
                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    @Override
    public Adversiment searchBinary(List<Adversiment> adversimentList, Double price) {
        if (adversimentList.isEmpty()) {
            throw new AdversimentNoContentException();
        }

        Adversiment[] vetor = ConverterUtils.convertList(adversimentList);

        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 1; j < vetor.length - i; j++) {
                if (vetor[j - 1].getPrice() > vetor[j].getPrice()) {
                    Adversiment aux = vetor[j];
                    vetor[j] = vetor[j - 1];
                    vetor[j - 1] = aux;
                }
            }
        }
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (price.equals(vetor[meio].getPrice())) {
                Long adversimentId = vetor[meio].getId();
                Adversiment adversiment = findById(adversimentId);
                return adversiment;

            } else if (price > vetor[meio].getPrice()) {
                inicio = meio + 1;

            } else {
                fim = meio - 1;
            }
        }
        throw new AdversimentNotFoundException();
    }

    public void createCsvArchive(Long id, Optional<String> nomeArquivo) {
        List<Adversiment> adversimentList = userService.findById(id).getAdversiments();
        if (adversimentList.isEmpty()) {
            throw new AdversimentNotFoundException();
        }
        ListObj<Adversiment> adversimentObj = new ListObj<>(adversimentList.size());
        adversimentList.forEach(adversimentObj::adiciona);
        ListObj<Adversiment> orderedList = ListObj.orderByPrice(adversimentObj);
        ArchivesUtils.creatCsvArchive(orderedList, nomeArquivo);
    }
}
