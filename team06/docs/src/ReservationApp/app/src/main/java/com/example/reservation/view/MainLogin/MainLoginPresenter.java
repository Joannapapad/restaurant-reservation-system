package com.example.reservation.view.MainLogin;

/**
 * Presenter class responsible for handling the logic behind the MainLogin screen.
 * This class interacts with the `MainLoginView` to manage user actions such as navigating
 * to the login or sign-up pages for store owners and customers.
 */
public class MainLoginPresenter
{
    private MainLoginView view;

    /**
     * Initializes the presenter by setting the view.
     *
     * @param view An instance of the `MainLoginView` interface that the presenter will interact with.
     */

    public void setView (MainLoginView view) {this.view = view;}

    /**
     * This method is called when the user clicks on the Store Owner Login button.
     * It instructs the view to navigate the user to the `StoreOwnerLoginActivity`.
     */
    public void onManageStoreOwnerLogin()
    {
        view.ManageStoreOwnerLogin();
    }
    /**
     * This method is called when the user clicks on the Store Owner Sign-Up button.
     * It instructs the view to navigate the user to the `StoreOwnerSignUpActivity`.
     */
    public void onManageStoreOwnerSignUp()
    {
        view.ManageStoreOwnerSignUp();
    }


    /**
     * This method is called when the user clicks on the Customer Login button.
     * It instructs the view to navigate the user to the `CustomerLoginActivity`.
     */
    public void onManageCustomerLogin()
    {
        view.ManageCustomerLogin();
    }

    /**
     * This method is called when the user clicks on the Customer Sign-Up button.
     * It instructs the view to navigate the user to the `CustomerSignUpActivity`.
     */
    public void onManageCustomerSignUp()
    {
        view.ManageCustomerSignUp();
    }


}
