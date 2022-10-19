package model;

import java.util.Date;

public class Cliente {
	private int cedula;
	private String nombre;
	private Date fecha;
	private String direccion;
	private String telefono;
	private String email;

	public Cliente(int cedula, String nombre, Date fecha, String direccion, String telefono, String email) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.fecha = fecha;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}
	
	public Cliente() {
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", nombre=" + nombre + ", fecha=" + fecha + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", email=" + email + "]";
	}
	
}
