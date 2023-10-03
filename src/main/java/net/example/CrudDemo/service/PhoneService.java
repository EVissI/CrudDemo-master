package net.example.CrudDemo.service;

import net.example.CrudDemo.model.PhoneNumber;
import net.example.CrudDemo.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Optional<PhoneNumber> getPhoneById(Long id) {
        return phoneRepository.findById(id);
    }

    public List<PhoneNumber> getAllPhones() {
        return phoneRepository.findAll();
    }
    public PhoneNumber savePhone(PhoneNumber phone) {
        return phoneRepository.save(phone);
    }

    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }
}
