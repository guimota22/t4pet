package br.com.tech4pet.t4pet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4pet.t4pet.model.Pet;
import br.com.tech4pet.t4pet.repository.PetRepository;
import br.com.tech4pet.t4pet.shared.PetDto;
import br.com.tech4pet.t4pet.shared.PetListagemDto;

@Service
public class PetServiceImpl implements PetService{
    
    @Autowired
    private PetRepository repositorio;

    @Override
    public List<PetListagemDto> obterTodos() {
        return repositorio.findAll()
            .stream()
            .map(p -> new PetListagemDto(p.getId(), p.getNome(), p.getProcedimentos()))
            .toList();
    }

    @Override
    public Optional<PetDto> obterPorId(String id) {
        Optional<Pet> pet = repositorio.findById(id);

        if (pet.isPresent()) {
            return Optional.of(new PetDto(pet.get().getId(),
                pet.get().getNome(),
                pet.get().getRaca(),
                pet.get().getAnoNascimento(),
                pet.get().isVacinado(),
                pet.get().getProcedimentos()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PetDto cadastrar(PetDto dto) {
        Pet pet = new Pet(dto);
        repositorio.save(pet);

        return new PetDto(pet.getId(),
            pet.getNome(),
            pet.getRaca(),
            pet.getAnoNascimento(),
            pet.isVacinado(),
            pet.getProcedimentos());
    }

    @Override
    public PetDto atualizar(String id, PetDto dto) {
        Pet pet = repositorio.findById(id).orElse(null);

        if (pet != null) {
            Pet petAtualizar = new Pet(dto);
            petAtualizar.setId(id);
            repositorio.save(petAtualizar);
            return new PetDto(pet.getId(),
                pet.getNome(),
                pet.getRaca(),
                pet.getAnoNascimento(),
                pet.isVacinado(),
                pet.getProcedimentos());
        } else {
            return null;
        }
    }

    @Override
    public void remover(String id) {
        repositorio.deleteById(id);
    }

    
}
