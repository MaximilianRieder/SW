package de.othr.sw.quickstart.repository;

import de.othr.sw.quickstart.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
