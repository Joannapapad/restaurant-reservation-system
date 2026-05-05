package com.example.reservation.MemoryDao;

import com.example.reservation.dao.RequestDAO;
import com.example.reservation.domain.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestDAOMemory implements RequestDAO {

    // Static list to act as the in-memory database for Request entities
    protected static List<Request> entities = new ArrayList<>();

    /**
     * Deletes a request from the in-memory list.
     *
     * @param entity The request to delete.
     */
    public void delete(Request entity) {
        entities.remove(entity);
    }

    /**
     * Finds all requests in the in-memory list.
     *
     * @return A list of all requests.
     */
    public List<Request> findAll() {
        return new ArrayList<>(entities);
    }

    /**
     * Saves a request to the in-memory list if it doesn't already exist.
     *
     * @param entity The request to save.
     */
    public void save(Request entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
        }
    }

    /**
     * Finds a request by its unique ID.
     *
     * @param requestId The ID of the request to find.
     * @return The request with the given ID, or null if not found.
     */
    public Request find(int requestId) {
        for (Request request : entities) {
            if (request.getReservationID() == requestId) {
                return request;
            }
        }
        return null;
    }
    @Override
    public List<Request> getRequestsByStoreId(int storeId) {
        List<Request> result = new ArrayList<>();
        for (Request r : entities) {
            if (r.getStoreID() == storeId) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public List<Request> getRequestsByCustomerId(int customerid) {
        return Collections.emptyList();
    }
}
