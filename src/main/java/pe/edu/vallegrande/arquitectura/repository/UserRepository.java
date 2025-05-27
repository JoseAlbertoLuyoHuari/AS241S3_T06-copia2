package pe.edu.vallegrande.arquitectura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pe.edu.vallegrande.arquitectura.model.User;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatus(boolean Status);
}
