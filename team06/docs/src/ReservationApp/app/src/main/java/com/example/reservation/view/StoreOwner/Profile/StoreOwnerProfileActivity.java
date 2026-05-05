package com.example.reservation.view.StoreOwner.Profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;
import com.example.reservation.view.MainLogin.MainLoginActivity;
import com.example.reservation.view.StoreOwner.MainMenu.MainMenuActivity;
import com.example.reservation.view.StoreOwner.Requests.RequestsActivity;
import com.example.reservation.view.StoreOwner.Edit.StoreOwnerEditActivity;

public class StoreOwnerProfileActivity extends AppCompatActivity implements StoreOwnerProfileView {

    private StoreOwnerProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_profile);
        presenter = new StoreOwnerProfilePresenter(this, CurrentUserDAOMemory.getInstance());

        // Find the buttons in the bottom menu
        ImageButton bottomProfile = findViewById(R.id.bottom_profile);
        ImageButton bottomHome = findViewById(R.id.bottom_home);
        ImageButton bottomRequests = findViewById(R.id.bottom_requests);

        // Set click listeners for each button
        bottomProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleProfileClick();
            }
        });

        bottomHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleHomeClick();
            }
        });

        bottomRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRequestsClick();
            }
        });

        findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartEditButtonClick();
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                presenter.onStartDeleteButtonClick();
            }
        });



    }

    @Override
    public String getName() {
        return this.getIntent().hasExtra("owner_name")
                ? this.getIntent().getStringExtra("owner_name")
                : null; // Return null if the extra doesn't exis
    }


    @Override
    public void setName(String value) {
        ((TextView) findViewById(R.id.txtStoreOwnerNameProfileBlank)).setText(value);
    }

    @Override
    public void setEmail(String value) {
        ((TextView) findViewById(R.id.txtStoreOwnerProfileBlank)).setText(value);
    }

    @Override
    public void setTel(String value) {
        ((TextView) findViewById(R.id.txtStoreOwnerProfilePhoneBlank)).setText(value);
    }

    @Override
    public void setAfm(String value) {
        ((TextView) findViewById(R.id.txtStoreOwnerProfileAfmBlank)).setText(value);
    }

    @Override
    public void setPassword(String value) {
        ((TextView) findViewById(R.id.txtStoreOwnerProfilePasswordBlank)).setText(value);
    }

    @Override
    public void startEdit(String name) {
            Intent intent = new Intent(this, StoreOwnerEditActivity.class);
            intent.putExtra("owner_name", name);
            startActivity(intent);
    }

    @Override
    public void setId(String value) {
        ((TextView) findViewById(R.id.txtStoreOwnerIdBlank)).setText(value);

    }

    @Override
    public void showToast(String value) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startEditActivity(int ownerId, String userName, String email, String afm, String phone, String password) {
        Intent intent = new Intent(this, StoreOwnerEditActivity.class);
        intent.putExtra("owner_id", ownerId);
        intent.putExtra("owner_name", userName);
        intent.putExtra("owner_email", email);
        intent.putExtra("owner_afm", afm);
        intent.putExtra("owner_tel", phone);
        intent.putExtra("owner_password", password);
        startActivityForResult(intent, 2);
    }

    private void handleProfileClick() {
        // Navigate to Profile Activity
        startActivity(new Intent(StoreOwnerProfileActivity.this, StoreOwnerProfileActivity.class));
    }

    private void handleHomeClick() {
        // Home Action (Reload the same page)
        startActivity(new Intent(StoreOwnerProfileActivity.this, MainMenuActivity.class));
    }

    private void handleRequestsClick() {
        // Navigate to Requests Activity
        startActivity(new Intent(StoreOwnerProfileActivity.this, RequestsActivity.class));
    }
    public void startDelete(String title, String message)
    {
        new AlertDialog.Builder(StoreOwnerProfileActivity.this).setCancelable(true).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.yes_delete, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        presenter.onDoDeleteAndFinish();
                    }
                })
                .setNegativeButton(R.string.cancel, null).create().show();
    }

    public void doDeleteAndFinish(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(StoreOwnerProfileActivity.this, MainLoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == Activity.RESULT_OK)
        {
            recreate();
            presenter.onShowToast(data.getStringExtra("message_to_toast"));
        }
        else if(requestCode == 100)
            recreate();
    }

}
