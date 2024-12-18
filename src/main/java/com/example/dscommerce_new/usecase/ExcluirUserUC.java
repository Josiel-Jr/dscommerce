package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.config.exceptions.RoleAdmin;
import com.example.dscommerce_new.dto.UserDTO;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirUserUC {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserDTO execute(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui user com o id: %s".formatted(id)));
        if(user.hasRole("Admin")){
            throw new RoleAdmin("O user tem role administrador e não poderá ser excluído");
        }
        userRepository.delete(user);
        return modelMapper.map(user, UserDTO.class);
    }
}
