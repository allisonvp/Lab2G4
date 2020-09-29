package pe.edu.pucp.tel306;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TareasPendientes extends AppCompatActivity {

    private int contadorImg = 0;
    private ArrayList<String> tareas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_pendientes);

        View imgteleco = findViewById(R.id.imageteleco);
        View imgelect = findViewById(R.id.imageelect);
        View imgmeca = findViewById(R.id.imagemeca);
        imgteleco.setVisibility(View.GONE);
        imgelect.setVisibility(View.GONE);
        imgmeca.setVisibility(View.GONE);
        final LinearLayout ll = findViewById(R.id.listaTareas);
        Intent intent = getIntent();
        Persona persona = (Persona) intent.getSerializableExtra("persona");
        if (persona != null) {
            setTitle("Tareas de " + persona.getNombreCompleto());
            TextView bienvenido = findViewById(R.id.textBienvenido);
            bienvenido.setText(String.valueOf("Bienvenidx, " + persona.getNombre()));
            switch (persona.getCarrera()) {
                case "Telecomunicaciones":
                    imgteleco.setVisibility(View.VISIBLE);

                    break;
                case "Electronica":
                    imgelect.setVisibility(View.VISIBLE);

                    break;
                case "Mecatronica":
                    imgmeca.setVisibility(View.VISIBLE);

                    break;
            }
        }

        TextView notask = findViewById(R.id.noTask);
        if (tareas.isEmpty()) {
            notask.setVisibility(View.VISIBLE);
        } else {
            notask.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);
            for (String tarea : tareas) {
                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setOnClickListener(new checkboxTareas());
                cb.setText(tarea);
                ll.addView(cb);
            }
        }

        LinearLayout grupoimagenes = findViewById(R.id.layoutimagen);
        grupoimagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorImg++;
                if (contadorImg == 5) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Toast.makeText(TareasPendientes.this, "Has desbloqueado la opción oscura", Toast.LENGTH_SHORT).show();
                } else if (contadorImg == 10) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(TareasPendientes.this, "Has restaurado el modo claro", Toast.LENGTH_SHORT).show();
                    contadorImg = 0;
                }
            }
        });

        Button btnsesion = findViewById(R.id.buttonNewSesion);
        btnsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TareasPendientes.this, RegistroActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button btntarea = findViewById(R.id.buttonAddTask);
        btntarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TareasPendientes.this, AgregarTareaActivity.class);
                intent.putExtra("tareas", tareas);

                int requestCode = 1;
                startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LinearLayout ll = findViewById(R.id.listaTareas);
        ll.removeAllViews();

        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<String> datafromact2 = (ArrayList<String>) data.getSerializableExtra("tareas");
            if (datafromact2 != null) {
                TextView notask = findViewById(R.id.noTask);
                notask.setVisibility(View.INVISIBLE);
                ll.setVisibility(View.VISIBLE);
                for (String tarea : datafromact2) {
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setOnClickListener(new checkboxTareas());
                    cb.setText(tarea);
                    ll.addView(cb);
                }
                tareas = datafromact2;
                Toast.makeText(this, "Nueva tarea agregada!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    class checkboxTareas implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            CheckBox cb = (CheckBox) view;
            if (cb.isChecked()) {
                String desc = cb.getText().toString();
                Toast.makeText(TareasPendientes.this, "Tarea '" + desc + "' finalizada", Toast.LENGTH_SHORT).show();
                cb.setVisibility(View.GONE);
                Iterator<String> taskIterator = tareas.iterator();
                while (taskIterator.hasNext()) {
                    String tarea = taskIterator.next();
                    if (desc.equals(tarea)) {
                        taskIterator.remove();
                    }
                }
                if (tareas.isEmpty()) {
                    TextView notask = findViewById(R.id.noTask);
                    notask.setVisibility(View.VISIBLE);
                }
            }

        }
    }
}