package com.brigido.pedidos.service.impl;

import com.brigido.pedidos.dto.ProdutoDto;
import com.brigido.pedidos.entity.ProdutoEntity;
import com.brigido.pedidos.repository.ProdutoRepository;
import com.brigido.pedidos.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ProdutoEntity save(ProdutoDto dto) {
        return produtoRepository.save(modelMapper.map(dto, ProdutoEntity.class));
    }

    @Override
    @Transactional
    public ProdutoEntity update(UUID id, ProdutoDto dto) {
        ProdutoEntity produto = findById(id);
        produto.update(dto);
        return produtoRepository.save(produto);
    }

    @Override
    public ProdutoEntity findById(UUID id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    @Override
    public void delete(UUID id) {
        ProdutoEntity produto = findById(id);
        produtoRepository.delete(produto);
    }

    @Override
    @Transactional
    public List<ProdutoEntity> list() {
        return produtoRepository.findAll();
    }
}
