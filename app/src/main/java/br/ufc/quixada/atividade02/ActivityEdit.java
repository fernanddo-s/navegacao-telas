package br.ufc.quixada.atividade02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufc.quixada.atividade02.transactions.Constants;

public class ActivityEdit extends AppCompatActivity {

    EditText edtNome;
    EditText edtMatricula;
    EditText edtCurso;

    boolean edit;
    int idAlunoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtNome = findViewById(R.id.editTextNome);
        edtMatricula = findViewById(R.id.editTextMatricula);
        edtCurso = findViewById(R.id.editTextCurso);
        edit =false;
        if(getIntent().getExtras() != null){
            String nome = (String) getIntent().getExtras().get("nome");
            String matricula = (String) getIntent().getExtras().get("matricula");
            String curso = (String) getIntent().getExtras().get("curso");
            idAlunoEditar = (int)getIntent().getExtras().get("id");

            edtNome.setText(nome);
            edtMatricula.setText(matricula);
            edtCurso.setText(curso);

            edit = true;
        }
    }

    public void cancelar(View view){
        setResult(Constants.RESULT_CANCEL);
        finish();
    }

    public  void adicionar(View view){
        Intent intent = new Intent();

        String nome = edtNome.getText().toString();
        String matricula = edtMatricula.getText().toString();
        String curso = edtCurso.getText().toString();

        intent.putExtra("nome", nome);
        intent.putExtra("matricula", matricula);
        intent.putExtra("curso", curso);

        if(edit) intent.putExtra("id", idAlunoEditar);

        setResult(Constants.RESULT_ADD, intent);
        finish();
    }
}