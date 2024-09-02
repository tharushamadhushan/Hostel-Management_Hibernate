

package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;
import lk.ijse.model.tm.ReservationTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblReservationId;

    @FXML
    private JFXComboBox<String> cmbStdId;

    @FXML
    private JFXComboBox<String> cmbRoomTypeId;

    @FXML
    private DatePicker date;

    @FXML
    private Label lblPricePerRoom;

    @FXML
    private Label lblBalance;

    @FXML
    private JFXTextField txtResId;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TableColumn<?, ?> clmResId;

    @FXML
    private TableColumn<?, ?> clmDate;

    @FXML
    private TableColumn<?, ?> clmStdId;

    @FXML
    private TableColumn<?, ?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private TableColumn<?, ?> clmStatus1;

    @FXML
    private JFXTextField txtPayAmount;

    ReservationBO reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.RESERVATION);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ObservableList<ReservationTM> observableList;

    private void loadRoomID() {
        List<String> id = reservationDAO.loadRoomID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        cmbRoomTypeId.setItems(obList);
    }

    private void loadStudentID() {
        List<String> id = reservationDAO.loadStudentID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        cmbStdId.setItems(obList);
    }

    private void loadNextResId(){
        String nextReservationId = reservationBO.getNextResId();
        lblReservationId.setText(nextReservationId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        lblReservationId.setText(null);
        date.setValue(null);
        cmbStdId.setValue(null);
        cmbRoomTypeId.setValue(null);
        txtResId.setText(null);
        lblBalance.setText(null);
        txtPayAmount.setText(null);
        lblPricePerRoom.setText(null);

    }
    @FXML
    void btnBookOnAction(ActionEvent event) {
        String id = lblReservationId.getText();
        LocalDate localDate = date.getValue();
        String sid = String.valueOf(cmbStdId.getValue());
        String rid = String.valueOf(cmbRoomTypeId.getValue());

        if (!rid.matches("^[H0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room ID format").show();
            return;
        }

        if (!sid.matches("^[S0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID format").show();
            return;
        }


        if (localDate == null) {
            new Alert(Alert.AlertType.ERROR, "Date of Reservation is required").show();
            return;
        }


        Double keyMoney;
        try {
            keyMoney = Double.valueOf(lblPricePerRoom.getText());
            if (keyMoney < 0) {
                new Alert(Alert.AlertType.ERROR, "Key money must be a positive number").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid key money format").show();
            return;
        }

        Double balance;
        try {
            balance = Double.valueOf(lblBalance.getText());
            if (balance < 0) {
                new Alert(Alert.AlertType.ERROR, "Balance must be a positive number").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid balance format").show();
            return;
        }

        RoomDTO roomDTO = new RoomDTO(rid);
        StudentDTO studentDTO = new StudentDTO(sid);

        boolean isReserved = reservationBO.addRevervation(new ReservationDTO(id, localDate, keyMoney, balance, roomDTO, studentDTO));

        if (isReserved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "saved !!!").show();
        }
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
        Double balanceText= Double.valueOf(lblBalance.getText());
        Double amountText= Double.valueOf(txtPayAmount.getText());

        Double newBalance = balanceText-amountText;

        boolean isUpdate = reservationBO.UpdatePayment(newBalance,txtResId.getText());

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtResId.getText();

        ReservationDTO reservation = reservationBO.search(id);
        System.out.println(reservation.getBalance()+"  "+reservation.getKeyMoney());

        if (reservation != null) {
            lblPricePerRoom.setText(String.valueOf(reservation.getKeyMoney()));
            lblBalance.setText(String.valueOf(reservation.getBalance()));
        }
    }

    @FXML
    void cmbRoomTypeOnAction(ActionEvent event) {
        Double keymoney = reservationBO.getKeymoney(cmbRoomTypeId.getSelectionModel().getSelectedItem());
        lblPricePerRoom.setText(String.valueOf(keymoney));
    }

    @FXML
    void cmbStdIdOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRoomID();
        loadNextResId();
        loadStudentID();
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        clmResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("rid"));
        clmStdId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        clmStatus1.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    private void getAll() {
        observableList = FXCollections.observableArrayList();
        List<ReservationDTO> allReservation = reservationBO.getAllRevervation();

        for (ReservationDTO reservationDTO : allReservation){
            observableList.add(new ReservationTM(
                    reservationDTO.getRes_id(),
                    reservationDTO.getDate(),
                    reservationDTO.getStudentDTO().getSId(),
                    reservationDTO.getRoomDTO().getRoom_type_id(),
                    reservationDTO.getKeyMoney(),
                    reservationDTO.getBalance()
            ));
        }
        tblReservation.setItems(observableList);
    }

    public void getBalance(ActionEvent actionEvent) {
        Double payment = Double.valueOf(txtPayAmount.getText());
        Double keymoney = Double.valueOf(lblPricePerRoom.getText());
        Double balance = keymoney - payment;

        lblBalance.setText(String.valueOf(balance));
    }


}



