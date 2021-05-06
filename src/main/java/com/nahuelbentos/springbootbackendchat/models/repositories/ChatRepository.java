package com.nahuelbentos.springbootbackendchat.models.repositories;

import com.nahuelbentos.springbootbackendchat.models.documents.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Mensaje, String> {

    public List<Mensaje> findFirst10ByOrderByOrderByFechaDesc();
}
