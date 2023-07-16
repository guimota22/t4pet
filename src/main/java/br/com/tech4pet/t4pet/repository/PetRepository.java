package br.com.tech4pet.t4pet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4pet.t4pet.model.Pet;

public interface PetRepository extends MongoRepository<Pet, String>{
    
}
