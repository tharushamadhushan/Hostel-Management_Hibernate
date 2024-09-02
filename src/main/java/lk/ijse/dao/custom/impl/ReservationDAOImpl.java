package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.entity.Reservation;
import lk.ijse.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    public boolean save(Reservation reservation) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.save(reservation);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Reservation search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Reservation reservationSearch=session.get(Reservation.class,id);
        System.out.println(reservationSearch.getBalance()+" "+reservationSearch.getKeyMoney());
        transaction.commit();
        session.close();
        return reservationSearch;
    }

    @Override
    public boolean Update(Reservation reservation) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Reservation> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from Reservation ");
        List<Reservation>reservation =q.list();
        transaction.commit();
        session.close();
        return reservation;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        Reservation remove=session.get(Reservation.class,id);
        session.remove(remove);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Double getkeymoney(String selectedItem) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("SELECT key_money FROM Room WHERE room_type_id = ?1");
        query.setParameter(1,selectedItem);
        Double key= (Double) query.uniqueResult();
        System.out.println(key);
        transaction.commit();
        session.close();
        return key;
    }

    @Override
    public boolean UpdatePayment(Double newBalance, String text) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("UPDATE Reservation set balance = ?1 where res_id= ?2");
        query.setParameter(1,newBalance);
        query.setParameter(2,text);
        int i = query.executeUpdate();
        transaction.commit();
        session.close();
        if (i>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public String getNextId() {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC LIMIT 1");
        String currentId = String.valueOf(query.uniqueResult());
        transaction.commit();
        session.close();
        return nextId(currentId);
    }

    @Override
    public List<String> loadStudentID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT sId FROM Student");
        List<String> studentIds = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return studentIds;
    }

    @Override
    public List<String> loadRoomID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT room_type_id FROM Room");
        List<String> roomIds = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return roomIds;
    }

    private String nextId(String currentId) {
        if (currentId != null){
            String[] strings = currentId.split("R");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "R"+id;
        }
        return"O001";
    }


}
