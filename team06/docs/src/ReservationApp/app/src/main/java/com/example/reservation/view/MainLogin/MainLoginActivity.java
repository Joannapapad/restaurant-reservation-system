package com.example.reservation.view.MainLogin;
import com.example.reservation.view.StoreOwner.LogIn.StoreOwnerLogInActivity;
import com.example.reservation.view.StoreOwner.SignUp.StoreOwnerSignUpActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.reservation.R;
import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.view.Customer.SignUp.CustomerSignUpActivity;
import com.example.reservation.view.Customer.LogIn.CustomerLogInActivity;

/**
 * MainLoginActivity represents the main screen where the user can either log in or sign up
 * as a store owner or a customer. It manages navigation between different login and sign-up activities.
 * The activity uses the MVP architecture, with the presenter controlling the business logic.
 */
public class MainLoginActivity extends AppCompatActivity implements MainLoginView {
    private static boolean initialized = false;
    private MainLogInViewModel viewModel;
    private Button btnCustomerSignUp;
    private Button btnCustomerLogIn;
    private Button btnStoreOwnerSignUp;
    private Button btnStoreOwnerLogIn;

    /**
     * Called when the activity is first created. It sets up the layout, initializes the ViewModel,
     * binds the buttons with click listeners, and initializes the data if it hasn't been done before.
     *
     * @param savedInstanceState A bundle containing the activity's previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewModel = new ViewModelProvider(this).get(MainLogInViewModel.class);
        MainLoginPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        btnCustomerSignUp = findViewById(R.id.btnCustomerSignUp);
        btnCustomerLogIn = findViewById(R.id.btnCustomerLogIn);
        btnStoreOwnerSignUp = findViewById(R.id.btnStoreOwnerSignUp);
        btnStoreOwnerLogIn = findViewById(R.id.btnStoreOwnerLogIn);


        btnStoreOwnerLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageStoreOwnerLogin();
            }
        });

        btnStoreOwnerSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageStoreOwnerSignUp();
            }
        });

        btnCustomerLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageCustomerLogin();
            }
        });

        btnCustomerSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageCustomerSignUp();
            }
        });


        if (!initialized) {
            new MemoryInitializer().prepareData();
            initialized = true;
        }
    }

    /**
     * Navigates to the StoreOwnerLoginActivity when the store owner login button is clicked.
     */
    @Override
    public void ManageStoreOwnerLogin() {
        Intent intent = new Intent(MainLoginActivity.this, StoreOwnerLogInActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the StoreOwnerSignUpActivity when the store owner sign-up button is clicked.
     */
    @Override
    public void ManageStoreOwnerSignUp() {
        Intent intent = new Intent(MainLoginActivity.this, StoreOwnerSignUpActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the CustomerLoginActivity when the customer login button is clicked.
     */
    @Override
    public void ManageCustomerLogin() {
        Intent intent = new Intent(MainLoginActivity.this, CustomerLogInActivity.class);
        startActivity(intent);
    }
    /**
     * Navigates to the CustomerSignUpActivity when the customer sign-up button is clicked.
     */
    @Override
    public void ManageCustomerSignUp() {
        Intent intent = new Intent(MainLoginActivity.this, CustomerSignUpActivity.class);
        startActivity(intent);
    }
}
