package es.fjaviles.testappfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private Button btnLogin, btnRegister;
    private EditText edtUser, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        String id = "1", name = "Fran";
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        edtUser = findViewById(R.id.username);
        edtPass = findViewById(R.id.password);

        setActions();

    }

    private void setActions() {

        btnLogin.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    edtUser.getText().toString(),edtPass.getText().toString()).
                    addOnCompleteListener(this, task -> {

                        if (task.isSuccessful()){
                            Intent goHome = new Intent(this,HomeActivity.class);
                            startActivity(goHome);
                        }

                    });

        });

        btnRegister.setOnClickListener(v -> {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    edtUser.getText().toString(),edtPass.getText().toString()).
                    addOnCompleteListener(this, task -> {

                        if (task.isSuccessful()){
                            Intent goHome = new Intent(this,HomeActivity.class);
                            startActivity(goHome);
                        }

                    });

        });


    }
}