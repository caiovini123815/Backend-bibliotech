package com.altis.library.users.models.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" ,nullable = false, length = 50)
    private String name;

    @Column(name = "birth_Date" ,nullable = false)
    private LocalDate birthDate;

    @Column(name = "cpf" ,nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "phone" ,nullable = false, length = 15)
    private String phone;

    @Column(name = "email" ,nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "address" ,nullable = false)
    private String address;

    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "is_Admin" ,nullable = false)
    private String isAdmin;

    @Column(name = "is_Disable" ,nullable = false)
    private String isDisable;

    @Column(name = "create_At" ,nullable = false)
    private String crateAt;

    @Column(name = "update_At" ,nullable = false)
    private String updateAt;

}
