package com.brigido.pedidos.service;

import com.brigido.pedidos.dto.AplicaDescontoPedidoDto;
import com.brigido.pedidos.dto.PedidoDto;
import com.brigido.pedidos.entity.PedidoEntity;
import java.util.List;
import java.util.UUID;

public interface PedidoService {

    PedidoEntity saveAndFlush(PedidoEntity pedido);
    PedidoEntity create(PedidoDto dto);
    PedidoEntity aplicaDesconto(UUID id, AplicaDescontoPedidoDto dto);
    PedidoEntity findById(UUID id);
    void delete(UUID id);
    List<PedidoEntity> list();
    PedidoEntity fechar(UUID id);
}
