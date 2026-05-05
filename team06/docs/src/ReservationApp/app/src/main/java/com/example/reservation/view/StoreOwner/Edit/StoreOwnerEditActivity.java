package com.example.reservation.view.StoreOwner.Edit;

import static androidx.compose.ui.semantics.SemanticsPropertiesKt.setText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;

public class StoreOwnerEditActivity extends AppCompatActivity implements StoreOwnerEditView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_edit);
        final StoreOwnerEditPresenter presenter = new StoreOwnerEditPresenter(this, CurrentUserDAOMemory.getInstance());

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("owner_name");
            String email = intent.getStringExtra("owner_email");
            String afm = intent.getStringExtra("owner_afm");
            String phone = intent.getStringExtra("owner_tel");
            String password = intent.getStringExtra("owner_password");

            // Set the retrieved values to the UI
            ((EditText) findViewById(R.id.edtEditName)).setText(name);
            ((EditText) findViewById(R.id.edtEditEmail)).setText(email);
            ((EditText) findViewById(R.id.edtEditAfm)).setText(afm);
            ((EditText) findViewById(R.id.edtEditTel)).setText(phone);
            ((EditText) findViewById(R.id.edtEditPassword)).setText(password);
        }

        findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSaveBorrower();
            }
        });

    }

    @Override
    public int getId() {
        return this.getIntent().hasExtra("owner_id") ? this.getIntent().getExtras().getInt("owner_id") : null;
    }

    @Override
    public String getName() {
        return ((EditText)findViewById(R.id.edtEditName)).getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return ((EditText)findViewById(R.id.edtEditTel)).getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return ((EditText)findViewById(R.id.edtEditEmail)).getText().toString().trim();

    }

    @Override
    public String getAfm() {
        return ((EditText)findViewById(R.id.edtEditAfm)).getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return ((EditText)findViewById(R.id.edtEditPassword)).getText().toString().trim();
    }


    @Override
    public void setName(String value) {
        ((EditText)findViewById(R.id.edtEditName)).setText(value);

    }

    @Override
    public void setTel(String value) {
        ((EditText)findViewById(R.id.edtEditTel)).setText(value);
    }

    @Override
    public void setEmail(String value) {
        ((EditText)findViewById(R.id.edtEditEmail)).setText(value);
    }

    @Override
    public void setAfm(String value) {
        ((EditText)findViewById(R.id.edtEditAfm)).setText(value);
    }

    @Override
    public void setPassword(String value) {
        ((EditText)findViewById(R.id.edtEditPassword)).setText(value);
    }

    @Override
    public void successfullyFinishActivity(String message) {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}