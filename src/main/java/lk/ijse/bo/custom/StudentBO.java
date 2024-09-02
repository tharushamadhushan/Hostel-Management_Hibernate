package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBo {

    boolean delete(String id) throws SQLException;

    boolean save(StudentDTO dto) throws SQLException;

    StudentDTO search(String id) throws SQLException;

    boolean Update(StudentDTO dto) throws SQLException;

    List<StudentDTO> getAllStudent();
}
