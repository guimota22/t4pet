package br.com.tech4pet.t4pet.shared;

import java.util.List;

public record PetDto(String id, 
    String nome, 
    String raca, 
    int anoNascimento, 
    boolean vacinado, 
    List<String> procedimentos) {
    
}
