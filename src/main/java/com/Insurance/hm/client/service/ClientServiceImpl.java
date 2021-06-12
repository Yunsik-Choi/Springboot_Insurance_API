package com.Insurance.hm.client.service;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.ClientRepository;
import com.Insurance.hm.client.dto.ClientCreateRequestDto;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public Long create(ClientCreateRequestDto clientCreateRequestDto) {
        Client client = clientCreateRequestDto.toEntity();
        clientRepository.save(client);
        return client.getId();
    }

    @Override
    public Client find(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> getNonMatchClientById());
        return client;
    }

    @Override
    public Long deleteById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> getNonMatchClientById());
        clientRepository.delete(client);
        return id;
    }

    @Override
    public List<Client> findAll() {
        List<Client> all = clientRepository.findAll();
        return all;
    }


    private NonMatchIdException getNonMatchClientById() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("Client"));
    }


}
