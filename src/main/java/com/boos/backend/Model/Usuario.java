package com.boos.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUsuario;

//    @OneToOne
//    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_ROL"))
//    private Rol rol;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="id_user", referencedColumnName="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="id_role", referencedColumnName="idRol")
    )
    private List<Rol> roles;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private boolean estado;
}