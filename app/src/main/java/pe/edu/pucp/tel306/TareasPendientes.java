package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class TareasPendientes extends AppCompatActivity {

    private int contadorImg = 0;
    private ArrayList<String> tareas = null;

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

        /**AQUI DEBO OBTENER LAS TAREAS*/
        tareas = new ArrayList<>();
        tareas.add("tarea1 hola");
        tareas.add("tarea2 chau");

        int select = 2;
        switch (select) {
            case 1:
                imgteleco.setVisibility(View.VISIBLE);

                break;
            case 2:
                imgelect.setVisibility(View.VISIBLE);

                break;
            case 3:
                imgmeca.setVisibility(View.VISIBLE);

                break;
        }
        if (tareas.isEmpty()) {
            TextView notask = findViewById(R.id.noTask);
            notask.setVisibility(View.VISIBLE);
        } else {
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
                    Toast.makeText(TareasPendientes.this, "Has desbloqueado la opción oscura", Toast.LENGTH_SHORT).show();
                } else if (contadorImg == 10) {
                    Toast.makeText(TareasPendientes.this, "Has restaurado el modo claro", Toast.LENGTH_SHORT).show();
                    contadorImg = 0;
                }
            }
        });
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