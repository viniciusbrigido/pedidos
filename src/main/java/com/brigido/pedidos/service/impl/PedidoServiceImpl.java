package com.brigido.pedidos.service.impl;

import com.brigido.pedidos.dto.AplicaDescontoPedidoDto;
import com.brigido.pedidos.dto.PedidoDto;
import com.brigido.pedidos.entity.*;
import com.brigido.pedidos.repository.PedidoRepository;
import com.brigido.pedidos.service.PedidoService;
import com.brigido.pedidos.util.PedidoUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoUtil pedidoUtil;

    @Override
    @Transactional
    public PedidoEntity saveAndFlush(PedidoEntity pedido) {
        return pedidoRepository.saveAndFlush(pedido);
    }

    @Override
    @Transactional
    public PedidoEntity create(PedidoDto pedidoDto) {
        PedidoEntity pedido = new PedidoEntity();

        pedido.setItens(pedidoUtil.getItens(pedidoDto.getItens(), null));
        pedido.calcularTotaisDoPedido(pedidoDto.getPercentualDesconto());

        return saveAndFlush(pedido);
    }

    @Override
    @Transactional
    public PedidoEntity aplicaDesconto(UUID id, AplicaDescontoPedidoDto dto) {
        PedidoEntity pedido = findById(id);
        pedidoUtil.validaPedido(pedido);
        pedido.calcularTotaisDoPedido(dto.getPercentualDesconto());
        return saveAndFlush(pedido);
    }

    @Override
    public PedidoEntity findById(UUID id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }

    @Override
    public void delete(UUID id) {
        PedidoEntity pedido = findById(id);
        pedidoRepository.delete(pedido);
    }

    @Override
    @Transactional
    public List<PedidoEntity> list() {
        return pedidoRepository.findAll();
    }

    @Override
    public PedidoEntity fechar(UUID id) {
        PedidoEntity pedido = findById(id);
        pedido.fechar();
        return saveAndFlush(pedido);
    }
}
