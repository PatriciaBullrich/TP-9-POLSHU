package ar.marto.tp9_polshu;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextActivity extends BaseActivity {
    EditText edt_texto;
    Button btn_enviar;
    Button btn_cancelar;

    public void inicializar(){
        edt_texto  = (EditText) findViewById(R.id.gone); //falta crear en xml
        btn_enviar = (Button) findViewById(R.id.gone); //falta crear en xml
        btn_cancelar = (Button) findViewById(R.id.gone); //falta crear en xml
    }

    public void setearListeners(){
        btn_enviar.setOnClickListener(btn_enviar_click);
        btn_cancelar.setOnClickListener(btn_cancelar_click);
    }

    View.OnClickListener btn_enviar_click = v ->{
        Intent returnIntent = new Intent();
        String texto = edt_texto.getText().toString().trim(); //validar
        returnIntent.putExtra("result",texto);
        setResult(RESULT_OK,returnIntent);
        finish();
    };

    View.OnClickListener btn_cancelar_click = v ->{
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED,returnIntent);
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        inicializar();
        setearListeners();
    }
}