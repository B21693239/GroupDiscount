package kuneri.ersan.com.groupdiscount;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnloginguest;
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        userName=(EditText)findViewById(R.id.edtUsername);
        btnloginguest = findViewById(R.id.btnloginguest);
        password=(EditText)findViewById(R.id.edtPassword);
        btnloginguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainGuest();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate(userName.getText().toString(),password.getText().toString());

            }
        });
    }

    public void validate(String userName, String password)
    {
        if(userName.equals("user") && password.equals("user"))
            startMain("user");
        else
            Toast.makeText(this, "baş", Toast.LENGTH_LONG).show();
    }

    public void startMain(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
        finish();
    }
    public void startMainGuest() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}


