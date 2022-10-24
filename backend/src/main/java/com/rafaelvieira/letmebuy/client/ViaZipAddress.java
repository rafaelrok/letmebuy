package com.rafaelvieira.letmebuy.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.rafaelvieira.letmebuy.dto.AddressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author rafae
 * @RestControler faz a combinação de @Controller e @ResponseBody, simplificando a criação de serviços ‘web’ RESTFULL.
 */

@RestController
public class ViaZipAddress implements Serializable {

    private static final long serialVersionUID = 6046704732666502085L;

    /**
     * @PathVariable É utilizado quando o valor da variável é passada diretamente na (URL), sem a utilização de interrogação (?).
     * O valor poderá ser acessado at-raves do parameter cep que é do parecido com String.
     *
     * @GetMapping permite mapear solicitações HTTP GET, toda vez que uma solicitação GET para o endereço /getCep/{cep} foi disparada o método será executado.
     *
     * @param zipcode
     * @return ResponseEntity<AddressDTO>
     */
    @GetMapping(value="/getCep/{zipcode}")
    public ResponseEntity<AddressDTO> fromGetZipcode(@PathVariable(name = "zipcode") String zipcode) {
        //
        /*
         * Criando e instanciando um objeto do tipo RestTemplate, este objeto possui métodos que
         * irá permitir a comunicação com o webservice
         */
        RestTemplate restTemplate = new RestTemplate();

        /*
         * Criação de uma String com nome de uri, irá armazenar a uri (endereço) a ser consumido, observe que o cep é passado como parameter e o retorno é json.
         */
        String uri = "https://viacep.com.br/ws/{zipcode}/json/";

        /*
         * É possível passar mais de um parameter, entretanto só iremos utilizar o cep
         * observe que foi utilizado um Map. Para permit obter o valor pela chave
         */
        Map<String, String> params = new HashMap<String, String>();
        params.put("zipcode", zipcode);

        /*
         * restTemplate.getForObject(uri, CepTO.class, params);
         * 1 (URI) - Endereço do webservice que será consumido
         * 2 (EnderecoTO.class) - Classe que representa os dados do endereço e será mapeada no retorno da requisição com base no Json.
         * 3 (Params) - Parametros que serão utilizados na requisição, os mesmos serão includes na uri. Exemplo: {cep} será substitution pelo cep informado
         * Após a requisição ser conclusion, o retorno será armazenado no AddressDTO, com todos os dados já mapeados.
         */
        AddressDTO dto = restTemplate.getForObject(uri, AddressDTO.class, params);

        /*
         * ResponseEntity permite retornar para tela os dados encontrados, o primeiro parameter recebe os dados, o segundo o status do response.
         */
        return new ResponseEntity<AddressDTO>(dto, HttpStatus.OK);
    }

}
