package com.example.isabella.app_movel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddInfoFragment extends Fragment {

    String keyAluno;
    TextView textProblemaSaude;
    TextView textRestricaoAlimentar;
    TextView textRemedioNome;
    TextView textRemedioHora;
    TextView textRemedioObs;
    Aluno aluno;
    Button buttonSalvar;
    DatabaseReference databaseReference;

    public AddInfoFragment() {
        // Required empty public constructor
    }

    public void setKeyAluno(String key){
        keyAluno = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_info, container, false);

        textProblemaSaude = (TextView) view.findViewById(R.id.addProblema);
        textRestricaoAlimentar = (TextView) view.findViewById(R.id.addRestricao);
        textRemedioNome = (TextView) view.findViewById(R.id.addNomeRemedio);
        textRemedioHora = (TextView) view.findViewById(R.id.addHorarioRemedio);
        textRemedioObs = (TextView) view.findViewById(R.id.addObsRemedio);
        buttonSalvar = (Button) view.findViewById(R.id.saveInfo);

        databaseReference = FirebaseDatabase.getInstance().getReference("alunos/"+keyAluno);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String problemaSaude = textProblemaSaude.getText().toString().trim();
                String restricaoAlimentar = textRestricaoAlimentar.getText().toString().trim();
                String remedioNome = textRemedioNome.getText().toString().trim();
                String remedioHora = textRemedioHora.getText().toString().trim();
                String remedioObs = textRemedioObs.getText().toString().trim();

                if(!(TextUtils.isEmpty(problemaSaude)))
                    databaseReference.child("problemaSaude").setValue(problemaSaude);
                if(!(TextUtils.isEmpty(restricaoAlimentar)))
                    databaseReference.child("restricaoAlimentar").setValue(restricaoAlimentar);
                if(!(TextUtils.isEmpty(remedioNome)))
                    databaseReference.child("remedio").child("nome").setValue(remedioNome);
                if(!(TextUtils.isEmpty(remedioHora)))
                    databaseReference.child("remedio").child("horario").setValue(remedioHora);
                if(!(TextUtils.isEmpty(remedioObs)))
                    databaseReference.child("remedio").child("observacao").setValue(remedioObs);

                Toast.makeText(getContext(), "Informações adicionadas com sucesso", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aluno = dataSnapshot.getValue(Aluno.class);
                if(aluno != null){
                    if(aluno.getProblemaSaude() != null)
                        textProblemaSaude.setText(aluno.getProblemaSaude());
                    if(aluno.getRestricaoAlimentar() != null)
                        textRestricaoAlimentar.setText(aluno.getRestricaoAlimentar());
                    if(aluno.getRemedio()!= null) {
                        if (aluno.getRemedio().getNome() != null)
                            textRemedioNome.setText(aluno.getRemedio().getNome());
                        if (aluno.getRemedio().getHorario() != null)
                            textRemedioHora.setText(aluno.getRemedio().getHorario());
                        if (aluno.getRemedio().getObservacao() != null)
                            textRemedioObs.setText(aluno.getRemedio().getObservacao());
                    } 
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
