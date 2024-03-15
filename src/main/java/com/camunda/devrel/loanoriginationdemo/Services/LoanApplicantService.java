package com.camunda.devrel.loanoriginationdemo.Services;

import com.camunda.devrel.loanoriginationdemo.Data.LoanApplicantRepository;
import com.camunda.devrel.loanoriginationdemo.Entities.LoanApplicant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanApplicantService {
    private final Logger log = LoggerFactory.getLogger(LoanApplicantService.class);
    private final LoanApplicantRepository repository;

    public LoanApplicantService(LoanApplicantRepository repository) {
        this.repository = repository;
    }

    public LoanApplicant create(String givenName, String surname, String address, String city, String stateOrProvince, String country, String postalCode) {
        return repository.save(new LoanApplicant(
            givenName,
            surname,
            address,
            city,
            stateOrProvince,
            country,
            postalCode
        ));
    }
    public LoanApplicant createIfNotExists(String givenName, String surname, String address, String city, String stateOrProvince, String country, String postalCode) {
        // todo: this should check for more than just first+last name match
        LoanApplicant matchedName = repository.findByGivenNameAndSurname(givenName, surname);

        if (matchedName == null) {
            log.info("applicant does not exist, creating");
            return create(givenName, surname, address, city, stateOrProvince, postalCode, country);
        }

        return matchedName;
    }

    public Optional<LoanApplicant> findById(Long id) {
        return repository.findById(id);
    }
}
