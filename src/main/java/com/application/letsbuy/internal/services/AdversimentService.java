package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.AdversimentInterface;
import com.application.letsbuy.internal.dto.*;
import com.application.letsbuy.internal.entities.*;
import com.application.letsbuy.internal.enums.AdversimentColorEnum;
import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.application.letsbuy.internal.exceptions.AdversimentIsAlreadyLikedException;
import com.application.letsbuy.internal.exceptions.AdversimentNoContentException;
import com.application.letsbuy.internal.exceptions.AdversimentNotFoundException;
import com.application.letsbuy.internal.exceptions.ImageNotFoundException;
import com.application.letsbuy.internal.repositories.*;
import com.application.letsbuy.internal.utils.ArchivesUtils;
import com.application.letsbuy.internal.utils.ConverterUtils;
import com.application.letsbuy.internal.utils.ListObj;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdversimentService implements AdversimentInterface {

    private AdversimentRepository adversimentRepository;
    private AdversimentsLikeRepository adversimentsLikeRepository;
    private final PaymentControlSellerRepository paymentControlSellerRepository;
    private final UserService userService;
    private final PaymentUserAdversimentRepository paymentUserAdversimentRepository;
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
        List<Adversiment> adversimentList = this.adversimentRepository.findAll();
        if (adversimentList.isEmpty()) {
            throw new AdversimentNoContentException();
        }
        return adversimentList;
    }

    public long quantityAds() {
        return adversimentRepository.count();
    }

    public List<QuantitySelledByMonthDto> findQuantitySelledByMonth() {
        List<Adversiment> adversiments = this.findAll();
        List<QuantitySelledByMonthDto> quantitySelledByMonthDtos = new ArrayList<>();
        Map<YearMonth, Long> quantityByMonth = new HashMap<>();
        for (Adversiment adversiment : adversiments) {
            LocalDate saleDate = adversiment.getSaleDate();
            if (saleDate != null) {
                YearMonth yearMonth = YearMonth.from(saleDate);
                quantityByMonth.put(yearMonth, quantityByMonth.getOrDefault(yearMonth, 0L) + 1L);
            }
        }
        for (Map.Entry<YearMonth, Long> entry : quantityByMonth.entrySet()) {
            YearMonth yearMonth = entry.getKey();
            Long quantity = entry.getValue();
            quantitySelledByMonthDtos.add(new QuantitySelledByMonthDto(yearMonth.getMonthValue(), quantity));
        }
        quantitySelledByMonthDtos.sort(Comparator.comparingInt(QuantitySelledByMonthDto::getMonth));
        return quantitySelledByMonthDtos;
    }

    public Long countFinishedAds() {
        return adversimentRepository.countByIsActive(AdversimentEnum.SALLED);
    }

    public List<AllAdversimentsAndLikeDtoResponse> retrieveAdversimentById(Long idAdversiment, Optional<Long> idUser) {
        Adversiment adversiment = findById(idAdversiment);
        List<AdversimentsLike> likedAdversiments = new ArrayList<>();
        if (idUser.isPresent()) {
            likedAdversiments = findByAdversimentsLike(idUser.get());
        }
        Long quantityTotalAdversiment = countTotalAdversimentsByUser(adversiment.getUser().getId());
        Long quantityAdversimentSolded = countAdversimentSolded(adversiment.getUser().getId());
        Long quantityAdversimentActive = countAdversimentActive(adversiment.getUser().getId());
        List<AllAdversimentsAndLikeDtoResponse> allAdversimentslikes = new ArrayList<>();
        allAdversimentslikes.add(new AllAdversimentsAndLikeDtoResponse(idUser, adversiment, likedAdversiments, quantityTotalAdversiment, quantityAdversimentSolded, quantityAdversimentActive));
        return allAdversimentslikes;
    }

    public List<AllAdversimentsAndLikeDtoResponse> retrieveAdversiments(Optional<Long> idUser, Pageable pageable) {
        Page<Adversiment> adversiments = adversimentRepository.findAll(pageable);
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
        Optional<AdversimentsLike> adversimentIsAlreadyLiked = this.adversimentsLikeRepository.findAdversimentLikeByAdversimentIdAndUserId(idAdversiment, idUser);
        if (adversimentIsAlreadyLiked.isPresent()) {
            throw new AdversimentIsAlreadyLikedException();
        }
        this.adversimentsLikeRepository.save(new AdversimentsLike(user, adversiment));
    }

    @Override
    public List<AdversimentDtoResponse> findByState(Long id, AdversimentEnum state) {
        User user = this.userService.findById(id);
        List<Adversiment> adversimentList = adversimentRepository.findAdversimentsByUserAndIsActive(user, state);
        if (AdversimentEnum.SALLED.equals(state)) {
            return createDtoResponseSalled(adversimentList);
        }
        return createDtoResponse(adversimentList);
    }

    public List<ListAdversimentTrackingDtoResponse> findBought(Long id) {
        List<PaymentControllSeller> payments = paymentControlSellerRepository.findByPaymentUserAdvertisementBuyerId(id);
        List<Adversiment> adversiments = new ArrayList<>();
        payments.forEach((payment) -> {
            adversiments.add(payment.getPaymentUserAdvertisement().getAdversiment());
        });
        return ListAdversimentTrackingDtoResponse.convert(adversiments);
    }

    private List<AdversimentDtoResponse> createDtoResponse(List<Adversiment> adversimentList) {
        List<AdversimentDtoResponse> adversimentDtoList = new ArrayList<>();
        for (Adversiment adversiment : adversimentList) {
            AdversimentDtoResponse dto = new AdversimentDtoResponse(adversiment);
            adversimentDtoList.add(dto);
        }
        return adversimentDtoList;
    }

    private List<AdversimentDtoResponse> createDtoResponseSalled(List<Adversiment> adversimentList) {
        List<AdversimentDtoResponse> adversimentDtoList = new ArrayList<>();
        for (Adversiment adversiment : adversimentList) {
            Optional<PaymentUserAdvertisement> paymentUserAdvertisement = this.paymentUserAdversimentRepository.findByAdversiment(adversiment);
            if (paymentUserAdvertisement.isEmpty()) {
                throw new RuntimeException();
            }
            AdversimentDtoResponse dto = new AdversimentDtoResponse(adversiment, PaymentUserAdvertisementResponseDto.parseEntityToDto(paymentUserAdvertisement.get()), new UserDtoResponse(paymentUserAdvertisement.get().getBuyer()));
            adversimentDtoList.add(dto);
        }
        return adversimentDtoList;
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
    public List<MostSelledCategoriesDto> findFiveCategoriesAppearTheMost() {
        List<Adversiment> list = adversimentRepository.findByIsActive(AdversimentEnum.SALLED);

        Map<CategoryEnum, Long> categoryQuantities = list.stream()
                .collect(Collectors.groupingBy(Adversiment::getCategory, Collectors.counting()));

        List<MostSelledCategoriesDto> mostSelledCategories = categoryQuantities.entrySet().stream()
                .map(entry -> new MostSelledCategoriesDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingLong(MostSelledCategoriesDto::getQuantity).reversed())
                .limit(5)
                .collect(Collectors.toList());

        return mostSelledCategories;
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

    public ByteArrayResource createTxtResource(Long id, Optional<String> nomeArq) throws IOException {
        return new ByteArrayResource(exportFileTxt(id, nomeArq));
    }

    public byte[] exportFileTxt(Long id, Optional<String> nomeArq) throws IOException {
        List<Adversiment> adversiments = this.userService.findById(id).getAdversiments();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String archiveName = nomeArq.orElse("adversiments");
        archiveName += ".txt";
        int contaRegistroDado = 0;
        Double valorTotal = 0.0;

        String header = "00ANUNCIO2023";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        ArchivesUtils.gravaRegistro(outputStream, header);

        String corpo;
        for (Adversiment a : adversiments) {
            corpo = "01";
            corpo += String.format("%010d", a.getId());
            corpo += String.format("%-50.50s", a.getTitle());
            corpo += String.format("%-255.255s", a.getDescription());
            corpo += String.format("%10.2f", a.getPrice());
            corpo += String.format("%-10.10s", a.getPostDate());
            corpo += String.format("%-10.10s", a.getLastUpdate());
            corpo += String.format("%-18.18s", a.getCategory());
            corpo += String.format("%-9.9s", a.getQuality());
            corpo += String.format("%09d", a.getUser().getId());
            ArchivesUtils.gravaRegistro(outputStream, corpo);
            contaRegistroDado++;
            valorTotal += a.getPrice();
        }

        String trailer = "02";
        trailer += String.format("%010d", contaRegistroDado);
        trailer += String.format("%16.2f", valorTotal);
        ArchivesUtils.gravaRegistro(outputStream, trailer);
        return outputStream.toByteArray();
    }

    public void importFileTxt(String texto) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;

        // User atributes
        String name, email, cpf, phoneNumber;
        LocalDate birthDate;
        String password = "Mallhub123@";

        // Adversiment atributes
        String title, description;
        Double price;
        LocalDate postDate, lastUpdate;
        CategoryEnum category;
        QualityEnum quality;
        AdversimentColorEnum color;
        Long userId;

        entrada = new BufferedReader(new StringReader(texto));

        try {
            registro = entrada.readLine();

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
                    color = AdversimentColorEnum.valueOf(registro.substring(337, 347).trim());
                    category = CategoryEnum.valueOf(registro.substring(347, 365).trim());
                    quality = QualityEnum.valueOf(registro.substring(365, 374).trim());
                    userId = Long.parseLong(registro.substring(374, 383).trim());

                    User user = userService.findById(userId);
                    Adversiment adversiment = new Adversiment(user, title, description, price, postDate, lastUpdate, color, category, quality);
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

    public ByteArrayResource createCsvResource(Long id, Optional<String> nomeArq) throws IOException {
        return new ByteArrayResource(createCsvArchive(id, nomeArq));
    }

    public byte[] createCsvArchive(Long id, Optional<String> nomeArquivo) throws IOException {
        List<Adversiment> adversimentList = userService.findById(id).getAdversiments();
        if (adversimentList.isEmpty()) {
            throw new AdversimentNotFoundException();
        }
        ListObj<Adversiment> adversimentObj = new ListObj<>(adversimentList.size());
        adversimentList.forEach(adversimentObj::adiciona);
        ListObj<Adversiment> orderedList = ListObj.orderByPrice(adversimentObj);
        return ArchivesUtils.generateCsvBytes(orderedList, nomeArquivo);
    }
}

