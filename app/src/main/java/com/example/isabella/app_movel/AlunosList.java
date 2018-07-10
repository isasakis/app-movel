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

public class AlunosList extends ArrayAdapter<Aluno> {

    private Activity context;
    private List<Aluno> alunoList;

    public AlunosList(@NonNull Activity context, @NonNull List<Aluno> alunoList) {
        super(context, R.layout.list_alunos_layout, alunoList);
        this.context = context;
        this.alunoList = alunoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_alunos_layout, null, true);

        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.textNomeAluno);

        Aluno aluno = alunoList.get(position);

        if(aluno.getNome() != null)
            textViewNome.setText(aluno.getNome().toString());

        return listViewItem;
    }


}
