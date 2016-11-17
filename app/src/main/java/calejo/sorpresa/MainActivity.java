package calejo.sorpresa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity  {
    String rCorrecta;
    int cont =0;
    PreguntasWebService pregunta;
    RadioButton rR1;
    RadioButton rR2;
    RadioButton rR3;
    RadioButton rR4;
    int contadorCorrecto = 0;
    int contadorIncorrecto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int heigth = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(heigth*.8));
        pregunta = new PreguntasWebService();
        pregunta.obtenerPregunta();
        this.setTextInicio(pregunta);
        rCorrecta = pregunta.respuesta_correcta;

    }
    void setTextInicio(PreguntasWebService pregunta){
        String preguntaA = pregunta.preguntaM;
        if (preguntaA == null || preguntaA.equals("")) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        String r1 = pregunta.respuesta1;
        String r2 = pregunta.respuesta2;
        String r3 = pregunta.respuesta3;
        String r4 = pregunta.respuesta4;
        TextView tPregunta =  (TextView)findViewById(R.id.tPregunta);
        RadioButton rR1 = (RadioButton)findViewById(R.id.rR1);
        RadioButton rR2 = (RadioButton)findViewById(R.id.rR2);
        RadioButton rR3 = (RadioButton)findViewById(R.id.rR3);
        RadioButton rR4 = (RadioButton)findViewById(R.id.rR4);
        tPregunta.setText(preguntaA);
        rR1.setText(r1);
        rR2.setText(r2);
        rR3.setText(r3);
        rR4.setText(r4);
    }

    void setText(PreguntasWebService pregunta){
        String preguntaA = pregunta.preguntaM;
        String r1 = pregunta.respuesta1;
        String r2 = pregunta.respuesta2;
        String r3 = pregunta.respuesta3;
        String r4 = pregunta.respuesta4;
        TextView tPregunta =  (TextView)findViewById(R.id.tPregunta);
        rR1 = (RadioButton)findViewById(R.id.rR1);
        rR2 = (RadioButton)findViewById(R.id.rR2);
        rR3 = (RadioButton)findViewById(R.id.rR3);
        rR4 = (RadioButton)findViewById(R.id.rR4);
        tPregunta.setText(preguntaA);
        rR1.setText(r1);
        rR2.setText(r2);
        rR3.setText(r3);
        rR4.setText(r4);
    }

    public void siguiente(View view){

            pregunta = new PreguntasWebService();
            pregunta.obtenerPregunta();
            this.setText(pregunta);
            //rCorrecta = pregunta.respuesta_correcta;
            if((rR1.isChecked() && rCorrecta.equals("a")) || (rR2.isChecked() && rCorrecta.equals("b"))  || (rR3.isChecked() && rCorrecta.equals("c")) || (rR4.isChecked() && rCorrecta.equals("d")) ){
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Respuesta Correcta");
                dlgAlert.setTitle("Preparate EGEL");
                //dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        salir();
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                contadorCorrecto++;
            }else {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Respuesta Incorrecta");
                dlgAlert.setTitle("Preparate EGEL");
                //dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        salir();
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                contadorIncorrecto++;

            }

    }

    public void salir() {
        finish();
        System.exit(0);
    }
}

