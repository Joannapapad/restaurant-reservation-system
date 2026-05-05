package com.example.reservation.view.StoreOwner.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.reservation.R;
import com.example.reservation.view.StoreOwner.MainMenu.MainMenuActivity;
import com.example.reservation.view.StoreOwner.SignUp.StoreOwnerSignUpActivity;

/**
 * Activity for the StoreOwner Log In screen.
 * This activity allows store owners to log into the system and navigate to the main menu.
 * It also provides an option for new store owners to sign up.
 */
public class StoreOwnerLogInActivity extends AppCompatActivity implements StoreOwnerLogInView {

    private StoreOwnerLogInViewModel viewModel;
    private TextView txtSignUp;
    private EditText EdtNameLogIn;
    private EditText EdtPasswordLogIn;
    private Button btnFinishLogIn;
    private Button btnSignUp;
    private ImageButton btnBackLogIn;
    //private CheckBox btmRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_log_in);
        viewModel = new ViewModelProvider(this).get(StoreOwnerLogInViewModel.class);
        StoreOwnerLogInPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        EdtNameLogIn = findViewById(R.id.EdtNameLogIn);
        EdtPasswordLogIn = findViewById(R.id.EdtPasswordLogIn);
        btnFinishLogIn = findViewById(R.id.btnFinishLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnBackLogIn = (android.widget.ImageButton)findViewById(R.id.btnBackLogIn);

        btnFinishLogIn.setOnClickListener(v -> {String username = EdtNameLogIn.getText().toString();
            String password = EdtPasswordLogIn.getText().toString();

            presenter.LogIn(username, password);

        });

        btnBackLogIn.setOnClickListener(v -> finish());
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(StoreOwnerLogInActivity.this, StoreOwnerSignUpActivity.class);
            startActivity(intent);
        });



    }

    /**
     * Sets the username in the login form.
     *
     * @param name The username to be set in the EditText.
     */
    @Override
    public void setUserName(String name) {
        ((EditText)findViewById(R.id.EdtNameLogIn)).setText(name);
    }


    /**
     * Sets the password in the login form.
     *
     * @param password The password to be set in the EditText.
     */

    @Override
    public void setPassword(String password) {
        ((EditText)findViewById(R.id.EdtPasswordLogIn)).setText(password);
    }

    /**
     * Shows a successful login message and navigates to the main menu.
     *
     * @param message The success message to be displayed as a Toast.
     */
    @Override
    public void succesfullyLogedIn(String message) {
        Toast.makeText(StoreOwnerLogInActivity.this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StoreOwnerLogInActivity.this, MainMenuActivity.class);
        startActivity(intent);

    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to be displayed as a Toast.
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}