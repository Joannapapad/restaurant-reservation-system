package com.example.reservation.MemoryDao;

import com.example.reservation.dao.ReservationDAO;
import com.example.reservation.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOMemory implements ReservationDAO {

    // Static list to act as the in-memory database for Reservation entities
    protected static List<Reservation> entities = new ArrayList<>();

    /**
     * Deletes a reservation from the in-memory list.
     *
     * @param entity The reservation to delete.
     */
    public void delete(Reservation entity) {
        entities.remove(entity);
    }

    /**
     * Finds all reservations in the in-memory list.
     *
     * @return A list of all reservations.
     */
    public List<Reservation> findAll() {
        return new ArrayList<>(entities);
    }

    /**
     * Saves a reservation to the in-memory list if it doesn't already exist.
     *
     * @param entity The reservation to save.
     */
    public void save(Reservation entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
        }
    }

    /**
     * Finds a reservation by its unique ID.
     *
     * @param reservationId The ID of the reservation to find.
     * @return The reservation with the given ID, or null if not found.
     */
    public Reservation find(int reservationId) {
        for (Reservation reservation : entities) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getReservationsByStoreId(int storeId) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : entities) {
            if (r.getStoreId() == storeId) {
                result.add(r);
            }
        }
        return result;
    }
}
