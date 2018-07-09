package com.example.isabella.app_movel;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TurmasList extends ArrayAdapter<Turma> {

    private Activity context;
    private List<Turma> turmasList;

    public TurmasList(Activity context, List<Turma> turmasList){
        super(context, R.layout.list_turmas_layout, turmasList);
        this.context = context;
        this.turmasList = turmasList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_turmas_layout, null, true);

        TextView textViewCod = (TextView) listViewItem.findViewById(R.id.codTurma);
        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.nomeTurma);
        TextView textViewTurno = (TextView) listViewItem.findViewById(R.id.turnoTurma);

        Turma turma = turmasList.get(position);

        if(turma.getCodTurma() != null)
            textViewCod.setText(turma.getCodTurma().toString());
        if(turma.getNomeTurma() != null)
            textViewNome.setText(turma.getNomeTurma());
        if(turma.getTurnoTurma() != null)
            textViewTurno.setText(turma.getTurnoTurma());

        return listViewItem;
    }
}
