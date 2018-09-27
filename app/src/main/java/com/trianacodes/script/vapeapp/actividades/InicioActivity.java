package com.trianacodes.script.vapeapp.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.trianacodes.script.vapeapp.R;

public class InicioActivity extends AppCompatActivity {

    public ImageButton calculadora, aromas, almacen, recetas, gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        calculadora = findViewById(R.id.ibCalculadora);
        aromas = findViewById(R.id.ibAromas);
        almacen = findViewById(R.id.ibAlmacen);
        recetas = findViewById(R.id.ibRecetas);
        gastos = findViewById(R.id.ibGastos);

        lanzaActividades();
        /*calculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });*/

        /*almacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

        recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

        gastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });*/

    }

    public void lanzaActividades(){

        aromas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent llamaAromas = new Intent(getApplicationContext(), GeneralActivity.class);
                startActivity(llamaAromas);

            }

        });

    }
}
