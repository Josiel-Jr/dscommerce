package com.example.dscommerce_new.usecase;

import com.example.dscommerce_new.config.exceptions.EntidadeNaoEncontradaException;
import com.example.dscommerce_new.dto.UserAutenticadoDTO;
import com.example.dscommerce_new.infraestructure.repository.UserRepository;
import com.example.dscommerce_new.schema.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetornarUsuarioAutenticadoUC {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserAutenticadoDTO execute() {
        User user = new User();

        SecurityContext securityContext = SecurityContextHolder.getContext();

        if(securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if(authentication.getPrincipal() instanceof Jwt) {
                Jwt jwt = (Jwt) authentication.getPrincipal();
                String email = jwt.getClaimAsString("username");
                user = userRepository.findByEmail(email);
                if(user==null){
                    throw new EntidadeNaoEncontradaException("Não há user com o username: %s".formatted(email));
                }
                return modelMapper.map(user, UserAutenticadoDTO.class);
            }
        }

        return null;
    }
}
