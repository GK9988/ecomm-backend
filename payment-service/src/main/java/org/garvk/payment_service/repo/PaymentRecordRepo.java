package org.garvk.payment_service.repo;

import org.garvk.payment_service.model.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRecordRepo extends JpaRepository<PaymentRecord, Long> {
    Optional<PaymentRecord> findByOrderId(Long aInId);
}
