package com.brigido.pedidos.repository;

import com.brigido.pedidos.entity.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, UUID> {

}

