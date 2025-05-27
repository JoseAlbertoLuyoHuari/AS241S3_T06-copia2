package pe.edu.vallegrande.arquitectura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @Column(name = "id_product")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "specification")
    private String specification;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "category")
    private String category;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "stock")
    private String stock;

    @Column(name = "status")
    private String status;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "internal_code")
    private String internalCode;
}
