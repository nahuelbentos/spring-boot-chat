package com.nahuelbentos.springbootbackendchat.models.services;

import com.nahuelbentos.springbootbackendchat.models.documents.Mensaje;
import com.nahuelbentos.springbootbackendchat.models.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Mensaje> getLast10Mensajes(){
        return chatRepository.findFirst10ByOrderByOrderByFechaDesc();
    }

    public Mensaje save(Mensaje mensaje){
        return chatRepository.save(mensaje);
    }
}
