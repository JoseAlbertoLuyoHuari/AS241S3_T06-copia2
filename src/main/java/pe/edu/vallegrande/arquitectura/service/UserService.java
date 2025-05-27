package pe.edu.vallegrande.arquitectura.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.arquitectura.model.User;
import pe.edu.vallegrande.arquitectura.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor

public class UserService {
    private final UserRepository userRepository;

   /* Listar mediante || Total || ID || EStado */

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info(users.toString());
        log.info("Listado de usuarios completado :>");
        return users;
    }

    public Optional<User> findById(Long id) {
        log.info("Buscando usuario con ID:{}", id);
        return userRepository.findById(id);
    }

    public List<User> findByStatus(boolean Status) {
        log.info("Listando usuarios por estado:{}", Status);
        return userRepository.findByStatus(Status);
    }


    /* Creando usuario */

    public User create(User user) {
        user.setStatus(true);                        // Por defecto se crea como Activo
        user.setRegistrationDate(LocalDateTime.now());
        log.info("Creando usuario: {}", user);
        return userRepository.save(user);
    }


    /*Editar usuario*/

    public User update(User user) {
        return userRepository.findById(user.getIdUsuario()).map(existing -> {

            // Actualizar solo los campos que el usuario puede modificar
            existing.setUsername(user.getUsername());
            existing.setContra(user.getContra());
            existing.setEmail(user.getEmail());
            existing.setFirstName(user.getFirstName());
            existing.setLastName(user.getLastName());
            existing.setDocumentType(user.getDocumentType());
            existing.setDocumentNumber(user.getDocumentNumber());
            existing.setPhone(user.getPhone());
            existing.setAddress(user.getAddress());
            existing.setRoles(user.getRoles());

            // **No actualizar `status` ni `registrationDate`**
            // Se conservan los valores originales en caso el usuario no los envíe
            existing.setRegistrationDate(existing.getRegistrationDate());

            return userRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    /*Eliminar usuario*/

    public void delete(Long id) {
        log.info("Eliminando lógicamente cliente con ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        user.setStatus(false); // Cambiar estado a Inactivo
        userRepository.save(user);
    }


    /*Restaurar usuario por ID*/

    public void restore(Long id) {
        log.info("Restaurando usuario con ID: {}", id);

        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setStatus(true);  // Cambiar el estado a 'A' (activo)
            userRepository.save(user);  // Guardar el cambio
            log.info("Usuario restaurado con ID: {}", id);
        } else {
            log.warn("No se encontró el usuario con ID: {}", id);
        }
    }

}
