package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.UserInterface;
import com.application.letsbuy.internal.dto.BalanceDtoResponse;
import com.application.letsbuy.internal.dto.TransactionRequestDto;
import com.application.letsbuy.internal.dto.TransactionResponseDto;
import com.application.letsbuy.internal.entities.*;
import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.enums.PaymentStatusEnum;
import com.application.letsbuy.internal.enums.TransactionTypeEnum;
import com.application.letsbuy.internal.exceptions.InsufficientBalanceException;
import com.application.letsbuy.internal.exceptions.UserConflictException;
import com.application.letsbuy.internal.exceptions.UserNotFoundException;
import com.application.letsbuy.internal.repositories.PaymentControlSellerRepository;
import com.application.letsbuy.internal.repositories.PaymentUserAdversimentRepository;
import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class UserService implements UserInterface {
    private final UserRepository userRepository;

    private final ImageService imageService;

    private final BankAccountService bankAccountService;

    private final TransactionService transactionService;

    private final PaymentUserAdversimentRepository paymentUserAdversimentRepository;

    private final PaymentControlSellerRepository paymentControlSellerRepository;

    @Override
    public void save(User user) {
        if (isUserAlreadyRegistered(user.getEmail(), user.getCpf(), user.getPhoneNumber())) {
            throw new UserConflictException();
        }
        this.userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    public Long quantityUsers() {
        return userRepository.count();
    }


    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setIsActive(ActiveInactiveEnum.INACTIVE);
    }

    public User insertProfileImage(Long id, MultipartFile img) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setProfileImage(imageService.upload(img));
        this.userRepository.save(user);
        return user;
    }

    public User deleteProfileImage(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setProfileImage(null);
        this.userRepository.save(user);
        return user;
    }

    private boolean isUserAlreadyRegistered(String email, String cpf, String phoneNumber) {
        return this.userRepository.findByEmail(email).isPresent() ||
                this.userRepository.findByCpf(cpf).isPresent() ||
                this.userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    @Override
    public String generateWppLink(Long id) {
        return "wa.me/55" + findById(id).getPhoneNumber();
    }

    @Override
    public BalanceDtoResponse transactionMoney(TransactionRequestDto dto) {

        if (dto.getTransactionType().equals(TransactionTypeEnum.DEPOSIT)) {
            Optional<PaymentUserAdvertisement> paymentUserAdvertisement = paymentUserAdversimentRepository.findByAdversimentId(dto.getAdversimentId());

            if (paymentUserAdvertisement.isPresent()) {
                User user = paymentUserAdvertisement.get().getBuyer();
                Payment payment = paymentUserAdvertisement.get().getPayment();

                if (payment.getStatus().equals(PaymentStatusEnum.CONCLUDED)) {
                    Double amount = payment.getAmount().doubleValue();
                    Optional<PaymentControllSeller> paymentControllSeller = paymentControlSellerRepository.findByPaymentUserAdvertisementAdversimentId(dto.getAdversimentId());

                    if (paymentControllSeller.isPresent()) {
                        Transaction transaction = new Transaction(amount, dto.getTransactionType(), user);
                        transactionService.save(transaction);
                        user.setBalance(user.getBalance() + (transaction.getAmount() * (1 - paymentControllSeller.get().getAmountTax() / 100)));
                        Double balance = user.getBalance();
                        List<TransactionResponseDto> transactions = transactionService.listTransactions(transaction.getUser().getId());
                        return new BalanceDtoResponse(balance, transactions);
                    }

                }
            }
            throw new RuntimeException();

        } else if (dto.getTransactionType().equals(TransactionTypeEnum.WITHDRAW)) {
            bankAccountService.findById(dto.getUserId());
            Optional<User> userOptional = userRepository.findById(dto.getUserId());

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (dto.getAmount() > user.getBalance()) {
                    throw new InsufficientBalanceException();
                }
                Transaction transaction = new Transaction(dto.getAmount(), dto.getTransactionType(), user);
                transactionService.save(transaction);
                user.setBalance(user.getBalance() - transaction.getAmount());
                Double balance = user.getBalance();
                List<TransactionResponseDto> transactions = transactionService.listTransactions(transaction.getUser().getId());
                return new BalanceDtoResponse(balance, transactions);
            }
        }
        throw new IllegalArgumentException();
    }
}
