package com.example.isabella.app_movel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Spinner tipo;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = (Button)findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        editEmail = (EditText)findViewById(R.id.email);
        editSenha = (EditText)findViewById(R.id.senha);
        tipo = (Spinner)findViewById(R.id.spinner_tipo);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    if(tipo.getSelectedItem().toString().equals("Professor"))
                        startActivity(new Intent(MainActivity.this, DashboardProfessorActivity.class));
                    else
                        startActivity(new Intent(MainActivity.this, DashboardAlunoActivity.class));
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        auth.addAuthStateListener(authListener);
    }

    private void login(){
        String email = editEmail.getText().toString(); //converte para string
        String senha = editSenha.getText().toString(); //converte para string

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)){
            Toast.makeText(MainActivity.this, "Os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
        } else {
            auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                        }
                    });


        }
    }
}
