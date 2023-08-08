package com.userservice.userservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice.userservice.Product;
import com.userservice.userservice.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name="user_tbl")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="user_id")
    private UUID userId;

    @NotBlank(message = "first name cannot be empty or null")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message = "last name cannot be empty or null")
    @Column(name="last_name")
    private String lastName;

    @Email(message = "email is not valid")
    @Column(name = "user_email")
    private String userEmail;

    @PhoneNumberValidation
    @Column(name = "phonenumber")
    private String phonenumber;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}