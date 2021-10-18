package ar.marto.tp9_polshu;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.marto.tp9_polshu.utiles.AlertHelper;

public class TextActivity extends BaseActivity {
    EditText edt_texto;
    Button btn_enviar;
    Button btn_cancelar;

    public void inicializar(){
        edt_texto  = (EditText) findViewById(R.id.et_texto); //falta crear en xml
        btn_enviar = (Button) findViewById(R.id.btn_enviar); //falta crear en xml
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar); //falta crear en xml
    }

    public void setearListeners(){
        btn_enviar.setOnClickListener(btn_enviar_click);
        btn_cancelar.setOnClickListener(btn_cancelar_click);
    }

    private boolean llenoElTexto(){ return edt_texto.getText().toString().trim().length()>0;}


    View.OnClickListener btn_enviar_click = v ->{
        if(!llenoElTexto()){
            AlertHelper.mostrarAlertaError(getApplicationContext(),"por favor llena el texto");
            return;
        }
        Intent returnIntent = new Intent();
        String texto = edt_texto.getText().toString().trim();
        returnIntent.putExtra("result",texto);
        setResult(MainActivity.RESULT_OK,returnIntent);
        finish();
    };

    View.OnClickListener btn_cancelar_click = v -> setCanceledResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        inicializar();
        setearListeners();
    }
}