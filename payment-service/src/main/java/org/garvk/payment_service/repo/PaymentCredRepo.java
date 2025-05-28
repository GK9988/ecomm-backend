package org.garvk.payment_service.repo;

import org.garvk.payment_service.model.PaymentCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentCredRepo extends JpaRepository<PaymentCredentials, Long> {

    boolean existsByPayId(Long aInPayId);
    Optional<PaymentCredentials> findByPayId(Long aInPayId);

}
