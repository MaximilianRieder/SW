package de.othr.sw.quickstart.service;

import de.othr.sw.quickstart.entity.Kunde;
import de.othr.sw.quickstart.repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KundeService implements KundeServiceIF {

    @Autowired
    private KundeRepository kundeRepository;

    @Override
    public Kunde kundeAnlegen(Kunde kunde) {
        Kunde neu = kundeRepository.save(kunde);
        return neu;
    }
}
