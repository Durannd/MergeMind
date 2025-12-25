package com.ricael.mergemind.domain;

import com.ricael.mergemind.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_project")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "The title cannot be blank")
    private String title;
    private String description;
    private String banner_url;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "project")
    List<Role> role = new ArrayList<>();

    @NotNull(message = "The project must have an owner")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "projectsParticipated")
    List<User> participants = new ArrayList<>();
}
