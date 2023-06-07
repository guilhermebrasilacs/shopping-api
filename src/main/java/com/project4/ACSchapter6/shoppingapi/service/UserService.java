package com.project4.ACSchapter6.shoppingapi.service;

import com.project5.ACSchapter8.shoppingclient.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.project5.ACSchapter8.shoppingclient.dto.UserDTO;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private String userApiURL = "http://localhost:8080";

    public UserDTO getUserByCpf(String cpf, String key){
        try{
            WebClient webClient = WebClient.builder().baseUrl(userApiURL).build();
            Mono<UserDTO> user = webClient.get().uri("/user/" + cpf + "/cpf/key=" + key).retrieve().bodyToMono(UserDTO.class);
            return user.block();
        }catch(Exception e){
            throw new UserNotFoundException();
        }
    }
}
