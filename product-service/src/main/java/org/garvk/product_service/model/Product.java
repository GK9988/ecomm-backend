package org.garvk.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(nullable = false, name = "quantity_available")
    private Long quantityAvailable;

    @Column(nullable = false, name = "mrp")
    private Long mrp;

    @Column(nullable = false, name = "price")
    private Long price;

    @Column(nullable = false, name = "discount")
    private Long discount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "product_status")
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "product_category")
    private ProductCategory productCategory;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

}
