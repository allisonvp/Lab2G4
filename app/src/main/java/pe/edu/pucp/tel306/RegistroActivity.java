package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        setTitle("PucpToDoList");

        Button buttonIngresar = findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView editTextNombre = findViewById(R.id.editTextNombre);
                TextView editTextApellido = findViewById(R.id.editTextApellido);
                TextView editTextDni = findViewById(R.id.editTextDni);
                TextView editTextCodigo = findViewById(R.id.editTextCodigo);
                TextView editTextPassword = findViewById(R.id.editTextPassword);
                RadioButton radioButtonTeleco = findViewById(R.id.radioButtonTeleco);
                RadioButton radioButtonElectro = findViewById(R.id.radioButtonElectro);
                RadioButton radioButtonMeca = findViewById(R.id.radioButtonMeca);

                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String dni = editTextDni.getText().toString();
                String codigo = editTextCodigo.getText().toString();
                String password = editTextPassword.getText().toString();

                if(nombre.isEmpty()) {
                    editTextNombre.setError("No puede estar vacío");
                }
                if(apellido.isEmpty()) {
                    editTextApellido.setError("No puede estar vacío");
                }
                if(dni.isEmpty()) {
                    editTextDni.setError("No puede estar vacío");
                } else if(dni.length() < 8 || dni.length() > 8) {
                    TextView textViewErrorCodigo = findViewById(R.id.textViewErrorDni);
                    textViewErrorCodigo.setVisibility(View.VISIBLE);
                }
                if(codigo.isEmpty()) {
                    editTextCodigo.setError("No puede estar vacío");
                } else if(codigo.length() < 7) {
                    TextView textViewErrorCodigo = findViewById(R.id.textViewErrorDni);
                    textViewErrorCodigo.setVisibility(View.VISIBLE);
                }
                if(password.isEmpty()) {
                    editTextPassword.setError("No puede estar vacío");
                }


                if(!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !codigo.isEmpty() && !password.isEmpty()
                        && password.equals("S3cr3t306")) {
                    Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    TextView textViewErrorPassword = findViewById(R.id.textViewErrorPassword);
                    textViewErrorPassword.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}