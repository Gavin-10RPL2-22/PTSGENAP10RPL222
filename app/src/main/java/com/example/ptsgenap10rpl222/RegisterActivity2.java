package com.example.ptsgenap10rpl222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.ptsgenap10rpl222.MainActivity.fromHtml;

public class RegisterActivity2 extends AppCompatActivity {

    EditText TxUsername, TxPassword, TxConPassword;
    Button BtnRegister;
    Helper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        dbHelper = new Helper(this);

        TxUsername = (EditText)findViewById(R.id.txUsernameReg);
        TxPassword = (EditText)findViewById(R.id.txPasswordReg);
        TxConPassword = (EditText)findViewById(R.id.txConPassword);
        BtnRegister = (Button)findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView)findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to " +
                "</font><font color='#3b5998'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity2.this, MainActivity.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String conPassword = TxConPassword.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(conPassword)){
                    Toast.makeText(RegisterActivity2.this, "Password tidak cocok", Toast.LENGTH_SHORT).show();
                }else if (password.equals("") || username.equals("")){
                    Toast.makeText(RegisterActivity2.this, "Username atau Password tidak bisa dikosongkan", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(Helper.rov_username, username);
                    values.put(Helper.rov_password, password);
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity2.this, "Register Sukses", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}