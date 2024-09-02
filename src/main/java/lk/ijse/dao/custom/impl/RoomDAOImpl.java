package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    public Room search(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Room roomSearch=session.get(Room.class,id);
        transaction.commit();
        session.close();
        return roomSearch;
    }

    public boolean save(Room room) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.save(room);
        transaction.commit();
        session.close();
        return false;
    }

    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Room customerRemove=session.get(Room.class,id);
        session.remove(customerRemove);
        transaction.commit();
        session.close();
        return false;
    }

    public boolean Update(Room room) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.update(room);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public void updateRoomQut() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "UPDATE room SET quntity = (quntity - 1)";
        NativeQuery<Student> nativeQuery = session.createNativeQuery(sql);
        nativeQuery.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("FROM Room ");
        List<Room>rooms =q.list();
        transaction.commit();
        session.close();
        return rooms;
    }
}
