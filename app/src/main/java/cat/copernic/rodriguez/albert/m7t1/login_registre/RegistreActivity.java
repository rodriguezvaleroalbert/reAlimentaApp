package cat.copernic.rodriguez.albert.m7t1.login_registre;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import cat.copernic.rodriguez.albert.m7t1.Nav;
import cat.copernic.rodriguez.albert.m7t1.R;


public class RegistreActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        mFullName   = findViewById(R.id.fullName);
        mEmail      = findViewById(R.id.Email);
        mPassword   = findViewById(R.id.password);
        mPhone      = findViewById(R.id.phone);
        mRegisterBtn= findViewById(R.id.btnRegistrar);
        mLoginBtn   = findViewById(R.id.btnLogin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Nav.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullName = mFullName.getText().toString();
                final String phone    = mPhone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email es necesario.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password es necesaria.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password tiene que ser >= 6 Caracteres");
                    return;
                }



                // registerar usuario en firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistreActivity.this, "Usuario creado.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: El perfil del usuario es creado "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Nav.class));

                        }else {
                            Toast.makeText(RegistreActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



       /*mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(RegistreActivity.this,LoginActivity.class));
            }
        }); */

    }
}
