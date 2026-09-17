package com.altis.library.users.models.entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_full" ,nullable = false, length = 100)
    private String nameFull;

    @Column(name = "email" ,nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "cpf" ,nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(name = "phone" ,nullable = false, length = 15)
    private String phone;

    @Column(name = "birth_date" , nullable = false)
    private LocalDate birthDate;

    @Column(name = "address" ,nullable = false)
    private String address;

    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "is_admin" ,nullable = false)
    private String isAdmin;

    @Column(name = "is_disable" ,nullable = false)
    private String isDisable;

    @CreatedDate
    @Column(name = "create_dt", nullable = false, updatable = false)
    private LocalDateTime createDt;

    @LastModifiedDate
    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updateDt;

}
