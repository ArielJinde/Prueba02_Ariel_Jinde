package AHJSRegistroData;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import DataBase.AHJS_DBHelper;
import app004.flagquizapp.R;
import app004.flagquizapp_ahjs.MainActivity_ahjs;


public class AHJS_Registro extends AppCompatActivity {
    /*Titulo de la actividad*/
    String Title="Registro de Usuarios";
    /*EditText para el ingreso de datos del usuario*/
    EditText Etusurname,EtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahjs_registro);

        this.setTitle(Title);
        /*Instanciamos las variables
         * capturamos los datos*/
        Etusurname = (EditText) findViewById(R.id.editTextTextUserName);
        EtPass=(EditText) findViewById(R.id.editTextTextPassword);
    }
    /*Metodo Para registrar los datos del usuario*/
    public void RegistrarDataUser(View v){
        /*creamos un objeto de la clase DBHelper
         * inicializamos el constructor
         * nombramos la base de datos
         * version de la base de datos*/
        AHJS_DBHelper admin=new AHJS_DBHelper(this,"instituto",null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        /*creamos dos variables string
         * inicializamos y convertimos*/
        String UserName=Etusurname.getText().toString();
        String PassUser=EtPass.getText().toString();
        /*Creamos un objeto contentvalues y instanciamos*/
        ContentValues values = new ContentValues();
        /*capturamos valores*/
        values.put("username",UserName);
        values.put("clave_user",PassUser);
        /*llamamos al insert damos el nombre de la base de datos
         * y los valores*/
        db.insert("userstable",null,values);
        /*cerramos la base de datos*/
        db.close();
        /*Lanzamos una notificacion toast*/
        Toast ToastMens= Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT);
        /*mostramos el toast*/
        ToastMens.show();
        /*lanzamos la actividad*/
        Intent intent=new Intent(this, MainActivity_ahjs.class);
        /*iniciamos la actividad*/
        startActivity(intent);
    }
}