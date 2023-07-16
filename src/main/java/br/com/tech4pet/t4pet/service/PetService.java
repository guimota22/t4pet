package br.com.tech4pet.t4pet.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4pet.t4pet.shared.PetDto;
import br.com.tech4pet.t4pet.shared.PetListagemDto;

public interface PetService {
    List<PetListagemDto> obterTodos();

    Optional<PetDto> obterPorId(String id);
    PetDto cadastrar(PetDto pet);
    PetDto atualizar(String id, PetDto pet);
    void remover(String id);
}
