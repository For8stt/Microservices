package com.example.demo.catfact;

import com.example.demo.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatFactService implements Query<Integer,CatFactDTO> {

    private final String url="https://catfact.ninja/fact";
    private final String MAX_length="max_length";



    private final RestTemplate restTemplate;
    public CatFactService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam(MAX_length,input)
                .build()
                .toUri();

        HttpHeaders headers=new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<String> entity= new HttpEntity<>(headers);

        try {
            ResponseEntity<CatFactResponse> response=restTemplate
                    .exchange(uri, HttpMethod.GET,entity,CatFactResponse.class);
            CatFactDTO catFactDTO=new CatFactDTO(response.getBody().getFact());
            return ResponseEntity.ok(catFactDTO);
        }catch (Exception exception) {
            throw new RuntimeException("Cat facts api is down");

        }



//        CatFactResponse response=restTemplate.getForObject("https://catfact.ninja/fact?max_length="+input,CatFactResponse.class);
//        CatFactDTO catFactDTO=new CatFactDTO(response.getFact());
//        return ResponseEntity.ok(catFactDTO);
    }
}
