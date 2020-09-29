package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);
        setTitle("Agregar tarea");

        Button button = findViewById(R.id.buttonNuevaTarea);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                EditText inputText= findViewById(R.id.inputTarea);
                String textTarea = inputText.getText().toString();
                intent.putExtra("nombreTarea",textTarea);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }


}