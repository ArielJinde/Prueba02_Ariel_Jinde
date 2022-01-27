package app004.flagquizapp_ahjs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import app004.flagquizapp.R;

public class Home_ahjs extends AppCompatActivity {
private TextView datosT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ahjs);
        datosT = findViewById(R.id.textViewinfo);

        Bundle datos = this.getIntent().getExtras();


        String mensaje = datos.getString("nombre");
        String nivel = datos.getString("nivel");
        datosT.setText("Jugador : "+mensaje+"  Nivel: "+nivel+"");



    }

    public void Jugar(View v){
        Intent ven=new Intent(this, MainActivity_ahjs.class);
        startActivity(ven);
    }

}