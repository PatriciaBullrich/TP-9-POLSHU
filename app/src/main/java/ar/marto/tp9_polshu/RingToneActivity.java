package ar.marto.tp9_polshu;


import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.ButtonBarLayout;

public class RingToneActivity extends BaseActivity {
    Button btn_ringtone;
     // Button btn_cancelar; ????
    public void inicializar(){
        btn_ringtone = (Button) findViewById(R.id.gone); // xml
    }

    public void setearListeners(){
        btn_ringtone.setOnClickListener(btn_ringtone_click);
    }

    View.OnClickListener btn_ringtone_click = v ->{
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
                RingtoneManager.TYPE_RINGTONE);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Ringtone");
        Uri urie =     RingtoneManager.getActualDefaultRingtoneUri(
                getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, urie);

        startActivityForResult(intent, MainActivity.REQUEST_RINGTONE);
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_tone);
        inicializar();
        setearListeners();
    }
}