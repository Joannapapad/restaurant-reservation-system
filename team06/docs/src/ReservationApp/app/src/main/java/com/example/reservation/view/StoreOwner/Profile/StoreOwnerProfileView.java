package com.example.reservation.view.StoreOwner.Profile;

public interface StoreOwnerProfileView {


     String getName();
     void setName(String value);
     void setEmail(String value);
     void setTel(String value);
     void setAfm(String value);
     void setPassword(String value);
     void startEdit(String name);
     void setId(String value);
     void showToast(String value);
     void startEditActivity(int ownerId, String userName, String email, String afm, String phone, String password);
     void startDelete(String title, String message);
     void doDeleteAndFinish(String message);



}
