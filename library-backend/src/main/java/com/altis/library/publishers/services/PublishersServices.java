package com.altis.library.publishers.services;

import com.altis.library.publishers.models.entities.publishers;
import com.altis.library.publishers.repositories.PublishersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishersServices {

    private final PublishersRepository publishersRepository;

    public PublishersServices(PublishersRepository publishersRepository) {
        this.publishersRepository = publishersRepository;
    }

    public publishers create(publishers publisher) {

        if (publishersRepository.existsByEmail(publisher.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (publishersRepository.existsByCnpj(publisher.getCnpj())) {
            throw new RuntimeException("CNPJ already registered");
        }

        return publishersRepository.save(publisher);
    }

    public List<publishers> findAll() {
        return publishersRepository.findAll();
    }

    public publishers findById(Long id) {
        return publishersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Publisher not found"));
    }

    public publishers update(Long id, publishers publisherData) {

        publishers publisher = findById(id);

        publisher.setNamePublisher(publisherData.getNamePublisher());
        publisher.setEmail(publisherData.getEmail());
        publisher.setPhonePublisher(publisherData.getPhonePublisher());
        publisher.setWebsitePublisher(publisherData.getWebsitePublisher());

        return publishersRepository.save(publisher);
    }

    public void delete(Long id) {

        publishers publisher = findById(id);

        publishersRepository.delete(publisher);
    }
}
