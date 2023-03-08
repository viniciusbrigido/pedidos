package com.brigido.pedidos.util;

import com.brigido.pedidos.dto.ItemPedidoDto;
import com.brigido.pedidos.entity.*;
import com.brigido.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoUtil {

    @Autowired
    private ProdutoService produtoService;

    public void validaPedido(PedidoEntity pedido) {
        if (pedido.isFechado()) {
            throw new RuntimeException("Pedido já fechado, não é possível alterar.");
        }
    }

    public List<ItemPedidoEntity> getItens(List<ItemPedidoDto> itens, PedidoEntity pedido) {
        return itens
                .stream()
                .map(item -> getItemPedidoEntityFromDto(item, pedido))
                .collect(Collectors.toList());
    }

    private ItemPedidoEntity getItemPedidoEntityFromDto(ItemPedidoDto itemPedidoDto, PedidoEntity pedido) {
        ProdutoEntity produto = produtoService.findById(itemPedidoDto.getProdutoId());
        if (produto.isInativo()) {
            throw new RuntimeException("Produto Inativo");
        }
        return ItemPedidoEntity.builder()
                .produto(produto)
                .pedido(pedido)
                .valorUnitario(produto.getValor())
                .quantidade(itemPedidoDto.getQuantidade())
                .build();
    }
}
