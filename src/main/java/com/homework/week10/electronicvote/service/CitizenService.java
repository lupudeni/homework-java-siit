package com.homework.week10.electronicvote.service;

import com.homework.week10.electronicvote.entity.Citizen;
import com.homework.exception.EntityNotFoundException;
import com.homework.week10.electronicvote.repository.CitizenRepository;

public class CitizenService {
    private final CitizenRepository citizenRepository;

    public CitizenService() {
        citizenRepository = new CitizenRepository();
    }

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen getCitizenByCNP(String cnp) throws EntityNotFoundException {
        if (cnp == null) {
            throw new IllegalArgumentException("CNP cannot be null");
        }
        Citizen citizen = citizenRepository.getCitizenByCNP(cnp);
        if (citizen == null) {
            throw new EntityNotFoundException("No citizen recorded with CNP: " + cnp);
        }
        return citizen;
    }
}
