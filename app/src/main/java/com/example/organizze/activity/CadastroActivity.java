    package com.example.organizze.activity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;
import com.example.organizze.config.ConfiguracaoFirebase;
import com.example.organizze.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);

        botaoCadastrar.setOnClickListener(v -> {
            String textoNome = campoNome.getText().toString();
            String textoEmail = campoEmail.getText().toString();
            String textoSenha = campoSenha.getText().toString();

            if (!textoNome.isEmpty()) {
                if(!textoEmail.isEmpty()){
                    if(!textoSenha.isEmpty()){

                        usuario = new Usuario();
                        usuario.setNome(textoNome);
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        cadastrarUsuario();

                    }else{
                        Toast.makeText(CadastroActivity.this,
                                "Preecha a senha!",
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                }else{
                    Toast.makeText(CadastroActivity.this,
                            "Preecha o Email!",
                            Toast.LENGTH_SHORT)
                            .show();
                }


            }else{
                Toast.makeText(CadastroActivity.this,
                        "Preecha o nome!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Toast.makeText(CadastroActivity.this,
                        "Usu??rio Cadastrado",
                        Toast.LENGTH_SHORT)
                        .show();

            }else {

                String execao = "";
                try{
                    throw  task.getException();
                }catch (FirebaseAuthWeakPasswordException e){
                    execao ="Digite uma senha mais forte!";
                }catch (FirebaseAuthInvalidCredentialsException e){
                    execao ="E-mail inv??lido";
                }catch (FirebaseAuthUserCollisionException e){
                    execao ="E-mail j?? cadastrado";
                }catch (Exception e){
                    execao ="Erro ao cadastrar usu??rio";
                }


                Toast.makeText(CadastroActivity.this,
                        execao,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}