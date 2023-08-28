package com.kabz.springboot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department extends BaseEntity {

    @Column(nullable = false)
    @NotBlank(message = "Please add a Department name")
    private String name;
    private String address;
    private String code;

}
