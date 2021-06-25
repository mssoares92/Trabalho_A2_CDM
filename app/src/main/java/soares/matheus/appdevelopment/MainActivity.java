package soares.matheus.appdevelopment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private ListView lvDevs;
    private AdapterDevs adapter;
    private List<Dev> listaDevs;
    private List<Dev> devsFiltrados = new ArrayList<>();
    private DevDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });
        lvDevs = findViewById(R.id.lvDevs);

        carregarDevs();
        configListView();
    }

    private void configListView() {

        lvDevs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Dev devSelecionado = listaDevs.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idDev", devSelecionado.id);
                startActivity(intent);
            }
        });

        lvDevs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Dev devSelecionado = listaDevs.get(position);
                excluirDev(devSelecionado);
                return true;
            }
        });
    }

    private void excluirDev(Dev dev) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage("Confirm deletion of  " + dev.nome + "?");
        alerta.setNeutralButton("Cancel", null);
        alerta.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DevDAO.excluir(dev.id, MainActivity.this);
                carregarDevs();
            }
        });

        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarDevs();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregarDevs() {
        listaDevs = DevDAO.getDev(this);
        //adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1, listaDevs);

        adapter = new AdapterDevs(this, listaDevs);
        lvDevs.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Menu que leva até o icones de CADASTRO e PESQUISA
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_main, menu);

        //Ligação do botão PESQUISAR com a função
        SearchView sv = (SearchView) menu.findItem(R.id.menu_pesquisa).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

   /* public void procuraDev(String nome){
        devsFiltrados.clear();
        for(Dev d: listaDevs){
            if(d.getDevelopment().toLowerCase().contains(nome.toLowerCase())){
                devsFiltrados.add(d);
            }
        }
        lvDevs.invalidateViews();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Função onde ao clicar no icone "+" Cadastrar leva para a tela de cadastro de linguagem
        Intent intent = new Intent(MainActivity.this, FormularioLinguagem.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}