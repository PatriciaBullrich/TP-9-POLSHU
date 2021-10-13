package ar.marto.tp9_polshu;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FotoActivity extends BaseActivity {
    Button btn_galeria;
    Button btn_cancelar2;
    public void inicializar(){
        btn_galeria = (Button) findViewById(R.id.gone); //falta xml
        btn_cancelar2 =(Button) findViewById(R.id.gone);
    }

    public void setearListeners(){
        btn_galeria.setOnClickListener(btn_galeria_click);
        btn_cancelar2.setOnClickListener(btn_cancelar_click);
    }

    View.OnClickListener btn_galeria_click = v->{
       Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, MainActivity.REQUEST_FOTO);
    };

    View.OnClickListener btn_cancelar_click = v -> setCanceledResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        inicializar();
        setearListeners();
    }
}