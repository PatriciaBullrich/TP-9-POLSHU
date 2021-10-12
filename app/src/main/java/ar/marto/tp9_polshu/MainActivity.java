package ar.marto.tp9_polshu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import ar.marto.tp9_polshu.utiles.AlertHelper;

public class MainActivity extends BaseActivity {
    public static final int REQUEST_FOTO = 101;
    public static final int REQUEST_TEXT = 102;
    public static final int REQUEST_RINGTONE = 103;


    public void inicializar(){
    }

    public void setearListeners(){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED || data == null) {
            AlertHelper.mostrarMensaje(getApplicationContext(), "cancelaste desde la otra activity"); // puede ser toast tambien
            return;
        }
        switch (requestCode) {
            case REQUEST_TEXT:
                String text = data.getStringExtra("resultado");
                // llenar un TV con lo que reicibi
                // tv_texto.setText(text);
                break;
            case REQUEST_FOTO:
                //elegir una foto de la galeria y ponerla en un imgView
                Uri selectedIamge = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedIamge,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                /*ImageView jorge;
                jorge.setImageResource(picturePath);*/
                break;

            case REQUEST_RINGTONE:
                // ir a cambiar el ringotone
                Uri ringtone = Uri.parse(data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI));
                RingtoneManager.setActualDefaultRingtoneUri(
                        this,
                        RingtoneManager.TYPE_RINGTONE,
                        ringtone);
                tostada(String.format("cambiaste tu ringtone a %s", RingtoneManager.URI_COLUMN_INDEX));
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        setearListeners();
    }


}