package org.example.gerenciadormaker.service;

import org.example.gerenciadormaker.dto.ItemPatchDTO;
import org.example.gerenciadormaker.dto.ItemRequestDTO;
import org.example.gerenciadormaker.model.Categoria;
import org.example.gerenciadormaker.repository.CategoriaRepository;
import org.example.gerenciadormaker.model.Item;
import org.example.gerenciadormaker.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ItemService {
    private ItemRepository itemRepository;
    private CategoriaRepository categoriaRepository;

    public ItemService(ItemRepository itemRepository,
                       CategoriaRepository categoriaRepository) {
        this.itemRepository = itemRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Item save(ItemRequestDTO dto) {
        Optional<Item> existente = itemRepository.findByNome(dto.getNome());
        if (existente.isPresent()) {
            throw new IllegalStateException("Já existe um item com esse nome");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada"));

        Item item = new Item();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setQuantidade(dto.getQuantidade());
        item.setCategoria(categoria);

        return itemRepository.save(item);
    }


    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ítem não encontrada"));
    }

    public List<Item> findAll() {return itemRepository.findAll();}

    public Item update(Long id, ItemPatchDTO dto) {
        Item item = findById(id);

        if(dto.getNome()!=null) {
            item.setNome(dto.getNome());
        }

        if(dto.getDescricao()!=null) {
            item.setDescricao(dto.getDescricao());
        }

        if (dto.getQuantidade() != null) {
            item.setQuantidade(dto.getQuantidade());
        }

        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        Item item = findById(id);
        itemRepository.deleteById(id);
    }

    public Item findByNome(String nome) {
        return itemRepository.findByNome(nome)
                .orElseThrow(() -> new IllegalArgumentException("Ítem não encontrado"));
    }
}
