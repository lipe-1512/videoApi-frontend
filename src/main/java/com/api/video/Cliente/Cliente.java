package com.api.video.Cliente;

import com.api.video.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false, unique = true)
    private String emailCliente;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpfCliente;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Usuario usuario;
}
