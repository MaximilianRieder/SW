package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Kunde;
import org.springframework.data.repository.CrudRepository;

public interface KundeRepository extends CrudRepository<Kunde, Long> {
}
