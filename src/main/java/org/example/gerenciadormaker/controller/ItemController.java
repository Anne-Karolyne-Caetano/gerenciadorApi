package org.example.gerenciadormaker.controller;

import jakarta.validation.Valid;
import org.example.gerenciadormaker.dto.CategoriaPatchDTO;
import org.example.gerenciadormaker.dto.ItemPatchDTO;
import org.example.gerenciadormaker.dto.ItemRequestDTO;
import org.example.gerenciadormaker.model.Categoria;
import org.example.gerenciadormaker.model.Item;
import org.example.gerenciadormaker.repository.ItemRepository;
import org.example.gerenciadormaker.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> cadastrar(@Valid @RequestBody ItemRequestDTO dto) {
         Item item = itemService.save(dto);
         return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Item item = itemService.findById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> listarTodas() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> atualizarParcial(
            @PathVariable Long id,
            @RequestBody ItemPatchDTO dto) {

        Item item = itemService.update(id, dto);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Item> buscarPorNome(@PathVariable String nome) {
        Item item = itemService.findByNome(nome);
        return ResponseEntity.ok(item);
    }
}
