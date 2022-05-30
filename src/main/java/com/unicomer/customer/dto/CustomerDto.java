package com.unicomer.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CustomerDto {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "Must specify first name")
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    private String gender;
    private Long cellphone;
    private Long homePhone;
    private String addressHome;
    private String profession;
    private Double incomes;
}
