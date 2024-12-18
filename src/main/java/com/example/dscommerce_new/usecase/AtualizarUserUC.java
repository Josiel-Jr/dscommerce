package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.UsernameAtribuidoComEntidade;
import com.example.dscommerce_new.dto.UserAutenticadoDTO;
import com.example.dscommerce_new.dto.UserRequestUpdateDTO;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarUserUC {
    private final UserRepository userRepository;
    private final RetornarUsuarioAutenticadoUC retornarUsuarioAutenticadoUC;
    private final ModelMapper modelMapper;

    public UserAutenticadoDTO execute(UserRequestUpdateDTO userDTO){
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user != null && !(retornarUsuarioAutenticadoUC.execute().getEmail().equals(user.getEmail()))){
            throw new UsernameAtribuidoComEntidade("O username já está sendo utilizado");
        }
        UserAutenticadoDTO userAutenticadoDTO = retornarUsuarioAutenticadoUC.execute();
        user=userRepository.findById(userAutenticadoDTO.getId()).get();
        user.setName(userDTO.getName()!=null?userDTO.getName():user.getName());
        user.setEmail(userDTO.getEmail()!=null?userDTO.getEmail():user.getEmail());
        user.setPhone(userDTO.getPhone()!=null?userDTO.getPhone():user.getPhone());
        userRepository.save(user);
        return modelMapper.map(user, UserAutenticadoDTO.class);
    }
}
