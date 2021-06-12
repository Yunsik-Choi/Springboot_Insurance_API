package com.Insurance.hm.client.service;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.dto.ClientCreateRequestDto;

import java.util.List;

public interface ClientService {

    Long create(ClientCreateRequestDto clientCreateRequestDto);

    Client find(Long id);

    Long deleteById(Long id);

    List<Client> findAll();

}
