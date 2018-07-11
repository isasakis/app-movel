package com.example.isabella.app_movel;


import android.content.Context;
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
    onAlunoSelectedListener listener;

    public interface onAlunoSelectedListener{
        public void onAlunoSelected(String key);
    }

    public AlunosListFragment() {
        // Required empty public constructor
    }

    public void setCodTurma(Integer cod){
        codTurma = cod;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (onAlunoSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ " deve implementar onAlunoSelectedListener");
        }
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

        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno a = alunoList.get(position);
                listener.onAlunoSelected(a.getUid());
            }
        });

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
