package com.brigido.pedidos.service;

import com.brigido.pedidos.dto.ProdutoDto;
import com.brigido.pedidos.entity.ProdutoEntity;
import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    ProdutoEntity findById(UUID id);
    ProdutoEntity save(ProdutoDto dto);
    ProdutoEntity update(UUID id, ProdutoDto dto);
    void delete(UUID id);
    List<ProdutoEntity> list();
}
