package com.altis.library.publishers.models.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "publishers")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class publishers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_publisher" ,nullable = false, length = 50)
    private String namePublisher;

    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;

    @Column(name = "email" ,nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "phone_publisher" ,nullable = false, length = 15)
    private String phonePublisher;

    @Column(name = "website",nullable = false, length = 255)
    private String website;

    @CreatedDate
    @Column(name = "create_dt", nullable = false, updatable = false)
    private LocalDateTime createDt;

    @LastModifiedDate
    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updateDt;

}
