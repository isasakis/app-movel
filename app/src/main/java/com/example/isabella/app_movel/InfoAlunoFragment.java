package com.example.isabella.app_movel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoAlunoFragment extends Fragment {

    String keyAluno;
    TabLayout tabInfos;
    ViewPager viewPager;
    Integer codTurma;
    Aluno aluno;
    TextView textNome;
    TextView textDataNasc;
    TextView textCpf;
    TextView textRg;
    TextView textNomePai;
    TextView textNomeMae;
    TextView textFonePai;
    TextView textFoneMae;
    TextView textSexo;
    TextView textProblemaSaude;
    TextView textRestricaoAlimentar;
    TextView textRemedioNome;
    TextView textRemedioHora;
    TextView textRemedioObs;

    DatabaseReference databaseAluno;

    public InfoAlunoFragment() {
        // Required empty public constructor
    }

    public void setKeyAluno(String key){
        keyAluno = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_aluno, container, false);

        textNome = (TextView) view.findViewById(R.id.nomeValue);
        textDataNasc = (TextView) view.findViewById(R.id.dataNascValue);
        textCpf = (TextView) view.findViewById(R.id.cpfValue);
        textRg = (TextView) view.findViewById(R.id.rgValue);
        textSexo = (TextView) view.findViewById(R.id.sexoValue);
        textNomePai = (TextView) view.findViewById(R.id.nomePaiValue);
        textNomeMae = (TextView) view.findViewById(R.id.nomeMaeValue);
        textFonePai = (TextView) view.findViewById(R.id.fonePaiValue);
        textFoneMae = (TextView) view.findViewById(R.id.foneMaeValue);
        textProblemaSaude = (TextView) view.findViewById(R.id.problemaValue);
        textRestricaoAlimentar = (TextView) view.findViewById(R.id.restricaoValue);
        textRemedioNome = (TextView) view.findViewById(R.id.nomeRemedioValue);
        textRemedioHora = (TextView) view.findViewById(R.id.horarioRemedioValue);
        textRemedioObs = (TextView) view.findViewById(R.id.obsRemedioValue);

        String database = "alunos/"+keyAluno;
        databaseAluno = FirebaseDatabase.getInstance().getReference(database);

        // Attach a listener to read the data at our posts reference
        databaseAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aluno = dataSnapshot.getValue(Aluno.class);
                if(aluno != null){
                    if(aluno.getNome() != null)
                        textNome.setText(aluno.getNome());
                    if(aluno.getNome() != null)
                        textDataNasc.setText(aluno.getDataNasc());
                    if(aluno.getCpf() != null)
                        textCpf.setText(aluno.getCpf());
                    if(aluno.getRg() != null)
                        textRg.setText(aluno.getRg());
                    if(aluno.getSexo() != null)
                        textSexo.setText(aluno.getSexo());
                    if(aluno.getNomeMae() != null)
                        textNomeMae.setText(aluno.getNomeMae());
                    if(aluno.getNomePai() != null)
                        textNomePai.setText(aluno.getNomePai());
                    if(aluno.getFoneMae() != null)
                        textFoneMae.setText(aluno.getFoneMae());
                    if(aluno.getNome() != null)
                        textFonePai.setText(aluno.getFonePai());
                    if(aluno.getProblemaSaude() != null)
                        textProblemaSaude.setText(aluno.getProblemaSaude());
                    else
                        textProblemaSaude.setText("Nenhum registro");
                    if(aluno.getRestricaoAlimentar() != null)
                        textRestricaoAlimentar.setText(aluno.getRestricaoAlimentar());
                    else
                        textRestricaoAlimentar.setText("Nenhum registro");
                    if(aluno.getRemedio()!= null) {
                        if (aluno.getRemedio().getNome() != null)
                            textRemedioNome.setText(aluno.getRemedio().getNome());
                        else
                            textRemedioNome.setText("Nenhum registro");
                        if (aluno.getRemedio().getHorario() != null)
                            textRemedioHora.setText(aluno.getRemedio().getHorario());
                        else
                            textRemedioHora.setText("Nenhum registro");
                        if (aluno.getRemedio().getObservacao() != null)
                           textRemedioObs.setText(aluno.getRemedio().getObservacao());
                        else
                           textRemedioObs.setText("Nenhum registro");
                    } else {
                        textRemedioNome.setText("Nenhum registro");
                        textRemedioHora.setText("Nenhum registro");
                        textRemedioObs.setText("Nenhum registro");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
