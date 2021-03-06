package ar.marto.tp9_polshu;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ar.marto.tp9_polshu.utiles.AlertHelper;
import ar.marto.tp9_polshu.utiles.CustomLog;

public class MainActivity extends BaseActivity {
    public static final int REQUEST_FOTO = 101;
    public static final int REQUEST_TEXT = 102;
    public static final int REQUEST_CONTACT = 103;
    Button btn_foto;
    Button btn_texto;
    Button btn_ringtone;
    ImageView mi_imagen;
    TextView tv_result;


    public void irAText(){ switchActivity(TextActivity.class,REQUEST_TEXT);}

    public void irAFoto(){
        //switchActivity(FotoActivity.class, REQUEST_FOTO);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_FOTO);
    }
    public void irAContact(){
        Uri uri = Uri.parse("content://contacts");
        //Intent intent = new Intent(Intent.ACTION_PICK);
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        intent.setDataAndType(uri,ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    public void inicializar(){
         btn_foto = (Button) findViewById(R.id.btn_foto);
         btn_texto = (Button) findViewById(R.id.btn_texto);
         btn_ringtone = (Button) findViewById(R.id.btn_rintone);
         mi_imagen = (ImageView) findViewById(R.id.mi_imagen);
         tv_result = (TextView) findViewById(R.id.tv_result);
    }

    public void setearListeners(){
        btn_foto.setOnClickListener(btn_foto_click);
        btn_texto.setOnClickListener(btn_texto_click);
        btn_ringtone.setOnClickListener(btn_ring_click);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED || data == null) {
            AlertHelper.mostrarMensaje(this, "cancelaste desde la otra activity"); // puede ser toast tambien
            return;
        }
        switch (requestCode) {
            case REQUEST_TEXT:
                String text = data.getStringExtra("result");
                CustomLog.log(text);
                 tv_result.setText(text);
                break;
            case REQUEST_FOTO:
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                if(thumbnail != null)mi_imagen.setImageBitmap(thumbnail);
                else AlertHelper.mostrarAlertaError(this, "no me llego ninguna foto");
                break;
            case REQUEST_CONTACT:
                Uri uri =data.getData();
                String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };
                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);
                tostada(String.format("tu contacto es: %s con el numero: %s", name,number));
                break;
        }
    }



    View.OnClickListener btn_foto_click = v -> irAFoto();
    View.OnClickListener btn_texto_click = v -> irAText();
    View.OnClickListener btn_ring_click = v -> irAContact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        setearListeners();
    }


}