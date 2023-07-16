package br.com.tech4pet.t4pet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4pet.t4pet.service.PetService;
import br.com.tech4pet.t4pet.shared.PetDto;
import br.com.tech4pet.t4pet.shared.PetListagemDto;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService servico;
    
    @GetMapping
    public ResponseEntity<List<PetListagemDto>> obterTodos(){
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> obterPorId(@PathVariable String id){
        Optional<PetDto> pet = servico.obterPorId(id);

        if(pet.isPresent()){
            return new ResponseEntity<>(pet.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PetDto> cadastrar(@RequestBody PetDto pet){
        return new ResponseEntity<>(servico.cadastrar(pet), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDto> atualizar(@PathVariable String id, @RequestBody PetDto pet){
        PetDto petAtualizado = servico.atualizar(id, pet);

        if( petAtualizado != null){
            return new ResponseEntity<>(petAtualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id){
        servico.remover(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
