package vistasController;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controller.ModelFactoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;
import model.Producto;

public class LlamadaController implements Initializable {
	
	@FXML
    private Label nombre;
	
	@FXML
    private TableView<Cliente> tablaCliente;

    @FXML
    private TableColumn<Cliente, Integer> cedulaCol;

    @FXML
    private TableColumn<Cliente, String> nombreCol;

    @FXML
    private TableColumn<Cliente, String> telefonoCol;

    @FXML
    private TableColumn<Cliente, String> emailCol;
    
	private ModelFactoryController mfc = ModelFactoryController.getInstance();

	private ObservableList<Cliente> observableListClientes = FXCollections.observableList(mfc.getListaClientes());
	
	private int cedulaClienteSeleccionado;
	
	@FXML
    private TextField buscarCliente;
	
	@FXML
    private TableView<Producto> tablaProducto;

    @FXML
    private TableColumn<Producto, Integer> idColProducto;

    @FXML
    private TableColumn<Producto, String> nombreColProducto;

    @FXML
    private TableColumn<Producto, String> descColProducto;
	
    private ObservableList<Producto> observableListProductos = FXCollections.observableList(mfc.getListaProductos());
    
	private int idProductoSeleccionado;

    @FXML
    private TextField buscarProducto;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		nombre.setText(mfc.obtenerNombreEmpleadoLogueado());
		mfc.obtenerClientes();
		cedulaCol.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
		nombreCol.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		telefonoCol.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
		tablaCliente.setItems(observableListClientes);
		
		mfc.obtenerProductos();
		idColProducto.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nombreColProducto.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		descColProducto.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
		tablaProducto.setItems(observableListProductos);
		
		FilteredList<Cliente> filteredData = new FilteredList<>(observableListClientes, p -> true);
	     tablaCliente.setItems(filteredData);
	     
	     buscarCliente.textProperty().addListener((prop, old, text) -> {
	         filteredData.setPredicate(cliente -> {
	             if(text == null || text.isEmpty()) return true;
	             
	             String codigoCliente = cliente.getNombre().toLowerCase();  
	             return codigoCliente.contains(text.toLowerCase());
	         });
	     });
		
		FilteredList<Producto> filteredData2 = new FilteredList<>(observableListProductos, p -> true);
	     tablaProducto.setItems(filteredData2);
	    
	     buscarProducto.textProperty().addListener((prop, old, text) -> {
	         filteredData2.setPredicate(producto -> {
	             if(text == null || text.isEmpty()) return true;
	             
	             String codigoProducto = producto.getNombre().toLowerCase();  
	             return codigoProducto.contains(text.toLowerCase());
	         });
	     });
	      
	}

	@FXML
	void realizarLlamada() {
		
		if(tablaCliente.getSelectionModel().getSelectedItem() != null && tablaProducto.getSelectionModel().getSelectedItem() != null) {
			cedulaClienteSeleccionado = tablaCliente.getSelectionModel().getSelectedItem().getCedula();
			idProductoSeleccionado = tablaProducto.getSelectionModel().getSelectedItem().getId();
			mfc.setCedulaLlamada(cedulaClienteSeleccionado);
			mfc.setIdProductoLlamada(idProductoSeleccionado);
			mfc.getMain().abrirPaginaFinLlamada();
		}else {
			JOptionPane.showMessageDialog( null, "Selecciones un cliente y un producto" );
		}
	}
}
