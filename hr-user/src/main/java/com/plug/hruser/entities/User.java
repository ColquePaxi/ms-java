package com.plug.hruser.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	// Garantir que o email não pode se repetir 
	// O JPA criará essa constraint no database
	@Column(unique = true)
	private String email;
	private String password;
	
	// Relacionamento muitos para muitos
	// Porém não aceita repetição. Ex: 1 priprietário: Colque para carro: fzn-8408
	// Não pode aceitar essa mesma relação entre Colque -> fzn-8408 
	// No Java o Set<> é um CONJUNTO (não aceita repetição)
	// Quando não tem essa obrigatoriedade, se usa geralmente o List<>

	// @ManyToMany
	// Mas pode carregar os dados automaticamente junto com os do usuário
	// Em resumo: carrega os perfis assim que carrega o usuário
	@ManyToMany(fetch = FetchType.EAGER)
	
	// Definir a "tabela do meio" que guarda as 2 chaves das 2 tabelas
	// que farão o relacionamento muito-para-muitos
	@JoinTable(name = "tb_user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();
	// Lá embaixo é necessário implementar getters e setters p/ o artibuto roles
	
	
	public User() {
		
	}

	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Importante fazer esse getter!!!!
	public Set<Role> getRoles() {
		return roles;
	}

	// Opcional
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Opcional
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}