package com.rafaelvieira.letmebuy.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.rafaelvieira.letmebuy.dto.CostumerDTO;
import com.rafaelvieira.letmebuy.dto.CostumerNewDTO;
import com.rafaelvieira.letmebuy.entities.Address;
import com.rafaelvieira.letmebuy.entities.City;
import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.entities.Role;
import com.rafaelvieira.letmebuy.enums.TypeCostumer;
import com.rafaelvieira.letmebuy.repository.AddressRepository;
import com.rafaelvieira.letmebuy.repository.CostumerRepository;
import com.rafaelvieira.letmebuy.repository.UserRepository;
import com.rafaelvieira.letmebuy.services.handlers.DataBaseException;
import com.rafaelvieira.letmebuy.services.handlers.ResourceNotFoundException;
import com.rafaelvieira.letmebuy.services.handlers.UnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CostumerService {
    @Autowired
    private CostumerRepository repo;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AuthService authService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Costumer find(Integer id) {
        User user = authService.authenticated();
        if (user==null || !user.hasRole("ROLE_ADMIN") && !id.equals(user.getId())) {
            throw new UnauthorizedException("Acesso negado");
        }

        Optional<Costumer> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Costumer.class.getName()));
    }

    @Transactional
    public Costumer insert(Costumer obj) {
        obj.setId(null);
        obj = repo.save(obj);
        addressRepository.saveAll(obj.getAddress());
        return obj;
    }

    public Costumer update(Costumer obj) {
        Costumer newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public List<Costumer> findAll() {
        return repo.findAll();
    }

    public User findByEmail(String email) {
        User user = authService.authenticated();
        if (user == null || !user.hasRole("ROLE_ADMIN") && !email.equals(user.getUsername())) {
            throw new UnauthorizedException("Acesso negado");
        }

        User obj = userRepository.findByEmail(email);
        if (obj == null) {
            throw new ResourceNotFoundException(
                    "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Costumer.class.getName());
        }
        return obj;
    }

    public Page<Costumer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Costumer fromDTO(CostumerDTO objDto) {
        return new Costumer(
                objDto.getId(),
                objDto.getFirstName(),
                objDto.getLastName(),
                null,
                null);
    }

    public Costumer fromDTO(CostumerNewDTO objDto) {
        Costumer costumer = new Costumer(
                null,
                objDto.getFirstName(),
                objDto.getLastName(),
                objDto.getCpfOuCnpj(),
                TypeCostumer.toEnum(objDto.getType()));
        User user = new User(
                null,
                objDto.getEmail(),
                pe.encode(objDto.getPassword())
        );
        City city = new City(
                objDto.getCityId(),
                null,
                null);
        Address address = new Address(
                null,
                objDto.getStreet(),
                objDto.getNumber(),
                objDto.getComplement(),
                objDto.getNeighborhood(),
                objDto.getZipcode(),
                costumer, city);
        costumer.getAddress().add(address);
        costumer.setUser(user);
        costumer.getPhones().add(objDto.getPhone1());
        if (objDto.getPhone2()!=null) {
            costumer.getPhones().add(objDto.getPhone2());
        }
        if (objDto.getPhone3()!=null) {
            costumer.getPhones().add(objDto.getPhone3());
        }
        return costumer;
    }

    private void updateData(Costumer newObj, Costumer obj) {
        newObj.setFirstName(obj.getFirstName());
        newObj.setLastName(obj.getFirstName());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        User user = authService.authenticated();
        if (user == null) {
            throw new UnauthorizedException("Acesso negado");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String fileName = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
