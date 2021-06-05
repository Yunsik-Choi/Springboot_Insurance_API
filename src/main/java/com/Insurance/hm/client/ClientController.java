package com.Insurance.hm.client;

import com.Insurance.hm.client.constants.ClientResponseConstants;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.dto.ClientCreateRequestDto;
import com.Insurance.hm.client.dto.ClientDetailDto;
import com.Insurance.hm.client.service.ClientService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseDto showAll(){
        List<Client> all = clientService.findAll();
        List<ClientDetailDto> responseAll = all.stream().map(i -> new ClientDetailDto(i)).collect(Collectors.toList());
        return ResponseDto.builder()
                .message(ClientResponseConstants.FIND_ALL.getMessage())
                .data(responseAll)
                .build();
    }

    @PostMapping("create")
    public void createClient(@RequestBody ClientCreateRequestDto clientCreateRequestDto,
                                    HttpServletResponse response) throws IOException {
        Long id = clientService.create(clientCreateRequestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    public ResponseDto findClientById(@PathVariable Long id){
        Client client = clientService.find(id);
        return ResponseDto.builder()
                .message(ClientResponseConstants.CLIENT_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(new ClientDetailDto(client))
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteClientById(@PathVariable Long id){
        Long deleteId = clientService.deleteById(id);
        return ResponseDto.builder()
                .message(ClientResponseConstants.CLIENT_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(deleteId)
                .build();
    }


}
