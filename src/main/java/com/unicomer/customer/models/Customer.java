package com.unicomer.customer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="customer")
public class Customer {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Must specify first name")
    @Pattern(regexp = "[a-zA-Z]+", message="Not match format first name")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message="Not match format last name")
    private String lastName;
    private LocalDate birthday;
    private String gender;
    private Long cellphone;
    private Long homePhone;
    private String addressHome;
    private String profession;
    private Double incomes;
}
