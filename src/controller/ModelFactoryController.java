package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Aplicacion;
import model.Cliente;
import model.Producto;
import model.UniBanco;

public class ModelFactoryController {
	private Aplicacion main;

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private UniBanco unibanco = new UniBanco();
	private int personaLogeada;

	// ------------------------------ Singleton
	// ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {
		abrirConexion();
	}

	public final void abrirConexion() {
		try { // servidor //usuario //clave

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CLIENTE", "root");
			stmt = con.createStatement();
			// ResultSet rs=stmt.executeQuery("use PRUEBA;");
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public final void cerrarConexion() {
		try {
			// System.out.println("DESCONECTADO");
			stmt.close();
			con.close();
			rs.close();
			System.out.println("DESCONECTADO");
		} catch (SQLException ex) {
			Logger.getLogger(ModelFactoryController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int validarLogin(String correo, String clave) {
		try {
			String query = "select e.cedula,e.email,e.clave,c.id_cargo from empleado e join cargo c on e.cargo_id_cargo = c.id_cargo";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (correo.equalsIgnoreCase(rs.getString("EMAIL")) && clave.equals(rs.getString("CLAVE"))) {
					personaLogeada = rs.getInt("CEDULA");
					return rs.getInt("ID_CARGO");
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFactoryController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}

	public void obtenerClientes() {
		unibanco.getListaClientes().clear();
		try {
			String query = "select c.cedula,c.nombre,c.fecha_nacimiento,c.direccion,c.telefono,c.email from cliente c";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCedula(rs.getInt(1));
				cliente.setNombre(rs.getString(2));
				cliente.setFecha(rs.getDate(3));
				cliente.setDireccion(rs.getString(4));
				cliente.setTelefono(rs.getString(5));
				cliente.setEmail(rs.getString(6));
				unibanco.getListaClientes().add(cliente);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFactoryController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void obtenerProductos() {
		unibanco.getListaProductos().clear();
		try {
			String query = "select p.id_producto,p.nombre,p.descripcion from producto p";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setId(rs.getInt(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				unibanco.getListaProductos().add(producto);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ModelFactoryController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void registrarLlamada(String fecha,String respuestaCliente,String contestoLlamada,int cliente,int producto) {
		char respuestaClienteChar = convertirStringChar(respuestaCliente);
		char respuestaLlamadaChar = convertirStringChar(contestoLlamada);
		
		try {
            String query = "insert into llamada values (null,'"+fecha+"',"+respuestaClienteChar+","+respuestaLlamadaChar+","+cliente+","+producto+","+personaLogeada+")";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFactoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
		//System.out.println("Fecha: "+fecha+" Res Cliente: "+respuestaClienteChar+" Res Llamada: "+respuestaLlamadaChar+" Cliente: "
		//		+cliente+" Producto: "+producto+" Empleado: "+personaLogeada);
	}
	
	char convertirStringChar(String respuesta) {
		char respuestaChar = '0';
		if(!respuesta.equals("Si")) {
			respuestaChar = '1';
		}
		return respuestaChar;
	}
	
	public String obtenerNombreEmpleadoLogueado(){
        try {
            String query = "select e.nombre from empleado e WHERE e.cedula ="+personaLogeada;
            rs = stmt.executeQuery(query);
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFactoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
}

	public UniBanco getUnibanco() {
		return unibanco;
	}

	public void setUnibanco(UniBanco unibanco) {
		this.unibanco = unibanco;
	}

	public Aplicacion getMain() {
		return main;
	}

	public void setMain(Aplicacion main) {
		this.main = main;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ArrayList<Cliente> getListaClientes() {
		return unibanco.getListaClientes();
	}

	public ArrayList<Producto> getListaProductos() {
		return unibanco.getListaProductos();
	}
	
	public void setCedulaLlamada(int cedula) {
		unibanco.setCedulaClienteLlamada(cedula);
	}

	public void setIdProductoLlamada(int idProducto) {
		unibanco.setIdProductoLlamada(idProducto);
	}

	public int getPersonaLogeada() {
		return personaLogeada;
	}

	public void setPersonaLogeada(int personaLogeada) {
		this.personaLogeada = personaLogeada;
	}

}
