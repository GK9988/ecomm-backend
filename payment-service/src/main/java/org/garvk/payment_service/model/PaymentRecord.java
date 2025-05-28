package org.garvk.payment_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "order_id")
    private Long orderId;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(nullable = false, name = "payment_amount")
    private Long paymentAmount;

    @CreationTimestamp
    @Column(nullable = false, name = "payment_timestamp")
    private LocalDateTime paymentTimestamp;
}
