package pe.edu.vallegrande.arquitectura.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.arquitectura.model.User;
import pe.edu.vallegrande.arquitectura.service.UserService;

import java.util.List;
import java.util.Optional;

/*
*   GET  ==> Listar
*   POST ==> Crear
*   PUT  ==> Editar
*   DELETE ==> Eliminar
*/


@RestController                 // Marca esta clase como un controlador REST que devuelve datos JSON (GET, POST, PUT, DELETE)
@AllArgsConstructor             // Genera un constructor con todos los atributos de la clase
@CrossOrigin("*")               // Me permite utilizar las rutas de cualquier dominio
@RequestMapping("api/v1/user")

public class UserRest {

    private final UserService userService;

    /*Listar Clientes*/
    @GetMapping("/listar")
    public List<User> getAll() {
        return userService.getAll();
    }


    /*Listado por ID*/
    @GetMapping("/listar/id/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /*Listado por Estado  ->  Activo = "A" Desconcetado = "D" */
    @GetMapping("/listar/estado/{status}")
    public List<User> findByStatus(@PathVariable("status") boolean Status) {
        return userService.findByStatus(Status);
    }

    /*Creando usuario POST */

    @PostMapping("/crear")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    /*Editar usuario PUT */

        @PutMapping("/editar")
        public User update(@RequestBody User user) {
            return userService.update(user);
        }

    /* Eliminar usuario DELETE */

    @DeleteMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);  // Llamar al servicio para eliminar físicamente el cliente
        return "Cliente eliminado con ID: " + id;
    }

    /*  Resturar por ID */
    @PutMapping("/restaurar/{id}")
    public String restore(@PathVariable Long id) {
        userService.restore(id);  // Llamar al servicio para restaurar lógicamente el usuario
        return "Usuario restaurado con ID: " + id;
    }
}
