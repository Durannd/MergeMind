package com.ricael.mergemind.domain;

import com.ricael.mergemind.domain.enums.Stacks;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_role")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Role name is required")
    private String name;


    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ElementCollection(targetClass = Stacks.class)
    @CollectionTable(name = "role_stack",
            joinColumns = @JoinColumn(name = "id_role"))
    @Column(name = "stack")
    @Enumerated(EnumType.STRING)
    private List<Stacks> stacks;

    @OneToMany(mappedBy = "role")
    private List<Application> applications;
}
