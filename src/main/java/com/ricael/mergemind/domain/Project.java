package com.ricael.mergemind.domain;

import com.ricael.mergemind.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    private String title;
    private String description;
    private String banner_url;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "project")
    List<Role> role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "projectsParticipated")
    List<User> participants;
}
