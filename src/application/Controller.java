package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Controller implements Initializable {

	private double dx;
    private double dy;

    @FXML
    private JFXButton buttonUsers, buttonNewUser, buttonSettings;
	@FXML
    private FontAwesomeIconView buttonClose, buttonMax, buttonMin;
	@FXML
	private AnchorPane mainPane, nuevoRegistro, registros;

	@FXML
	private JFXDatePicker datePickerFechaIngreso;

	@FXML
	private JFXTextField textFieldFecha;

	@FXML
	private JFXComboBox<String> comboboxCargo;

	private Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//JFXDatePickerSkin skin = new JFXDatePickerSkin(datePickerFechaIngreso);
		//System.out.println(datePickerFechaIngreso.getSkin().toString());
		datePickerFechaIngreso.setValue(LocalDate.now());
		textFieldFecha.textProperty().bind(datePickerFechaIngreso.valueProperty().asString());
		datePickerFechaIngreso.show();
		datePickerFechaIngreso.hide();
		usersClick(new ActionEvent());


		comboboxCargo.getItems().add("Operador");
		comboboxCargo.getItems().add("Ingeniero");
		comboboxCargo.getItems().add("Secretaria");
		comboboxCargo.getItems().add("Sena");
		//comboboxCargo.la
	}

	@FXML
	public void windowButtonClick(MouseEvent ev){
		String id = ((FontAwesomeIconView)ev.getSource()).getId();
		Stage stage = (Stage)((FontAwesomeIconView)ev.getSource()).getScene().getWindow();
		if(id.equals(buttonClose.getId())){
			stage.close();
			System.exit(0);
		}else{

			if(id.equals(buttonMin.getId())){
				stage.setIconified(true);
			}else
				if(!stage.isMaximized()){
					stage.setX(bounds.getMinX());
					stage.setY(bounds.getMinY());
				    stage.setWidth(bounds.getWidth());
				    stage.setHeight(bounds.getHeight());
				    stage.setMaximized(true);
				}else{
					stage.setMaximized(false);
				}
		}
	}

	@FXML
	public void openDatePicker(MouseEvent ev){
		datePickerFechaIngreso.show();
		datePickerFechaIngreso.requestFocus();
	}

	@FXML
	public void newUserClick(ActionEvent ev){
		buttonNewUser.setStyle("-fx-background-color: #404040");
		buttonUsers.setStyle("");
		buttonSettings.setStyle("");
		nuevoRegistro.setVisible(true);
		registros.setVisible(false);
		System.out.println("Time: "+datePickerFechaIngreso.getValue());
	}

	@FXML
	public void usersClick(ActionEvent ev){
		buttonNewUser.setStyle("");
		buttonUsers.setStyle("-fx-background-color: #404040");
		buttonSettings.setStyle("");
		registros.setVisible(true);
		nuevoRegistro.setVisible(false);
	}

	@FXML
	public void settingsClick(ActionEvent ev){
		buttonNewUser.setStyle("");
		buttonUsers.setStyle("");
		buttonSettings.setStyle("-fx-background-color: #404040");
	}

	@FXML
	public void resizeStarted(MouseEvent ev){
		dx = ((Stage)mainPane.getScene().getWindow()).getWidth() - ev.getSceneX();
        dy = ((Stage)mainPane.getScene().getWindow()).getHeight() - ev.getSceneY();
        ev.consume();
	}

	@FXML
	public void resizing(MouseEvent ev){

		if(ev.getSceneX()+dx>mainPane.getMinWidth())
			((Stage)mainPane.getScene().getWindow()).setWidth(ev.getSceneX()+dx);
		if(ev.getSceneY()+dy>mainPane.getMinHeight())
			((Stage)mainPane.getScene().getWindow()).setHeight(ev.getSceneY()+dy);
		ev.consume();
	}



}
