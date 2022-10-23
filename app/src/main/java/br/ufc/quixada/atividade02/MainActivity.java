package br.ufc.quixada.atividade02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.quixada.atividade02.model.Aluno;
import br.ufc.quixada.atividade02.transactions.Constants;

public class MainActivity extends AppCompatActivity {

    int selected;
    ArrayList<Aluno> listaAlunos;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaAlunos = new ArrayList<Aluno>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAlunos);
        ListView listViewAlunos = (ListView) findViewById(R.id.listViewAlunos);
        listViewAlunos.setAdapter(adapter);
        listViewAlunos.setSelector(android.R.color.holo_blue_light);

        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(MainActivity.this,
                        "" + listaAlunos.get(position).toString(),
                        Toast.LENGTH_SHORT).show();
                selected = position;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                clicarAdicionar();
                break;
            case R.id.edit:
                clicarEditar();
                break;
            case R.id.delete:
                apagarItemLista();
                break;
        }
        return true;
    }

    public void apagarItemLista() {
        if (selected >= 0) {
            listaAlunos.remove(selected);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(MainActivity.this, "Selecione um Item", Toast.LENGTH_SHORT).show();
        }
    }

    public void clicarAdicionar() {
        Intent intent = new Intent(this, ActivityEdit.class);
        startActivityForResult(intent, Constants.REQUEST_ADD);
    }

    public void clicarEditar() {
        if (selected >= 0) {
            Intent intent = new Intent(this, ActivityEdit.class);

            Aluno aluno = listaAlunos.get(selected);

            intent.putExtra("id", aluno.getId());
            intent.putExtra("nome", aluno.getNome());
            intent.putExtra("matricula", aluno.getMatricula());
            intent.putExtra("curso", aluno.getCurso());

            startActivityForResult(intent, Constants.REQUEST_EDIT);
        } else {
            Toast.makeText(MainActivity.this, "Selecione um Item", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD) {
            String nome = (String) data.getExtras().get("nome");
            String matricula = (String) data.getExtras().get("matricula");
            String curso = (String) data.getExtras().get("curso");
            Aluno aluno = new Aluno(nome, matricula, curso);
            listaAlunos.add(aluno);
            adapter.notifyDataSetChanged();
        } else if (requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD) {
            String nome = (String) data.getExtras().get("nome");
            String matricula = (String) data.getExtras().get("matricula");
            String curso = (String) data.getExtras().get("curso");
            int idEditar = (int) data.getExtras().get("id");

            for (Aluno aluno : listaAlunos) {
                if (aluno.getId() == idEditar) {
                    aluno.setNome(nome);
                    aluno.setMatricula(matricula);
                    aluno.setCurso(curso);
                }
            }
            adapter.notifyDataSetChanged();
        } else if (resultCode == Constants.RESULT_CANCEL) {
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        }
    }
}