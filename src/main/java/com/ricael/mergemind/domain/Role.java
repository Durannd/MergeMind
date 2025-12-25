package com.ricael.mergemind.domain;

import com.ricael.mergemind.domain.enums.Stacks;
import jakarta.persistence.*;
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

    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ElementCollection(targetClass = Stacks.class)
    @CollectionTable(name = "role_stack", // Nome da tabela no diagrama
            joinColumns = @JoinColumn(name = "id_role")) // FK que liga à Role)
    @Column(name = "stack")
    @Enumerated(EnumType.STRING)
    private List<Stacks> stacks;

    @OneToMany(mappedBy = "role")
    private List<Application> applications;
}
