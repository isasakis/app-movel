package com.example.isabella.app_movel;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
public class TurmasListFragment extends Fragment {

    ListView listViewTurmas;
    DatabaseReference databaseTurmas;
    List<Turma> turmaList;
    View minhasTurmas;
    onTurmaSelectedListener listener;

    public TurmasListFragment() {
        // Required empty public constructor
    }

    public interface onTurmaSelectedListener{
        public void onItemSelected(Integer cod);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (onTurmaSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ " deve implementar onTurmaSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        minhasTurmas =  inflater.inflate(R.layout.fragment_turmas_list, container, false);

        listViewTurmas = (ListView) minhasTurmas.findViewById(R.id.listViewTurmas);
        databaseTurmas = FirebaseDatabase.getInstance().getReference("turmas");
        turmaList = new ArrayList<>();

        listViewTurmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Turma t = turmaList.get(position);
                listener.onItemSelected(t.getCodTurma());
            }
        });

        return minhasTurmas;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseTurmas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                turmaList.clear();

                for(DataSnapshot turmaSnapshot: dataSnapshot.getChildren()){
                    Turma turma = turmaSnapshot.getValue(Turma.class);
                    turmaList.add(turma);
                }

                TurmasList adapter = new TurmasList(getActivity(), turmaList);
                listViewTurmas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
