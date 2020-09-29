package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
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

        setTitle("Registrarse");

        if(savedInstanceState != null) {
            String nombreTexto = savedInstanceState.getString("nombre");
            String apellidoTexto = savedInstanceState.getString("apellido");
            String dniTexto = savedInstanceState.getString("dni");
            String codigoTexto = savedInstanceState.getString("codigo");
            String passwordTexto = savedInstanceState.getString("password");

            if(nombreTexto != null) {
                TextView editTextNombre = findViewById(R.id.editTextNombre);
                editTextNombre.setText(nombreTexto);
            }
            if(apellidoTexto != null) {
                TextView editTextApellido = findViewById(R.id.editTextApellido);
                editTextApellido.setText(apellidoTexto);
            }
            if(dniTexto != null) {
                TextView editTextDni = findViewById(R.id.editTextDni);
                editTextDni.setText(dniTexto);
            }
            if(codigoTexto != null) {
                TextView editTextCodigo = findViewById(R.id.editTextCodigo);
                editTextCodigo.setText(codigoTexto);
            }
            if(passwordTexto != null) {
                TextView editTextPassword = findViewById(R.id.editTextPassword);
                editTextPassword.setText(passwordTexto);
            }
        }

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
                    TextView textViewErrorDni = findViewById(R.id.textViewErrorDni);
                    textViewErrorDni.setVisibility(View.VISIBLE);
                }
                String[] añoCod = codigo.split("\\\\",8);
                String part1 = añoCod[0];
                int codigonum = Integer.valueOf(part1);
                if(codigo.isEmpty()) {
                    editTextCodigo.setError("No puede estar vacío");
                } else if(codigonum < 2012 || codigonum > 2017) {
                    TextView textViewErrorCodigo = findViewById(R.id.textViewErrorCodigo);
                    textViewErrorCodigo.setVisibility(View.VISIBLE);
                }
                if(password.isEmpty()) {
                    editTextPassword.setError("No puede estar vacío");
                }

                TextView textViewOpcionCarrera = findViewById(R.id.textViewOpcionCarrera);
                if(radioButtonTeleco.isChecked()) {
                    textViewOpcionCarrera.setText("Telecomunicaciones");
                } else if (radioButtonElectro.isChecked()) {
                    textViewOpcionCarrera.setText("Electronica");
                } else if (radioButtonMeca.isChecked()) {
                    textViewOpcionCarrera.setText("Mecatronica");
                }
                String textoCarrera = textViewOpcionCarrera.getText().toString();

//S3cr3t306
                if(!nombre.isEmpty() && !apellido.isEmpty() && !dni.isEmpty() && !codigo.isEmpty() && !password.isEmpty()
                        && codigo.length() == 8 && password.equals("S3cr3t306")) {
                    Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroActivity.this, TareasPendientes.class);

                    intent.putExtra("persona", new Persona(nombre,apellido,textoCarrera));
                    startActivity(intent);
                    finish();
                } else {
                    TextView textViewErrorPassword = findViewById(R.id.textViewErrorPassword);
                    textViewErrorPassword.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView editTextNombre = findViewById(R.id.editTextNombre);
        TextView editTextApellido = findViewById(R.id.editTextApellido);
        TextView editTextDni = findViewById(R.id.editTextDni);
        TextView editTextCodigo = findViewById(R.id.editTextCodigo);
        TextView editTextPassword = findViewById(R.id.editTextPassword);

        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String dni = editTextDni.getText().toString();
        String codigo = editTextCodigo.getText().toString();
        String password = editTextPassword.getText().toString();

        outState.putString("nombre", nombre);
        outState.putString("nombre", apellido);
        outState.putString("dni", dni);
        outState.putString("codigo", codigo);
        outState.putString("password", password);
    }
}