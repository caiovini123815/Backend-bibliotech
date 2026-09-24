package com.altis.library.publishers.services;

import com.altis.library.books.repositories.BooksRepository;
import com.altis.library.publishers.models.dtos.PublishersRequestDTO;
import com.altis.library.publishers.models.dtos.PublishersResponseDTO;
import com.altis.library.publishers.models.entities.publishers;
import com.altis.library.publishers.repositories.PublishersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PublishersServices {

    private final PublishersRepository publishersRepository;
    private final BooksRepository booksRepository;

    public PublishersServices(
            PublishersRepository publishersRepository,
            BooksRepository booksRepository
    ) {
        this.publishersRepository = publishersRepository;
        this.booksRepository = booksRepository;
    }

    @Transactional
    public PublishersResponseDTO create(
            PublishersRequestDTO publisherData) {

        if (publishersRepository.existsByEmail(publisherData.email())) {
            throw new RuntimeException("Email already registered");
        }

        if (publishersRepository.existsByCnpj(publisherData.cnpj())) {
            throw new RuntimeException("CNPJ already registered");
        }

        publishers publisher = new publishers();

        publisher.setNamePublisher(publisherData.namePublisher());
        publisher.setCnpj(publisherData.cnpj());
        publisher.setEmail(publisherData.email());
        publisher.setPhonePublisher(publisherData.phonePublisher());
        publisher.setWebsite(publisherData.website());

        publishers savedPublisher =
                publishersRepository.save(publisher);

        return new PublishersResponseDTO(
                savedPublisher.getId(),
                savedPublisher.getNamePublisher(),
                savedPublisher.getPhonePublisher(),
                savedPublisher.getWebsite(),
                savedPublisher.getCreateDt(),
                savedPublisher.getUpdateDt()
        );
    }

    @Transactional(readOnly = true)
    public Page<PublishersResponseDTO> findAll(Pageable pageable) {

        return publishersRepository
                .findAll(pageable)
                .map(publisher -> new PublishersResponseDTO(
                        publisher.getId(),
                        publisher.getNamePublisher(),
                        publisher.getPhonePublisher(),
                        publisher.getWebsite(),
                        publisher.getCreateDt(),
                        publisher.getUpdateDt()
                ));
    }

    @Transactional(readOnly = true)
    public publishers findById(Long id) {
        return publishersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Publisher not found"));
    }

    @Transactional
    public publishers update(Long id, publishers publisherData) {

        publishers publisher = findById(id);

        publisher.setNamePublisher(publisherData.getNamePublisher());
        publisher.setPhonePublisher(publisherData.getPhonePublisher());
        publisher.setWebsite(publisherData.getWebsite());

        return publishersRepository.save(publisher);
    }

    @Transactional
    public publishers partialUpdate(Long id, publishers publisherData) {

        publishers publisher = findById(id);

        if (publisherData.getNamePublisher() != null) {
            publisher.setNamePublisher(publisherData.getNamePublisher());
        }

        if (publisherData.getPhonePublisher() != null) {
            publisher.setPhonePublisher(publisherData.getPhonePublisher());
        }

        if (publisherData.getWebsite() != null) {
            publisher.setWebsite(publisherData.getWebsite());
        }

        return publishersRepository.save(publisher);
    }

    @Transactional
    public void delete(Long id) {

        publishers publisher = findById(id);

        if (booksRepository.existsByPublisherId(id)) {
            throw new RuntimeException(
                    "Publisher cannot be deleted because it has registered books"
            );
        }

        publishersRepository.delete(publisher);
    }
}