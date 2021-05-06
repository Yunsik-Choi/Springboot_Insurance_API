package com.Insurance.hm.client.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class ClientRepository {

    private final EntityManager em;

    public Client add(Client claim){

        return null;
    }

    public Client findByID(Long id){

        return null;
    }

    public Client update(Client claim){

        return null;
    }

    public Client delete(Client id){

        return null;
    }
}
