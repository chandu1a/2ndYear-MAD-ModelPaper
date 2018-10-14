package com.example.chand.modelpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    private Button editBtn, deleteBtn, searchBtn;
    private EditText uName, pwd, dob;
    private RadioGroup genderGroup;
    private RadioButton selectedRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editBtn = findViewById(R.id.Edit_Profile_Edit_Button);
        deleteBtn = findViewById(R.id.Edit_Profile_Delete_Button);
        uName = findViewById(R.id.Edit_Profile_User_Name_EditText);
        pwd = findViewById(R.id.Edit_Profile_password_EditText);
        dob = findViewById(R.id.Edit_Profile_DOB_EditText);
        genderGroup = findViewById(R.id.Edit_Profile_radioGroup);
        searchBtn = findViewById(R.id.Edit_Profile_Search_Button);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioID = genderGroup.getCheckedRadioButtonId();
                UserBean userBean;
                List list = new ArrayList();
                DBHelper dbHelper = new DBHelper(EditProfile.this);
                list = dbHelper.readAllInfo(uName.getText().toString());
                if(list.size() == 0){
                    Toast toast = Toast.makeText(EditProfile.this, "User Not Found", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    userBean = (UserBean) list.get(0);
                    pwd.setText(userBean.getPassword());
                    dob.setText(userBean.getDOB());
                    String gend = userBean.getGender();
                    if (gend.equalsIgnoreCase("MALE")) {
                        genderGroup.check(R.id.ep_male_radio_btn);
                    } else {
                        genderGroup.check(R.id.ep_female_radio_btn);
                    }
                }

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioID = genderGroup.getCheckedRadioButtonId();
                selectedRadioBtn = findViewById(radioID);
                String gender = selectedRadioBtn.getText().toString();
                UserBean userBean;
                List list = new ArrayList();
                DBHelper dbHelper = new DBHelper(EditProfile.this);
                list = dbHelper.readAllInfo(uName.getText().toString());
                if(list.size() == 0){
                    Toast toast = Toast.makeText(EditProfile.this, "User Not Found", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    userBean = (UserBean) list.get(0);
                    boolean res = dbHelper.updateInfo(userBean.getID(), uName.getText().toString(), pwd.getText().toString(), dob.getText().toString(), gender);
                    if (res) {
                        Toast toast = Toast.makeText(EditProfile.this, "User Edited", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(EditProfile.this, "User Not Edited", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBean userBean;
                List list = new ArrayList();
                DBHelper dbHelper = new DBHelper(EditProfile.this);
                list = dbHelper.readAllInfo(uName.getText().toString());
                if(list.size() == 0){
                    pwd.setText("");
                    dob.setText("");
                    Toast toast = Toast.makeText(EditProfile.this, "User Not Found", Toast.LENGTH_SHORT);
                    toast.show();


                }
                else {
                    userBean = (UserBean) list.get(0);
                    boolean res = dbHelper.deleteInfo(userBean.getID());
                    pwd.setText("");
                    dob.setText("");
                    if (res) {
                        Toast toast = Toast.makeText(EditProfile.this, "User Deleted", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(EditProfile.this, "User Not Deleted", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });

    }
}
