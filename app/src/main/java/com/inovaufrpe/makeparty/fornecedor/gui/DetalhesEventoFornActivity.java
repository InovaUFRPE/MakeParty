package com.inovaufrpe.makeparty.fornecedor.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inovaufrpe.makeparty.R;
import com.inovaufrpe.makeparty.fornecedor.dominio.Event;
import com.inovaufrpe.makeparty.fornecedor.gui.adapter.FiltroEventoSelecionado;
import com.inovaufrpe.makeparty.infra.SessaoApplication;
import com.inovaufrpe.makeparty.user.gui.adapter.FiltroAnuncioSelecionado;

import java.text.SimpleDateFormat;

public class DetalhesEventoFornActivity extends AppCompatActivity {
    private TextView nomeClienteEventoSelecionado, dataInicioEventoSelecionado,
            dataFimTerminaEmQueDiaEventoSelecionado, dataFimEventoSelecionado, tipoEventoFornSelecionado,
            enderecoEventoForn, descricaoEventoFornSelecionado;
    private Button btAtualizarEventoForn, btExcluirEventoForn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento_forn);
        SessaoApplication.getInstance().setTelaAtual(DetalhesEventoFornActivity.class);
        procurandoViews();
        setandoAcoesBotoes();
    }

    private void procurandoViews() {
        setUpToolbar();
        nomeClienteEventoSelecionado = findViewById(R.id.textViewNomeClienteEventoSelecionado);
        dataInicioEventoSelecionado = findViewById(R.id.textViewDataInicioEventoSelecionado);
        dataFimEventoSelecionado = findViewById(R.id.textViewDataFimEventoSelecionado);
        dataFimTerminaEmQueDiaEventoSelecionado = findViewById(R.id.textViewDataFimTerminaEmQueDiaEventoSelecionado);
        tipoEventoFornSelecionado = findViewById(R.id.textViewTipoEouIEventoSelecionado);
        enderecoEventoForn = findViewById(R.id.textViewDescricaoEventoSelecionado);
        descricaoEventoFornSelecionado = findViewById(R.id.textViewDescricaoEventoSelecionado);
        btAtualizarEventoForn = findViewById(R.id.buttonEditarEventoForn);
        btExcluirEventoForn = findViewById(R.id.buttonExcluirEventoForn);
        setandoInfoDetalhes();
    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setandoAcoesBotoes() {
        btAtualizarEventoForn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mudarTela(EditarEventoActivity.class);
            }
        });

        btExcluirEventoForn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirEvento();
            }
        });
    }
    private void setandoInfoDetalhes(){
        Event eventoSelecionado= FiltroEventoSelecionado.instance.getEventoSelecionado();
        nomeClienteEventoSelecionado.setText(eventoSelecionado.getClient());
        SimpleDateFormat sdfDiaMesAno = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHoraMin = new SimpleDateFormat("HH:mm");
        String diaMesAnoInicioEvento = sdfDiaMesAno.format(eventoSelecionado.getStartDate());
        String horaMinInicioEvento = sdfHoraMin.format(eventoSelecionado.getStartDate());
        dataInicioEventoSelecionado.setText(("Data de inicio: "+diaMesAnoInicioEvento+" às "+horaMinInicioEvento));
        String diaMesAnoFimEvento = sdfDiaMesAno.format(eventoSelecionado.getEndDate());
        String horaMinFimEvento = sdfHoraMin.format(eventoSelecionado.getEndDate());
        dataFimEventoSelecionado.setText(("Data de fim: "+diaMesAnoFimEvento+" às "+horaMinFimEvento));
        tipoEventoFornSelecionado.setText(eventoSelecionado.getType());
        enderecoEventoForn.setText(("Endereço : "+eventoSelecionado.getAddress().getStreet()
                +", "+ "Bairro :"+eventoSelecionado.getAddress().getNeighborhood()+", "+ "Número :"
                +eventoSelecionado.getAddress().getNumber()+", "+"Cidade :" +eventoSelecionado.getAddress().getCity()
                //+","+anuncioSelecionado.getAddress().getState()
                +", CEP :"+eventoSelecionado.getAddress().getZipcode()));
        descricaoEventoFornSelecionado.setText(eventoSelecionado.getDescription());

    }

    private void excluirEvento() {

    }

    public void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(SessaoApplication.getInstance().getTelaAnterior());
    }
}

