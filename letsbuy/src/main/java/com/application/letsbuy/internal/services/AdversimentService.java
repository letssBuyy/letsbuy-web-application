package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.AdversimentInterface;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.entities.AdversimentsLike;
import com.application.letsbuy.internal.entities.Image;
import com.application.letsbuy.internal.entities.User;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.exceptions.*;
import com.application.letsbuy.internal.repositories.AdversimentRepository;
import com.application.letsbuy.internal.repositories.AdversimentsLikeRepository;
import com.application.letsbuy.internal.repositories.ImageRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import com.application.letsbuy.internal.utils.ConverterUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        Optional<Adversiment> retrieveAdversimentById = adversimentRepository.findById(id);
        if (retrieveAdversimentById.isPresent()) {
            return retrieveAdversimentById.get();
        } else {
            throw new AdversimentNotFoundException();
        }
    }

    @Override
    public List<Adversiment> findAll() {
        if (adversimentRepository.findAll().isEmpty()) {
            throw new AdversimentNoContentException();
        } else {
            return adversimentRepository.findAll();
        }
    }

    @Override
    public Adversiment openContest(Long id) {
        if (adversimentRepository.findById(id).isPresent()) {
            Adversiment adversiment = adversimentRepository.findById(id).get();
            adversiment.setContest(AdversimentEnum.ACTIVE);
            return adversiment;
        } else {
            throw new AdversimentNotFoundException();
        }
    }

    @Override
    public void likeAdversiment(Long idUser, Long idAdversiment) {
        Optional<User> user = userRepository.findById(idUser);
        Optional<Adversiment> adversiment = adversimentRepository.findById(idAdversiment);
        if (user.isPresent() && adversiment.isPresent()) {
            adversimentsLikeRepository.save(new AdversimentsLike(user.get(), adversiment.get()));
        } else {
            throw new AdversimentsLikeNotFoundException();
        }
    }

    @Override
    public List<AdversimentsLike> findAllAdversimentsLike() {
        if (adversimentRepository.findAll().isEmpty()) {
            throw new AdversimentNoContentException();
        } else {
            return adversimentsLikeRepository.findAll();
        }
    }

    @Override
    public List<AdversimentsLike> findByAdversimentsLike(Long id) {
        if (adversimentsLikeRepository.findByUserId(id).isEmpty()) {
            throw new AdversimentsLikeNoContentException();
        }
        return adversimentsLikeRepository.findByUserId(id);
    }

    @Override
    public void deslike(Long id) {
        if (adversimentsLikeRepository.findById(id).isPresent()) {
            adversimentsLikeRepository.deleteById(id);
        } else {
            throw  new AdversimentNotFoundException();
        }
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
                },
                () -> {
                    throw new ImageNotFoundException();
                }
        );
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
}

