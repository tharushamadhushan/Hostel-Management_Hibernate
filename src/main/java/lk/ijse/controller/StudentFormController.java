package lk.ijse.controller;


import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.StudentDTO;
import lk.ijse.model.tm.ReservationTM;
import lk.ijse.model.tm.StudentTM;
import lk.ijse.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    @FXML
    private JFXTextField studentid;

    @FXML
    private JFXTextField studentcontact;

    @FXML
    private JFXTextField studentaddress;

    @FXML
    private JFXTextField studentname;

    @FXML
    private DatePicker date;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> sid;

    @FXML
    private TableColumn<?, ?> sname;

    @FXML
    private TableColumn<?, ?> saddress;

    @FXML
    private TableColumn<?, ?> scontact;

    @FXML
    private TableColumn<?, ?> sdob;

    @FXML
    private TableColumn<?, ?> sgender;

    StudentBO studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.STUDENT);
    ObservableList<StudentTM> observableList;

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
        studentid.setText("");
        studentname.setText("");
        studentaddress.setText("");
        studentcontact.setText("");
        date.setValue(null);
        male.setSelected(false);
        female.setSelected(false);

    }
    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String studentIdInput = studentid.getText().trim();
        if (!studentIdInput.matches("^[S0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID format").show();
            return;
        }

        String name = studentname.getText().trim();
        if (name.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name cannot be empty").show();
            return;
        }


        String address = studentaddress.getText().trim();
        if (address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address cannot be empty").show();
            return;
        }


        String contact = studentcontact.getText().trim();
        if (contact.isEmpty() || !contact.matches("\\d{10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number").show();
            return;
        }

        LocalDate localDate = date.getValue();
        if (localDate == null) {
            new Alert(Alert.AlertType.ERROR, "Date of Birth is required").show();
            return;
        }

        Date DOB = java.sql.Date.valueOf(localDate);

        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        StudentDTO student = new StudentDTO(studentIdInput, name, address, contact, (java.sql.Date) DOB, gender);

        boolean isSave = false;
        try {
            isSave = studentBO.save(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Student not saved").show();
        }

        clearAll();

    }
    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String studentIdInput = studentid.getText().trim();
        if (!studentIdInput.matches("^[S0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID format").show();
            return;
        }

        String id = studentIdInput;

        boolean studentExists = false;
        try {
            studentExists = studentBO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (studentExists) {
            boolean isDelete = false;
            try {
                isDelete = studentBO.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not deleted").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Student not found").show();
        }

        clearAll();

    }
    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String studentIdInput = studentid.getText().trim();
        if (!studentIdInput.matches("^[S0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID format").show();
            return;
        }


        String name = studentname.getText().trim();
        if (name.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name cannot be empty").show();
            return;
        }


        String address = studentaddress.getText().trim();
        if (address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address cannot be empty").show();
            return;
        }


        String contact = studentcontact.getText().trim();
        if (contact.isEmpty() || !contact.matches("\\d{10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number").show();
            return;
        }

        LocalDate localDate = date.getValue();
        Date DOB = java.sql.Date.valueOf(localDate);

        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        StudentDTO student = new StudentDTO(studentIdInput, name, address, contact, (java.sql.Date) DOB, gender);

        boolean isUpdate = false;
        try {
            isUpdate = studentBO.Update(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update successful").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update successful").show();
        }

        clearAll();
    }
    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String studentIdInput = studentid.getText().trim();
        if (!studentIdInput.matches("^[S0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID format").show();
            return;
        }

        String id = studentIdInput;

        StudentDTO student = null;
        try {
            student = studentBO.search(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (student != null) {
            studentid.setText(student.getSId());
            studentname.setText(student.getSName());
            studentaddress.setText(student.getSAddress());
            studentcontact.setText(student.getSContact());
            date.setValue(student.getDate().toLocalDate());
            String gender = student.getGender();

            if ("Male".equals(gender)) {
                male.setSelected(true);
            } else if ("Female".equals(gender)) {
                female.setSelected(true);
            }
            boolean isSave = false;
            try {
                isSave = studentBO.Update(student);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Search successful").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Search successful").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Student not found").show();
        }
    }

    private void clearAll() {
        studentid.setText("");
        studentname.setText("");
        studentaddress.setText("");
        studentcontact.setText("");
        date.setValue(null);
        male.setSelected(false);
        female.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        sid.setCellValueFactory(new PropertyValueFactory<>("sId"));
        sname.setCellValueFactory(new PropertyValueFactory<>("sName"));
        saddress.setCellValueFactory(new PropertyValueFactory<>("sAddress"));
        scontact.setCellValueFactory(new PropertyValueFactory<>("sContact"));
        sdob.setCellValueFactory(new PropertyValueFactory<>("date"));
        sgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void getAll() {
        observableList = FXCollections.observableArrayList();
        List<StudentDTO> allStudent = studentBO.getAllStudent();

        for (StudentDTO studentDTO : allStudent){
            observableList.add(new StudentTM(
                    studentDTO.getSId(),
                    studentDTO.getSName(),
                    studentDTO.getSAddress(),
                    studentDTO.getSContact(),
                    studentDTO.getDate(),
                    studentDTO.getGender()
            ));
        }
        tblStudent.setItems(observableList);
    }
}
