package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;
import lk.ijse.model.tm.ReservationTM;
import lk.ijse.model.tm.RoomTM;
import lk.ijse.model.tm.StudentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField roomid;

    @FXML
    private JFXTextField roomqty;

    @FXML
    private JFXTextField roomkeymoney;

    @FXML
    private JFXTextField roomtype;

    @FXML
    private TableView<RoomTM> tblRoom;

    @FXML
    private TableColumn<?, ?> rid;

    @FXML
    private TableColumn<?, ?> rtype;

    @FXML
    private TableColumn<?, ?> rkeymoney;

    @FXML
    private TableColumn<?, ?> rqty;

    RoomBO roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ROOM);
    ObservableList<RoomTM> observableList;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String roomIdInput = roomid.getText().trim();
        if (!roomIdInput.matches("^[H0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room ID format").show();
            return;
        }

        String id = roomIdInput;

        boolean roomExists = false;
        try {
            roomExists = roomBO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (roomExists) {
            boolean isDelete = false;
            try {
                isDelete = roomBO.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room deleted successfully").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Room not found").show();
        }
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String roomIdInput = roomid.getText().trim();
        if (!roomIdInput.matches("^[H0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room ID format").show();
            return;
        }

        String id = roomIdInput;
        String type = roomtype.getText().trim();
        String keyMoneyInput = roomkeymoney.getText().trim();
        String qtyInput = roomqty.getText().trim();


        if (type.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Room type cannot be empty").show();
            return;
        }


        Double keyMoney;
        try {
            keyMoney = Double.parseDouble(keyMoneyInput);
            if (keyMoney < 0) {
                new Alert(Alert.AlertType.ERROR, "Key money must be a positive number").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid key money format").show();
            return;
        }


        Integer qty;
        try {
            qty = Integer.parseInt(qtyInput);
            if (qty < 1) {
                new Alert(Alert.AlertType.ERROR, "Quantity must be a positive integer").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity format").show();
            return;
        }
        RoomDTO room = new RoomDTO(id, type, keyMoney, qty);

        boolean isSave = false;
        try {
            isSave = roomBO.save(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "Room saved successfully").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Room saved successfully").show();
        }

        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String roomIdInput = roomid.getText().trim();
        if (!roomIdInput.matches("^[H0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room ID format").show();
            return;
        }

        String id = roomIdInput;

        RoomDTO room = null;
        try {
            room = roomBO.search(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (room != null) {
            roomid.setText(room.getRoom_type_id());
            roomtype.setText(room.getType());
            roomkeymoney.setText(String.valueOf(room.getKey_money()));
            roomqty.setText(String.valueOf(room.getQty()));
        } else {
            new Alert(Alert.AlertType.ERROR, "Room  found").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String roomIdInput = roomid.getText().trim();
        if (!roomIdInput.matches("^[H0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room ID format").show();
            return;
        }

        String id = roomIdInput;
        String type = roomtype.getText().trim();
        String keyMoneyInput = roomkeymoney.getText().trim();
        String qtyInput = roomqty.getText().trim();


        if (type.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Room type cannot be empty").show();
            return;
        }


        Double keyMoney;
        try {
            keyMoney = Double.parseDouble(keyMoneyInput);
            if (keyMoney < 0) {
                new Alert(Alert.AlertType.ERROR, "Key money must be a positive number").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid key money format").show();
            return;
        }


        Integer qty;
        try {
            qty = Integer.parseInt(qtyInput);
            if (qty < 1) {
                new Alert(Alert.AlertType.CONFIRMATION, "Quantity must be a positive integer").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity format").show();
            return;
        }

        RoomDTO room = new RoomDTO(id, type, keyMoney, qty);
        boolean isUpdate = false;
        try {
            isUpdate = roomBO.Update(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Room updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Room updated successfully").show();
        }
        roomid.setText("");
        roomtype.setText("");
        roomkeymoney.setText("");
        roomqty.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        rid.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        rtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        rkeymoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        rqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void getAll() {
        observableList = FXCollections.observableArrayList();
        List<RoomDTO> allRoom = roomBO.getAllStudent();

        for (RoomDTO roomDTO : allRoom){
            observableList.add(new RoomTM(
                    roomDTO.getRoom_type_id(),
                    roomDTO.getType(),
                    roomDTO.getKey_money(),
                    roomDTO.getQty()
            ));
        }
        tblRoom.setItems(observableList);
    }

}
