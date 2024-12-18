package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.dto.UserComRolesDTO;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarRolesUC {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserComRolesDTO execute(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe usuário com o id: %s".formatted(id)));
        UserComRolesDTO userComRolesDTO = modelMapper.map(user, UserComRolesDTO.class);
        return userComRolesDTO;
    }
}
