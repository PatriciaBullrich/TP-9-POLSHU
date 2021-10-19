package ar.marto.tp9_polshu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ar.marto.tp9_polshu.utiles.CustomLog;
import java.util.Locale;
import java.util.Set;
public class BaseActivity extends AppCompatActivity {


    // makes a simple toast, you can modify the length or the gravity if you like
    public void tostada(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }

    //region Replace fragment
    // this functions use a predefined framelayout as the container where all the fragments are replaced
    // you can change the frame layout to the one that you use or pass it in params
    // addBackToStack false, the app will not remember the fragment if you go back or delete it
   /* public void reemplazarFragment(Fragment frag, boolean addBackToStack){
        FragmentManager manager;
        FragmentTransaction transaction;

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fraContenedor,frag,null);
        if(addBackToStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }
    public void reemplazarFragment(Fragment frag){
        reemplazarFragment(frag,true);
    } */
    //endregion


    public void setCanceledResult(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }

    public void switchActivity(Class a){
        Intent intent = new Intent(this, a);
        startActivity(intent);
    }

    public void switchActivity(Class a, int requestCode){
        Intent intent = new Intent(this, a);
        startActivityForResult(intent,requestCode);
    }



    // region Preferences functions
    // to save preferences call this.SavePreferences(key, value).
    // to read preferences call this.ReadPreferences(value, datatype as a string) EG: ReadPreferences("userCount", "int").
    // To clear the xml prefs file call this.clearPreferences()
    // this methods implement the shared prefs object
    protected void SavePreferences(String key, int value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key, String value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key, boolean value){
        if(key == null) {
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key, float value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key,value);
        editor.apply(); //editor.commit():
    }


    protected void SavePreferences(String key, long value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key,  Set<String> value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key,value);
        editor.apply(); //editor.commit():
    }
    //leer de las preferencias
    // * write the primitive {@type type} for the function to work.
    // * if you miss the spelling it returns null and logs
    //
    protected Object ReadPreferences (String key, String type) {
        //needs the datatype of the return value
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return null;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Object result = null;
        switch (type.toLowerCase(Locale.ROOT)){
            case "int":
                result =  (int) sharedPref.getInt(key,0);// can replace default with whatever you want
                break;
            case "float":
                result =  (float) sharedPref.getFloat(key,0);
                break;
            case "long":
                result =  (long) sharedPref.getLong(key,0);
                break;
            case "string":
                result =  (String) sharedPref.getString(key,"");
                break;
            case "boolean":
                result =  (boolean) sharedPref.getBoolean(key,false);
                break;
            case "set":
                result =  (Set<String>) sharedPref.getStringSet(key,null);
                break;
            default:
                CustomLog.log("no encontre el tipo de dato especificado");
                break;
        }
        return result;
    }

    public void clearPreferences(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
    //endregion
}
