package soares.matheus.appdevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etDescr;
    private Spinner spDev;
    private Button btnSalvar;
    private String acao;
    private Dev dev;
    private DevDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etDescr = findViewById(R.id.etDescr);
        btnSalvar = findViewById(R.id.btnSalvar);
        spDev = findViewById(R.id.spDev);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        //carregarLinguagens();
    }

    private void carregarFormulario() {
        int idDev = getIntent().getIntExtra("idDev", 0);
        if (idDev != 0) {
            dev = DevDAO.getDevsById(this, idDev);
            etNome.setText(dev.nome);
            etEmail.setText(dev.email);
            etDescr.setText(dev.descr);


            String[] arrayDevs = getResources().getStringArray(R.array.arrayDevs);
            for (int i = 1; i < arrayDevs.length; i++) {

                if (arrayDevs[i] == dev.getDevelopment()) {
                    spDev.setSelection(i);
                }
            }
        }
    }

   private void carregarLinguagens(){
        List<Linguagem> lista = LinguagemDAO.getLinguagem(this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        spDev.setAdapter(adapter);
    }

    private void salvar() {
        if (spDev.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty()) {
            Toast.makeText(this, "All fields must be filled...", Toast.LENGTH_SHORT).show();
        }else {
            if (acao.equals("novo")) {
                dev = new Dev();
            }
            ////////////////////////////////////////////////////////
            dev.nome = etNome.getText().toString();
            dev.email = etEmail.getText().toString();
            dev.descr = etDescr.getText().toString();
            dev.setDevelopment(spDev.getSelectedItem().toString());

            //////////////////////////////////////////////////////

            if (acao.equals("editar")) {
                DevDAO.editar(dev, this);
                finish();
            } else {
                DevDAO.inserir(dev, this);
                etNome.setText("");
                etEmail.setText("");
                etDescr.setText("");
                spDev.setSelection(0);
                 }
            }
        }
    }

