package org.example.gerenciadormaker.controller;

import jakarta.validation.Valid;
import org.example.gerenciadormaker.dto.CategoriaPatchDTO;
import org.example.gerenciadormaker.dto.CategoriaRequestDTO;
import org.example.gerenciadormaker.model.Categoria;
import org.example.gerenciadormaker.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(
            @Valid @RequestBody CategoriaRequestDTO dto) {

        Categoria categoria = categoriaService.cadastrar(dto);
        return ResponseEntity.ok(categoria);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> atualizarParcial(
            @PathVariable Long id,
            @RequestBody CategoriaPatchDTO dto) {

        Categoria categoria = categoriaService.atualizar(id, dto);
        return ResponseEntity.ok(categoria);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Categoria> buscarPorNome(@PathVariable String nome) {
        Categoria categoria = categoriaService.buscarPorNome(nome);
        return ResponseEntity.ok(categoria);
    }
}




















/*
package org.example.gerenciadormaker.controller;
import org.example.gerenciadormaker.model.Categoria;
import org.example.gerenciadormaker.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Categoria categoria) { //ResponseEntity<?> ->Eu decido exatamente o que a API responde. <?> possibilita receber tipos de dados diferentes sem dar erro

        // 1. verificar se já existe categoria com esse nome
        Optional<Categoria> existente =
                categoriaRepository.findByNome(categoria.getNome());

        // 2. se existir, responder conflito
        if (existente.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Já existe uma categoria com esse nome");
        }

        // 3. salvar normalmente
        Categoria salva = categoriaRepository.save(categoria);

        // 4. responder sucesso
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salva);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable long id) {

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Não encontrado");
        }
        return ResponseEntity.ok(categoriaOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();//buscar a lista
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable long id,
                               @RequestBody Categoria categoriaAtualizada) {
        Optional<Categoria> catExistente = categoriaRepository.findById(id);
        if(catExistente.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Não encontrado");
        }

        Categoria categoria = catExistente.get();
        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());

        Categoria salva = categoriaRepository.save(categoria);

        return ResponseEntity.ok(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Não encontrado");
        }

        categoriaRepository.delete(categoriaOptional.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Deletado com sucesso");
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(nome);
        if (categoriaOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Não encontrado");
        }
        return ResponseEntity.ok(categoriaOptional.get());
    }
}
*/