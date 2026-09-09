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

    @Column(name = "name" ,nullable = false, length = 50)
    private String name;

    @Column(name = "birth_Date" ,nullable = false)
    private LocalDate birthdate;

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

    @Column(name = "is_admin" ,nullable = false)
    private String isadmin;

    @Column(name = "is_disable" ,nullable = false)
    private String isdisable;

    @CreatedDate
    @Column(name = "create_dt", nullable = false, updatable = false)
    private LocalDateTime createdt;

    @LastModifiedDate
    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updatedt;

}
