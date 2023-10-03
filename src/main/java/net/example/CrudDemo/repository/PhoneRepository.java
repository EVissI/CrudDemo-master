package net.example.CrudDemo.repository;

import net.example.CrudDemo.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneNumber,Long> {
}
