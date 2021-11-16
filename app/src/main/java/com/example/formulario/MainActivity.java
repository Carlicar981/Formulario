package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Spinner seleccion;
    CheckBox seguro;
    private TextView mostrarPorcentaje;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button miBoton;
        String[]datos={"Posicion:","Delantero","Centrocampista","Defensa","Portero"};
        miBoton = (Button) findViewById(R.id.miBoton);
        miBoton.setOnClickListener(this);
        miBoton.setOnLongClickListener(this::onLongClick);
        seleccion =(Spinner) findViewById(R.id.seleccion);
        ArrayAdapter<String>adaptador=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,datos);
        seleccion.setAdapter(adaptador);
        seguro=(CheckBox)  findViewById(R.id.checkBox);

        mostrarPorcentaje = (TextView)findViewById(R.id.textView);
        mostrarPorcentaje.setText("14 años");
        // SeekBar
        seekBar = (SeekBar)findViewById(R.id.seekBar2);
        // Valor Inicial
        seekBar.setProgress(0);
        // Valot Final
        seekBar.setMax(4);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //hace un llamado a la perilla cuando se arrastra
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mostrarPorcentaje.setText((14+progress)+" años");
                    }
                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
        Button acerca =(Button) findViewById(R.id.button);
        acerca.setOnClickListener(this::acerca);

    }

    @Override
    public void onClick(View v) {
        TextView entradaNombre;
        TextView entradaNombre2;
        TextView entradaNumero;
        TextView salidaNombre;
        String selector=seleccion.getSelectedItem().toString();
        String posicion = "";
        if (selector.equals("Delantero")){
                posicion="Delantero";
        }else if (selector.equals("Centrocampista")){
            posicion="Centrocampista";
        }else if (selector.equals("Defensa")){
            posicion="Defensa";
        }else if (selector.equals("Portero")){
            posicion="Portero";
        }
        String pagoSeguro;
        if (seguro.isChecked()) {
           pagoSeguro="Si";
        }else{
            pagoSeguro="No";
        }

        entradaNombre = (TextView) findViewById(R.id.entradaTexto);
        entradaNombre2 = (TextView) findViewById(R.id.entradaTexto2);


        salidaNombre = (TextView) findViewById(R.id.salidaTexto);
        salidaNombre.setText("Nombre: " + entradaNombre.getText()+
                "\nApellidos: " + entradaNombre2.getText()+
                "\nPosicion: "+posicion+"\nPaga seguro: "+pagoSeguro+"\nEdad: "+mostrarPorcentaje.getText());

    }
    public boolean onLongClick(View v) {
        TextView entradaNombre;
        TextView salidaNombre;
        entradaNombre = (TextView) findViewById(R.id.entradaTexto);
        salidaNombre = (TextView) findViewById(R.id.salidaTexto);
        salidaNombre.setText("Hola que tal " + entradaNombre.getText());
        return true;
    }
    public void acerca(View view) {
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }
    public void llamar112(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:112"));
        startActivity(intent);
    }
    public void enviarMensaje(View view){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Este es el texto de prueba que quiero enviar");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

}