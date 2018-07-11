package com.example.isabella.app_movel;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardAlunoActivity extends AppCompatActivity implements AlunosListFragment.onAlunoSelectedListener {
    Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_info:
                    setTitle("Informações aluno");
                    InfoAlunoFragment fragmentInfoAluno = new InfoAlunoFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, fragmentInfoAluno, "FragmentInfoAluno");
                    fragmentInfoAluno.setKeyAluno(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    fragmentTransaction1.commit();
                    return true;
                case R.id.nav_add_info:
                    setTitle("Informações adicionais");
                    AddInfoFragment fragmentAdd = new AddInfoFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame, fragmentAdd, "fragmentAdd");
                    fragmentAdd.setKeyAluno(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    fragmentTransaction2.commit();
                    return true;
                case R.id.nav_mensagens:
                    return true;
            }
            return false;


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_aluno);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("Informações aluno");
        InfoAlunoFragment fragmentInfoAluno = new InfoAlunoFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frame, fragmentInfoAluno, "FragmentInfoAluno");
        fragmentInfoAluno.setKeyAluno(FirebaseAuth.getInstance().getCurrentUser().getUid());
        fragmentTransaction1.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_aluno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DashboardAlunoActivity.this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAlunoSelected(String key) {
        setTitle("Informações aluno");
        InfoAlunoFragment infoAlunoFragment = new InfoAlunoFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, infoAlunoFragment, "infoAluno");
        fragmentTransaction.addToBackStack(null);
        infoAlunoFragment.setKeyAluno(key);
        fragmentTransaction.commit();
    }
}
