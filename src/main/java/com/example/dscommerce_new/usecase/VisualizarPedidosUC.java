package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.dto.OrderDTO;
import com.example.dscommerce_new.infraestructure.repository.OrderRepository;
import com.example.dscommerce_new.schema.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisualizarPedidosUC {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public List<OrderDTO> execute(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }
}
