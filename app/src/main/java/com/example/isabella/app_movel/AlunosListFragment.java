package com.example.isabella.app_movel;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosListFragment extends Fragment {

    View alunos;
    ListView listViewTurmas;
    ListView listViewAlunos;
    String databaseReference;
    DatabaseReference databaseAlunos;
    List<Aluno> alunoList;
    Integer codTurma;

    public AlunosListFragment() {
        // Required empty public constructor
    }

    public void setCodTurma(Integer cod){
        codTurma = cod;
        System.out.println("COD TURMA alunos fragment: " + cod);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        alunos = inflater.inflate(R.layout.fragment_alunos_list, container, false);
        alunoList = new ArrayList<>();

        databaseReference = "alunos/turma"+codTurma+"/";
        listViewAlunos = (ListView) alunos.findViewById(R.id.listViewAlunos);
        databaseAlunos = FirebaseDatabase.getInstance().getReference(databaseReference);

        return alunos;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseAlunos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                alunoList.clear();

                for(DataSnapshot turmaSnapshot: dataSnapshot.getChildren()){
                    Aluno aluno = turmaSnapshot.getValue(Aluno.class);
                    alunoList.add(aluno);
                }

                AlunosList adapter = new AlunosList(getActivity(), alunoList);
                listViewAlunos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
