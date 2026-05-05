package com.example.reservation.view.StoreOwner.Profile;

import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.domain.StoreOwner;

public class StoreOwnerProfilePresenter {

    private StoreOwnerProfileView view;
    private CurrentUserDAO currentUserDAO;
    private StoreOwner storeOwner;

    public void setView(StoreOwnerProfileView view) {
        this.view = view;
    }

    public StoreOwnerProfileView getView() {
        return view;
    }

    // Constructor for initializing presenter
    public StoreOwnerProfilePresenter(StoreOwnerProfileView view, CurrentUserDAO currentUserDAO) {
        this.view = view;
        this.currentUserDAO = currentUserDAO;

        // Retrieve the currently logged-in user (which is a StoreOwner in this case)
        storeOwner = (StoreOwner) currentUserDAO.retrieve();  // Cast it to StoreOwner

        // If storeOwner is not null, set the profile data
        if (storeOwner != null) {
            view.setId(String.valueOf(storeOwner.getOwnerId()));  // Assuming getId() returns an int
            view.setName(storeOwner.getUserName());
            view.setEmail(storeOwner.getEmail().getAddress());  // Assuming Email is a complex object
            view.setTel(storeOwner.getNumber().getTelephoneNumber());  // Assuming Phone number is a complex object
            view.setAfm(storeOwner.getAFM());
            view.setPassword(storeOwner.getPassword());
        }
    }

    public void onStartEditButtonClick()
    {
        view.startEditActivity(storeOwner.getOwnerId(),
                storeOwner.getUserName(),
                storeOwner.getEmail().getAddress(),
                storeOwner.getAFM(),
                storeOwner.getNumber().getTelephoneNumber(),
                storeOwner.getPassword());
    }

    public void onStartDeleteButtonClick()
    {
        view.startDelete("Delete Store Owner?", "Your account will delete for ever!!");
    }

    public void onDoDeleteAndFinish()
    {
        String msg = "Successful delete '"+storeOwner.getUserName()+"'!";

        currentUserDAO.delete(storeOwner);
        storeOwner = null;

        view.doDeleteAndFinish(msg);
    }

    public void onShowToast(String value)
    {
        view.showToast(value);
    }
}
