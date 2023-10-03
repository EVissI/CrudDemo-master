package net.example.CrudDemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "number")
    private Long phoneNumber;

    @Column(name = "comment")
    private String phoneComment;

    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;
}
