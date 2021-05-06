package com.nahuelbentos.springbootbackendchat.controller;

import com.nahuelbentos.springbootbackendchat.models.documents.Mensaje;
import com.nahuelbentos.springbootbackendchat.models.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class ChatController {

    private String[] colores = {"red", "gren", "blue", "magenta", "purple", "orange"};

    private final SimpMessagingTemplate webSocket;
    private final ChatService chatService;

    public ChatController(ChatService chatService, SimpMessagingTemplate webSocket) {
        this.chatService = chatService;
        this.webSocket = webSocket;
    }

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor( colores[new Random().nextInt( colores.length )]);
            mensaje.setTexto("nuevo usuario");
//            mensaje.setTexto(" Recibido por el Broker: " + mensaje.getTexto());
        } else {
            chatService.save( mensaje );
        }
        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username){
        return username.concat(" esta escribiendo ...");
    }

    @MessageMapping("/historial")
    public void historial(String clienteId){
        webSocket.convertAndSend("/chat/historial/"+clienteId, chatService.getLast10Mensajes());

    }

}
