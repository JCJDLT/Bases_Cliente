package vistasController;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.ModelFactoryController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FinLlamadaController implements Initializable{

	private ModelFactoryController mfc = ModelFactoryController.getInstance();
	
	@FXML
    private ComboBox<String> comboContestada;

    @FXML
    private ComboBox<String> comboRespuesta;
    
    @FXML
    private JFXButton finalizar;
    
     
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		inicializarCombos();
	}
	
	@FXML
    public void finalizarLlamada() {
		int cedula = mfc.getUnibanco().getCedulaClienteLlamada();
	    int idProducto = mfc.getUnibanco().getIdProductoLlamada();
		String respuestaLlamada = comboRespuesta.getValue();
		String respuestaCliente = comboContestada.getValue();
		String fecha = LocalDateTime.now().toString();
		mfc.registrarLlamada(fecha, respuestaCliente, respuestaLlamada,cedula,idProducto);
		Stage stage = (Stage) finalizar.getScene().getWindow();
	  	stage.close();
    }
	
	@FXML
    void validarRespuesta() {
		if(comboContestada.getValue().equals("Si")) {
			comboRespuesta.setValue("Opcion");
			comboRespuesta.setDisable(false);
		}else {
			comboRespuesta.setDisable(true);
			comboRespuesta.setValue("No");
		}
    }
	
	@FXML
	void inicializarCombos() {
		comboRespuesta.setDisable(true);
		comboContestada.getItems().add("Si");
		comboContestada.getItems().add("No");
		comboRespuesta.getItems().add("Si");
		comboRespuesta.getItems().add("No");
	}
	
}
