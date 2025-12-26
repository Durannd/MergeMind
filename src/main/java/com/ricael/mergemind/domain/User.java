package com.ricael.mergemind.domain;

import com.ricael.mergemind.domain.enums.Stacks;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_user")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "The email cannot be blank")
    private String name;

    @Email
    @NotBlank(message = "The email cannot be blank")
    private String email;

    @NotBlank(message = "The password cannot be blank")
    private String password;
    private String bio;
    private String github_url;
    private String photo_url;


    @OneToMany(mappedBy = "user")
    List<Project> projectsOwned = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    List<Project> projectsParticipated = new ArrayList<>();

    @ElementCollection(targetClass = Stacks.class)
    @CollectionTable(name = "user_stack", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "stack")
    @Enumerated(EnumType.STRING)
    private List<Stacks> stacks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Application> applications = new ArrayList<>();
}
