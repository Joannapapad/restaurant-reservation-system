package com.example.reservation.view.Customer.LogIn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.reservation.R;
import com.example.reservation.view.Customer.MainMenu.MainMenuActivity;
import com.example.reservation.view.Customer.SignUp.CustomerSignUpActivity;


public class CustomerLogInActivity extends AppCompatActivity implements CustomerLoginView{

    private CustomerLogInViewModel viewModel;
    private TextView txtLogInC;
    private EditText EdtUsernameLogIn;
    private EditText edtPasswordLogIn;
    private Button btnFinishLogInC;
    private Button btnSignUpC;
    private ImageButton btnBackLogInC;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_in);
        viewModel = new ViewModelProvider(this).get(CustomerLogInViewModel.class);
        CustomerLogInPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        EdtUsernameLogIn = findViewById(R.id.edtUsernameLogInC);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogInC);
        btnFinishLogInC = findViewById(R.id.btnFinishLogInC);
        btnSignUpC = findViewById(R.id.btnSignUpC);
        btnBackLogInC = (android.widget.ImageButton)findViewById(R.id.btnBackLogInC);

        btnFinishLogInC.setOnClickListener(v -> {String username = EdtUsernameLogIn.getText().toString();
            String password = edtPasswordLogIn.getText().toString();

            presenter.LogIn(username, password);

        });

        btnBackLogInC.setOnClickListener(v -> finish());
        btnSignUpC.setOnClickListener(v -> {
            Intent intent = new Intent(CustomerLogInActivity.this, CustomerSignUpActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void setUsername(String username) {
        ((EditText)findViewById(R.id.edtUsernameLogInC)).setText(username);

    }


    @Override
    public void setPassword(String password) {
        ((EditText)findViewById(R.id.EdtPasswordLogIn)).setText(password);
    }

    @Override
    public void succesfullyLogedIn(String message) {
        Toast.makeText(CustomerLogInActivity.this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CustomerLogInActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}