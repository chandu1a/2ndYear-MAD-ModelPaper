package com.example.chand.modelpaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {
    private Button updateBtn, registerBtn;
    private EditText uName, pwd, dob;
    private RadioGroup genderGroup;
    private RadioButton selectedRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        updateBtn = findViewById(R.id.profile_management_updateProfile_button);
        registerBtn = findViewById(R.id.profile_management_register_button);
        uName = findViewById(R.id.profile_management_userName_EditText);
        pwd = findViewById(R.id.profile_management_password_EditText);
        dob = findViewById(R.id.profile_management_DOB_EditText);
        genderGroup = findViewById(R.id.profile_management_radioGroup);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileManagement.this, EditProfile.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioID = genderGroup.getCheckedRadioButtonId();
                selectedRadioBtn = findViewById(radioID);
                String gender = selectedRadioBtn.getText().toString();

                DBHelper dbHelper = new DBHelper(ProfileManagement.this);
                boolean res = dbHelper.addInfo(uName.getText().toString(), pwd.getText().toString(), dob.getText().toString(), gender);
                if(res){
                    Toast toast = Toast.makeText(ProfileManagement.this, "User Added", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(ProfileManagement.this, "User Not Added", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });

    }
}
