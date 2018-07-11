package com.example.isabella.app_movel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardProfessorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TurmasListFragment.onTurmaSelectedListener,
        AlunosListFragment.onAlunoSelectedListener{

    FirebaseUser user;
    TextView textNome;
    TextView textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_professor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(Integer cod) {
        setTitle("Lista alunos turma " + cod + "");
        AlunosListFragment alunosListFragment = new AlunosListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameDashProfessor, alunosListFragment, "AlunosListFragment");
        fragmentTransaction.addToBackStack(null);
        alunosListFragment.setCodTurma(cod);
        fragmentTransaction.commit();
    }


    @Override
    public void onAlunoSelected(String key) {
        setTitle("Informações aluno");
        InfoAlunoFragment infoAlunoFragment = new InfoAlunoFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameDashProfessor, infoAlunoFragment, "infoAluno");
        fragmentTransaction.addToBackStack(null);
        infoAlunoFragment.setKeyAluno(key);
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle("Lista turmas");
        TurmasListFragment turmasListFragment = new TurmasListFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frameDashProfessor, turmasListFragment, "FragmentTurmas");
        fragmentTransaction1.addToBackStack(null);
        fragmentTransaction1.commit();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_professor, menu);

        textNome = (TextView) findViewById(R.id.userNome);
        textEmail = (TextView) findViewById(R.id.userEmail);

        if(user != null) {
            if(user.getDisplayName() != null && textNome != null)
                textNome.setText(user.getDisplayName());
            if(user.getEmail() != null && textEmail != null) {
                textEmail.setText(user.getEmail());
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_turmas) {
            setTitle("Lista turmas");
            TurmasListFragment turmasListFragment = new TurmasListFragment();
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.frameDashProfessor, turmasListFragment, "FragmentTurmas");
            fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.commit();
        } else if (id == R.id.nav_mensagens) {

        } else if (id == R.id.nav_perfil) {

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DashboardProfessorActivity.this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
