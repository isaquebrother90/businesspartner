package br.com.businesspart.app.repositories;

import br.com.businesspart.app.entities.BusinessPartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartnerEntity, Long> {
    Optional<BusinessPartnerEntity> findByCardCode(Long id);
}
