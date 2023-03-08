package com.brigido.pedidos.service;

import com.brigido.pedidos.dto.AdicionaItemPedidoDto;
import com.brigido.pedidos.dto.AtualizaItemPedidoDto;
import com.brigido.pedidos.entity.ItemPedidoEntity;
import com.brigido.pedidos.entity.PedidoEntity;
import java.util.List;
import java.util.UUID;

public interface ItemPedidoService {

    PedidoEntity create(AdicionaItemPedidoDto dto);
    ItemPedidoEntity findById(UUID id);
    PedidoEntity update(AtualizaItemPedidoDto dto);
    void delete(UUID id);
    List<ItemPedidoEntity> list();
}
