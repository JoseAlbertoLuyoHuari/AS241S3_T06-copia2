package pe.edu.vallegrande.arquitectura.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.arquitectura.model.Product;
import pe.edu.vallegrande.arquitectura.repository.ProductRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        log.info(products.toString());
        log.info("Listado de productos completado");
        return products;
    }

    public Optional<Product> findById(Long id) {
        log.info("Buscando usuario con ID:{}", id);
        return productRepository.findById(id);
    }

    public List<Product> findByStatus(String status) {
        log.info("Listando usuario por estados:{}", status);
        return productRepository.findByStatus(status);
    }


    public Product create(Product product) {
        product.setStatus("A");
        product.setRegistrationDate(LocalDateTime.now());
        log.info("Creando un producto: {}" ,product);
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.findById(product.getIdProduct()).map(existing -> {

            // Actualizar solo los campos que el usuario puede modificar
            existing.setName(product.getName());
            existing.setSpecification(product.getSpecification());
            existing.setDescription(product.getDescription());
            existing.setBrand(product.getBrand());
            existing.setCategory(product.getCategory());
            existing.setUnitPrice(product.getUnitPrice());
            existing.setStock(product.getStock());
            existing.setInternalCode(product.getInternalCode());
            
            // **No actualizar `status`, `registrationDate` ni `id`**
            return productRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("No se encontró el producto con ID: " + product.getIdProduct()));
    }

    public Product delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el producto con ID: " + id));
        product.setStatus("I");
        return productRepository.save(product);
    }

    public Product restore(Long id) {
        log.info("Restaurando usuario con ID: {}", id);

        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setStatus("A");
            productRepository.save(product);  // Guardar el cambio
            log.info("Usuario restaurado con ID: {}", id);
            return product;
        } else {
            log.warn("No se encontró el usuario con ID: {}", id);
            return null;
        }
    }
}
