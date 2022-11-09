package model.entities;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String phone;
	private String name;
	private String email;
	private String nacionalidade;
	private String passowrd;
	private String cpf;
	private boolean is_adm;

	
	public User() {
	}

	public User(Integer id, String phone, String name, String email, String nacionalidade, String passowrd, String cpf, boolean is_adm) {
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.nacionalidade = nacionalidade;
		this.passowrd = passowrd;
		this.cpf = cpf;
		this.is_adm = is_adm;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return this.cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setPassword(String password) {
		this.passowrd = password;
	}
	
	public String getPassowrd() {
		return this.passowrd;
	}

	public String getPhone() {
		return phone;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public boolean isIs_adm() {
		return is_adm;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public void setIs_adm(boolean is_adm) {
		this.is_adm = is_adm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", phone='" + phone + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", nacionalidade='" + nacionalidade + '\'' +
				", passowrd='" + passowrd + '\'' +
				", cpf='" + cpf + '\'' +
				", is_adm=" + is_adm +
				'}';
	}
}
