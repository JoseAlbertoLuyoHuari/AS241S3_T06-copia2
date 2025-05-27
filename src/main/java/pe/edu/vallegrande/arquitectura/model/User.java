package pe.edu.vallegrande.arquitectura.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="[User]")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue (strategy = GenerationType.IDENTITY)    // ID_usuario
    private Long idUsuario;

    @Column(name = "username")              //nombre de usuario
    private String username;

    @Column(name = "password")               //Contraseña de usuario
    private String contra;

    @Column(name = "email")                //Correo
    private String email;

    @Column(name = "first_name")          //Nombre
    private String firstName;

    @Column(name = "last_name")           //Apellido
    private String lastName;

    @Column(name = "document_type")     //Tipo de documento
    private String documentType;

    @Column(name = "document_number")   //Número de documento
    private int documentNumber;

    @Column(name = "phone")             //Télefono
    private int phone;

    @Column(name = "status")            //estado
    private boolean status;                /*Este nombres es unico ya que varia toda la configuracion */

    @Column(name = "roles")             //Roles
    private String roles;

    @Column(name = "address")         //Dirección
    private String address;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;     //Fecha de registro

}
