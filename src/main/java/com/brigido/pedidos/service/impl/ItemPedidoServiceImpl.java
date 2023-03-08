package com.brigido.pedidos.service.impl;

import com.brigido.pedidos.dto.AdicionaItemPedidoDto;
import com.brigido.pedidos.dto.AtualizaItemPedidoDto;
import com.brigido.pedidos.entity.ItemPedidoEntity;
import com.brigido.pedidos.entity.PedidoEntity;
import com.brigido.pedidos.repository.ItemPedidoRepository;
import com.brigido.pedidos.service.ItemPedidoService;
import com.brigido.pedidos.service.PedidoService;
import com.brigido.pedidos.util.PedidoUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoUtil pedidoUtil;

    @Override
    @Transactional
    public PedidoEntity create(AdicionaItemPedidoDto adicionaItemPedidoDto) {
        PedidoEntity pedido = pedidoService.findById(adicionaItemPedidoDto.getPedidoId());
        List<ItemPedidoEntity> itens = pedidoUtil.getItens(adicionaItemPedidoDto.getItens(), pedido);
        itemPedidoRepository.saveAll(itens);

        pedido.getItens().addAll(itens);
        pedido.calcularTotaisDoPedido();
        return pedidoService.saveAndFlush(pedido);
    }

    @Override
    public ItemPedidoEntity findById(UUID id) {
        return itemPedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Item do Pedido não encontrado."));
    }

    @Override
    @Transactional
    public PedidoEntity update(AtualizaItemPedidoDto dto) {
        ItemPedidoEntity item = findById(dto.getItemId());
        item.update(dto);
        itemPedidoRepository.save(item);

        PedidoEntity pedido = item.getPedido();
        pedido.calcularTotaisDoPedido();
        return pedidoService.saveAndFlush(pedido);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        ItemPedidoEntity item = findById(id);
        PedidoEntity pedido = item.getPedido();

        itemPedidoRepository.delete(item);
        pedido.calcularTotaisDoPedido();
        pedidoService.saveAndFlush(pedido);
    }

    @Override
    @Transactional
    public List<ItemPedidoEntity> list() {
        return itemPedidoRepository.findAll();
    }
}
