package com.recursos.service;

import com.recursos.exceptions.DepositNegativeSumException;
import com.recursos.exceptions.InsufficientFundsException;
import com.recursos.model.Recurso;
import com.recursos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public Recurso createAccount(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public Collection<Recurso> getAccounts() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> findById(Long cbu) {
        return recursoRepository.findById(cbu);
    }

    public void save(Recurso recurso) {
        recursoRepository.save(recurso);
    }

    public void deleteById(Long cbu) {
        recursoRepository.deleteById(cbu);
    }

    @Transactional
    public Recurso withdraw(Long cbu, Double sum) {
        Recurso recurso = recursoRepository.findAccountByCbu(cbu);

        if (recurso.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        recurso.setBalance(recurso.getBalance() - sum);
        recursoRepository.save(recurso);

        return recurso;
    }

    @Transactional
    public Recurso deposit(Long cbu, Double sum) {
        Double discount = 0.1;
        Double topDiscount = 500.0;

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit non positive sums");
        }

        if (sum >= 2000) {
            if (sum*discount < 500) {
                sum += sum*discount;
            } else {
                sum += topDiscount;
            }
        }

        Recurso recurso = recursoRepository.findAccountByCbu(cbu);
        recurso.setBalance(recurso.getBalance() + sum);
        recursoRepository.save(recurso);

        return recurso;
    }

}
