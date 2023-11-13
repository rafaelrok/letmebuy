package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Address;
import com.rafaelvieira.letmebuy.entities.City;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rafae
 */

@Setter
@Getter
public class AddressDTO {

    private Integer id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipcode;
    private City city;

    public AddressDTO() {
    }

    public AddressDTO(Integer id, String street, String number, String complement, String neighborhood,
            String zipcode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipcode = zipcode;
    }

    public AddressDTO(Address entity) {
        this.id = entity.getId();
        this.street = entity.getStreet();
        this.number = entity.getNumber();
        this.complement = entity.getComplement();
        this.neighborhood = entity.getNeighborhood();
        this.zipcode = entity.getZipcode();
        this.city = entity.getCity();
    }

}
