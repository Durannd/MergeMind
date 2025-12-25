package com.ricael.mergemind.domain;

import com.ricael.mergemind.domain.enums.Stacks;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    private String name;

    private String email;
    private String password;
    private String bio;
    private String github_url;
    private String photo_url;

    @OneToMany(mappedBy = "user")
    List<Project> projectsOwned;

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    List<Project> projectsParticipated;

    @ElementCollection(targetClass = Stacks.class)
    @CollectionTable(name = "user_stack", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "stack")
    @Enumerated(EnumType.STRING)
    private List<Stacks> stacks;

    @OneToMany(mappedBy = "user")
    List<Application> applications;
}
