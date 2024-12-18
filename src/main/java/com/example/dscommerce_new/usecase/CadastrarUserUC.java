package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.config.exceptions.IdAtribuidaComEntidade;
import com.example.dscommerce_new.config.exceptions.UsernameAtribuidoComEntidade;
import com.example.dscommerce_new.dto.UserDTO;
import com.example.dscommerce_new.infraestructure.repository.RoleRepository;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.Role;
import com.example.dscommerce_new.schema.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CadastrarUserUC {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserDTO execute(UserDTO userDTO) {
        userRepository.findById(userDTO.getId()).
                ifPresent(user -> {
                    throw new IdAtribuidaComEntidade("O id: %s. Encontra-se vinculado a outro user".formatted(userDTO.getId()));
                });
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user!=null){
            throw new UsernameAtribuidoComEntidade("O username já está sendo utilizado");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        user=modelMapper.map(userDTO, User.class);
        Role role = roleRepository.findById(1L).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui role com o id: %s".formatted(1)));
        user.addRole(role);

        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
}
