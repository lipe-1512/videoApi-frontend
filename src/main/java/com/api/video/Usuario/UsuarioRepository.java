package com.api.video.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {


    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END
        FROM Cliente a
        WHERE a.emailCliente = :email
    """)
    boolean existsClienteByEmail(@Param("email") String email);

    @Query("""
    SELECT u
    FROM Usuario u
    LEFT JOIN FETCH u.cliente
    WHERE u.id = :id
""")
    Optional<Usuario> findUsuarioById(@Param("id") UUID id);



    @Query("""
    SELECT u
    FROM Usuario u
    LEFT JOIN FETCH u.cliente
    WHERE u.cliente.emailCliente = :email
""")
    Optional<Usuario> findByEmail(@Param("email") String email);


    @Transactional
    @Modifying
    @Query(value = """
        UPDATE cliente
        SET nome_cliente = :nome, email_cliente = :email, senha = :senha, cpf_cliente = :cpf
        WHERE email_cliente = :email
    """, nativeQuery = true)
    int editarDadosCliente(
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("senha") String senha,
            @Param("cpf") String cpf);

    @Transactional
    @Modifying
    @Query(value = """
        DELETE FROM usuario
        WHERE id = :id
    """, nativeQuery = true)
    int deletarUsuario(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = """
        INSERT INTO cliente (id, nome_cliente, email_cliente, senha, cpf_cliente)
        VALUES (:id, :nome, :email, :senha, :cpf)
    """, nativeQuery = true)
    int cadastrarClienteComUsuario(
            @Param("id") UUID id,
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("senha") String senha,
            @Param("cpf") String cpf);

    @Transactional
    @Modifying
    @Query(value = """
        INSERT INTO usuario (id, tipo, mailcheck)
        VALUES (:id, :tipo,  false)
    """, nativeQuery = true)
    void inserirUsuario(@Param("id") UUID id, @Param("tipo") String tipo);

}
