package com.rafaelvieira.letmebuy.dto;

import com.rafaelvieira.letmebuy.entities.Address;
import com.rafaelvieira.letmebuy.entities.City;
import com.rafaelvieira.letmebuy.entities.Costumer;

import java.io.Serializable;

/**
 * @author rafae
 */
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipcode;
    private Costumer costumer;
    private City city;

    public AddressDTO() {
    }

    public AddressDTO(Integer id, String street, String number, String complement, String neighborhood, String zipcode) {
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
        this.costumer = entity.getCostumer();
        this.city = entity.getCity();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
