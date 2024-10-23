package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnXacNhan;
    EditText edtName, edtClass, edtLHP;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sharedPreferences = getSharedPreferences("dataSave", MODE_PRIVATE);

        // Lấy giá trị từ SharedPreferences
        edtName.setText(sharedPreferences.getString("ten", ""));
        edtClass.setText(sharedPreferences.getString("lop", ""));
        edtLHP.setText(sharedPreferences.getString("lhp", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String className = edtClass.getText().toString().trim();
                String lhp = edtLHP.getText().toString().trim();

                if (name.equals("Nguyễn Văn Viên") && className.equals("22T1") && lhp.equals("124LTTD03")) {
                    Toast.makeText(MainActivity.this, "Lưu thông tin sinh viên thành công.", Toast.LENGTH_SHORT).show();

                    // Nếu checkbox được chọn
                    if (cbRemember.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ten", name);
                        editor.putString("lop", className);
                        editor.putString("lhp", lhp);
                        editor.putBoolean("checked", true); // Ensure to save the checkbox state
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("ten");
                        editor.remove("lop"); // Changed 'matkhau' to 'lop'
                        editor.remove("lhp");
                        editor.remove("checked");
                        editor.commit();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Không lưu được thông tin sinh viên!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
        edtName = (EditText) findViewById(R.id.editTextName);
        edtClass = (EditText) findViewById(R.id.editTextClass);
        edtLHP = (EditText) findViewById(R.id.editTextLHP);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
    }
}
