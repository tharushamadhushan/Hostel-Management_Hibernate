package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Student;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean delete(String id) throws SQLException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean save(StudentDTO dto) throws SQLException {
        return studentDAO.save(new Student(dto.getSId(),dto.getSName(),dto.getSAddress(),dto.getSContact(),dto.getDate(),dto.getGender()));
    }

    @Override
    public StudentDTO search(String id) throws SQLException {
        Student student=studentDAO.search(id);
        return new StudentDTO(student.getSId(),student.getSName(),student.getSAddress(),student.getSContact(),student.getDate(),student.getGender());
    }

    @Override
    public boolean Update(StudentDTO dto) throws SQLException {
        return studentDAO.Update(new Student(dto.getSId(),dto.getSName(),dto.getSAddress(),dto.getSContact(),dto.getDate(),dto.getGender()));
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> allStudent= new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getSId(),student.getSName(),student.getSAddress(),student.getSContact(),student.getDate(),student.getGender()));
        }
        return allStudent;
    }
}
