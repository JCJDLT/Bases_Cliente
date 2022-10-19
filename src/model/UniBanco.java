package model;

import java.util.ArrayList;

public class UniBanco {
	private int cedulaClienteLlamada = 0;
	private int idProductoLlamada = 0;
	
	private ArrayList<Cliente> listaClientes = new ArrayList<>();
	private ArrayList<Producto> listaProductos = new ArrayList<>();

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public int getCedulaClienteLlamada() {
		return cedulaClienteLlamada;
	}

	public void setCedulaClienteLlamada(int cedulaClienteLlamada) {
		this.cedulaClienteLlamada = cedulaClienteLlamada;
	}

	public int getIdProductoLlamada() {
		return idProductoLlamada;
	}

	public void setIdProductoLlamada(int idProductoLlamada) {
		this.idProductoLlamada = idProductoLlamada;
	}
}
