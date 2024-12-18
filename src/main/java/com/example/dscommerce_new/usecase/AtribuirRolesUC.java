package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.config.exceptions.IdAtribuidaComEntidade;
import com.example.dscommerce_new.dto.RoleRequestDTO;
import com.example.dscommerce_new.dto.UserAutenticadoDTO;
import com.example.dscommerce_new.infraestructure.repository.RoleRepository;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.Role;
import com.example.dscommerce_new.schema.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtribuirRolesUC {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserAutenticadoDTO execute(Long id, List<RoleRequestDTO> roles) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui user com o id: %s".formatted(id)));
        if (roles.isEmpty()){
            throw new EntidadeNaoEncontradaException("Não possui role para ser adicionado");
        }
        List<String> errors = new ArrayList<>();
        boolean addRole = false; //verificar ao final se algum role foi adicionado mesmo
        for (RoleRequestDTO role : roles) {
            Role roleFind = roleRepository.findById(role.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Não possui role com o id: %s".formatted(role.getId())));

            if(user.getRoles().contains(roleFind)) {
                errors.add("O role de id: %s e authority: %s já está vinculado ao user".formatted(roleFind.getId(), roleFind.getAuthority()));
            }

            if(!user.getRoles().contains(roleFind)) {
                user.addRole(roleFind);
                addRole = true; //foi adicionado um role
            }
        }

        if(!errors.isEmpty() && !addRole) { //se houver erros e não foi adicionado nenhum role dispara o erro    //caso tiver pelo menos algum erro e fosse adicionado algum role leva em consideração ao menos essa adição
            throw new IdAtribuidaComEntidade(String.join(" - ", errors));
        }

        userRepository.save(user);

        return modelMapper.map(user, UserAutenticadoDTO.class);
    }

}
