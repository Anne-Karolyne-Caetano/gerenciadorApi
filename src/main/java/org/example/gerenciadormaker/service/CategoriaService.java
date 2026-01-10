package org.example.gerenciadormaker.service;

import org.example.gerenciadormaker.dto.CategoriaPatchDTO;
import org.example.gerenciadormaker.dto.CategoriaRequestDTO;
import org.example.gerenciadormaker.model.Categoria;
import org.example.gerenciadormaker.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public Categoria cadastrar(CategoriaRequestDTO dto) {

        Optional<Categoria> existente =
                categoriaRepository.findByNome(dto.getNome());

        if (existente.isPresent()) {
            throw new IllegalStateException("Já existe uma categoria com esse nome");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPorId(long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizar(Long id, CategoriaPatchDTO dto) {
        Categoria categoria = buscarPorId(id);

        if (dto.getNome() != null) {
            categoria.setNome(dto.getNome());
        }

        if (dto.getDescricao() != null) {
            categoria.setDescricao(dto.getDescricao());
        }

        return categoriaRepository.save(categoria);
    }


    public void deletar(long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }


    public Categoria buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
    }


}
