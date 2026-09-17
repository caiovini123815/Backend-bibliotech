package com.altis.library.publishers.models.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "publishers")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "website", length = 255)
    private String website;

}
