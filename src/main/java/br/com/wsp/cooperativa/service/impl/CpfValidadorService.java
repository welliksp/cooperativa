package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.service.ICpfValidadorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
public class CpfValidadorService implements ICpfValidadorService {

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://user-info.herokuapp.com/users/";

    @Override
    public String validaCpf(String cpf) {

        try {

            String url = API_URL + cpf;
            CpfValidadorResponse response = restTemplate.getForObject(url, CpfValidadorResponse.class);

            return response.getStatus();
        } catch (HttpClientErrorException.NotFound e) {

            log.error(e.getMessage());
            throw new IllegalArgumentException("CPF INVÁLIDO");
        }
    }

    private static class CpfValidadorResponse {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
