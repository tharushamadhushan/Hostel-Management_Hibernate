package lk.ijse.dao;

import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO {

        public T search(String id) throws SQLException;

        public boolean save(T dto) throws SQLException;

        public boolean delete(String id) throws SQLException;

        public boolean Update(T dto) throws SQLException;

}
