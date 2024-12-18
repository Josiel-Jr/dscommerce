package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.config.exceptions.IdAtribuidaComEntidade;
import com.example.dscommerce_new.dto.*;
import com.example.dscommerce_new.infraestructure.repository.OrderRepository;
import com.example.dscommerce_new.infraestructure.repository.PaymentRepository;
import com.example.dscommerce_new.infraestructure.repository.ProductRepository;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrarPedidoUC {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final RetornarUsuarioAutenticadoUC retornarUsuarioAutenticadoUC;
    private final RegistrarPaymentUC registrarPaymentUC;
    private final ModelMapper modelMapper;

    @Transactional
    public OrderDTO execute(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        orderRepository.findById(orderRequestDTO.getId()).
                ifPresent(order -> {
                    throw new IdAtribuidaComEntidade("Já possui order com o id: %s".formatted(orderRequestDTO.getId()));
                });

        PaymentDTO paymentDTO = registrarPaymentUC.execute(orderRequestDTO.getPaymentId());

        orderRepository.paymentInOrder(paymentDTO.getId()).ifPresent(
                order -> {
                    throw new IdAtribuidaComEntidade("O payment com id: %s. Encontra-se atribuído a uma order".formatted(paymentDTO.getId()));
                }
        );

        Order order = modelMapper.map(orderRequestDTO, Order.class);

        order.setMoment(Instant.now());

        UserAutenticadoDTO userAutenticadoDTO = retornarUsuarioAutenticadoUC.execute();
        User user = userRepository.findById(userAutenticadoDTO.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui o user com o id: %s".formatted(userAutenticadoDTO.getId())));
        order.setUser(user);

        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for(OrderItemRequestDTO orderItemRequestDTO : orderRequestDTO.getItems()){
            Product product = productRepository.findById(orderItemRequestDTO.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui produto com o id: %s".formatted(orderItemRequestDTO.getId())));
            OrderItemDTO orderItemDto = new OrderItemDTO();
            OrderItemPKDTO orderItemPKDTO = new OrderItemPKDTO();
            orderItemPKDTO.setProduct(modelMapper.map(product, ProductDTO.class));
            orderItemDto.setId(orderItemPKDTO);
            orderItemDto.setQuantity(orderItemRequestDTO.getQuantity());
            orderItemDTOS.add(orderItemDto);
        }


        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemDTO orderItemDTO : orderItemDTOS){
            OrderItem orderItem = new OrderItem();
            OrderItemPK orderItemPK = new OrderItemPK();
            orderItemPK.setProduct(modelMapper.map(orderItemDTO.getId().getProduct(), Product.class));
            orderItemPK.setOrder(order);
            orderItem.setId(orderItemPK);
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(orderItemDTO.getPrice());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);

        Payment payment = paymentRepository.findById(orderRequestDTO.getPaymentId()).get();

        order.setPayment(payment);

        order = orderRepository.saveAndFlush(order);

        payment.setOrder(order);

        paymentRepository.save(payment);

        return modelMapper.map(order, OrderDTO.class);
    }
}
