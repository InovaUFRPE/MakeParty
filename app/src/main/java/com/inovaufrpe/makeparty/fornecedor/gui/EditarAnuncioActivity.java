package com.inovaufrpe.makeparty.fornecedor.gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inovaufrpe.makeparty.R;
import com.inovaufrpe.makeparty.cliente.gui.adapter.FiltroAnuncioSelecionado;
import com.inovaufrpe.makeparty.cliente.gui.fragment.dialog.SimOuNaoDialog;
import com.inovaufrpe.makeparty.fornecedor.dominio.Ads;
import com.inovaufrpe.makeparty.usuario.dominio.Endereco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditarAnuncioActivity extends AppCompatActivity {

    private Spinner spinnerTipoAnuncio;
    private EditText editTextTituloAnuncio,editTextValorAnuncio,editTextDescAnuncio,editTextTagsAnuncio,editTextTelefoneAnuncio;
    private EditText editTextRuaAnuncio,editTextNumeroEndAnuncio,editTextBairroEndAnuncio,editTextCidadeEndAnuncio,editTextCepEndAnuncio;
    private TextView textViewLimitesAnuncio;
    private Button buttonAtualizarAnuncio,buttonExcluirAnuncio;
    private ImageButton ImgButtonGalFotosAnex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anuncio);
        encontrandoItensView();
    }
    public void encontrandoItensView(){
        spinnerTipoAnuncio = findViewById(R.id.spinnertipoAnuncioEditar);
        editTextTituloAnuncio = findViewById(R.id.editTextTituloEditarAn);
        editTextValorAnuncio = findViewById(R.id.editTextValorEditarAn);
        editTextDescAnuncio = findViewById(R.id.editTextDescricaoEditarAn);
        editTextTagsAnuncio = findViewById(R.id.editTextTagsEditarAn);
        editTextTelefoneAnuncio = findViewById(R.id.editTextTelefoneEditarAnun);
        editTextRuaAnuncio = findViewById(R.id.RuaIdEditarAnuncio);
        editTextNumeroEndAnuncio = findViewById(R.id.editTextNumeroEditarAnuncio);
        editTextBairroEndAnuncio = findViewById(R.id.editTextBairroEditarAnuncio);
        editTextCidadeEndAnuncio = findViewById(R.id.editTextCidadeEditarAnuncio);
        editTextCepEndAnuncio = findViewById(R.id.editTextCepEditarAnuncio);
        textViewLimitesAnuncio= findViewById(R.id. textViewObsLimitesAnuncioForn);
        ImgButtonGalFotosAnex = findViewById(R.id.imgButtonGalFotosAnexAnEdit);
        buttonAtualizarAnuncio = findViewById(R.id.button_atualizar_anuncio);
        buttonExcluirAnuncio = findViewById(R.id.button_excluir_anuncio);
        acoesButoesAtualizarOuExcluirAnuncio();
        //setandoInfoItensViewAntesEdicao();

    }
    public void acoesButoesAtualizarOuExcluirAnuncio(){
        buttonAtualizarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliqueAtualizarItensAnuncio();
            }
        });
        buttonExcluirAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliqueExcluindoAnuncio();
            }
        });
    }

    public void setandoInfoItensViewAntesEdicao(){
        Ads anuncioSelecionado = FiltroAnuncioSelecionado.instance.getAnuncioSelecionado();

        //spinnerTipoAnuncio.setOnItemSelectedListener(anuncioSelecionado.getType());

        editTextTituloAnuncio.setText(anuncioSelecionado.getTitle());
        editTextValorAnuncio.setText((int) anuncioSelecionado.getPrice());
        editTextDescAnuncio.setText(anuncioSelecionado.getDescription());
        editTextTagsAnuncio.setText((anuncioSelecionado.getTags().toString()));
        editTextTelefoneAnuncio.setText((anuncioSelecionado.getPhone()));
        editTextRuaAnuncio.setText(anuncioSelecionado.getAddress().getStreet());
        editTextNumeroEndAnuncio.setText(anuncioSelecionado.getAddress().getNumber());
        editTextBairroEndAnuncio.setText(anuncioSelecionado.getAddress().getNeighborhood());
        editTextCidadeEndAnuncio .setText(anuncioSelecionado.getAddress().getCity());
        editTextCepEndAnuncio.setText(anuncioSelecionado.getAddress().getZipcode());
        textViewLimitesAnuncio.setText("");
        ImgButtonGalFotosAnex = findViewById(R.id.imgButtonGalFotosAnexAnEdit);
        buscarAntigasImgAntesEdicao();
    }
    public void buscarAntigasImgAntesEdicao(){

    }
    public void cliqueAtualizarItensAnuncio(){
        SimOuNaoDialog.show(getSupportFragmentManager(),"Deseja confirmar a atualização desse anúncio?", new SimOuNaoDialog.Callback() {
            @Override
            public void metodoSimAoDialog() {
                //
                //
                showProgressDialogWithTitle("Por favor, espere", "atualizando dados do anúncio");
                msgToast("Anúncio atualizado com sucesso");
                msgToast("Erro");
                mudarTela(AnunciosFornecedorActivity.class);
            }
        });
    }
    public Ads retornandoAnuncioComNovosDadosParaAtualizar(){
        Ads dadosAnuncioSelecionadoAntesEdicao = FiltroAnuncioSelecionado.instance.getAnuncioSelecionado();
        Ads anuncioASerEnviadoPUT =  new Ads();
        anuncioASerEnviadoPUT.set_id( dadosAnuncioSelecionadoAntesEdicao.get_id());
        anuncioASerEnviadoPUT.setTitle(editTextTituloAnuncio.getText().toString());
        anuncioASerEnviadoPUT.setPrice(Double.parseDouble(editTextValorAnuncio.getText().toString().trim()));
        anuncioASerEnviadoPUT.setDescription(editTextDescAnuncio.getText().toString());
        anuncioASerEnviadoPUT.setPhone(editTextTelefoneAnuncio.getText().toString());

        String textoTags = editTextTagsAnuncio.getText().toString();
        String[] itensDasTags = textoTags.split(",");
        List<String> arrayDasTags = new ArrayList<String>();
        Collections.addAll(arrayDasTags, itensDasTags);
        anuncioASerEnviadoPUT.setTags((ArrayList) arrayDasTags);

        Endereco endAnuncioEditado = new Endereco();
        endAnuncioEditado.setStreet(editTextRuaAnuncio.getText().toString());
        endAnuncioEditado.setNeighborhood(editTextBairroEndAnuncio.getText().toString());
        endAnuncioEditado.setCity(editTextCidadeEndAnuncio.getText().toString());
        endAnuncioEditado.setNumber(editTextNumeroEndAnuncio.getText().toString());
        endAnuncioEditado.setState("Pernambuco"); //MUDAR ISSO AQ EINNNNNNNNNN
        endAnuncioEditado.setZipcode(editTextCepEndAnuncio.getText().toString());
        anuncioASerEnviadoPUT.setAddress(endAnuncioEditado);


        //textViewLimitesAnuncio.setText("");
        ImgButtonGalFotosAnex = findViewById(R.id.imgButtonGalFotosAnexAnEdit);
        guardandoNovasImgsSelecionadasEditadas();

        return anuncioASerEnviadoPUT;
    }
    public void guardandoNovasImgsSelecionadasEditadas(){

    }

    public void cliqueExcluindoAnuncio() {
        SimOuNaoDialog.show(getSupportFragmentManager(), "Deseja mesmo excluir esse anúncio ?", new SimOuNaoDialog.Callback() {
            @Override
            public void metodoSimAoDialog() {
                //
                //

                showProgressDialogWithTitle("Por favor, espere", "excluindo anúncio");
                msgToast("Anúncio excluído com sucesso");
                msgToast("Erro");
                mudarTela(AnunciosFornecedorActivity.class);

            }
        });
    }
    public void showProgressDialogWithTitle(String title, String msgEmbaixo) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msgEmbaixo);
        progressDialog.show();
    }
    public void msgToast(String msgToast){
        Toast.makeText(this, msgToast, Toast.LENGTH_SHORT).show();
        finish();
    }
    public void mudarTela(Class tela){
        Intent intent=new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        this.mudarTela(AnunciosFornecedorActivity.class);

    }

}