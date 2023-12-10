package no.exam.rentablesservice.repository;

import no.exam.rentablesservice.entity.Rentable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentableRepository extends JpaRepository<Rentable, Long> {

    Rentable findByRentableId(Long rentableId);
}
