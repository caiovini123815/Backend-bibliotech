package com.altis.library.books.models.entities;


import com.altis.library.publishers.models.entities.publishers;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "isbn",nullable = false, unique = true, length = 13)
    private String isbn;

    @Column(name = "publication_Date",nullable = false)
    private LocalDate publicationDate;

    @Column(name = "genre",nullable = false, length = 150)
    private String genre;

    @Column(name = "number_Pages",nullable = false)
    private Integer numberPages;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private publishers publisher;

    @CreatedDate
    @Column(name = "create_dt", nullable = false, updatable = false)
    private LocalDateTime createDt;

    @LastModifiedDate
    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updateDt;

}



