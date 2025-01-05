package com.example.calculette;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    // Déclaration des composants
    protected Button btnraz, btncalc, btnquit;
    protected RadioButton radioPlus, radioMoins, radioMultiplié, radioDivisé;
    protected EditText edtop1, edtop2;
    protected TextView rslt;
    private Snackbar sbb; // Snackbar variable declared here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation de la vue principale
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initializing Snackbar here
        sbb = Snackbar.make(findViewById(R.id.main), "Erreur division par 0", Snackbar.LENGTH_LONG);

        // Gérer les marges pour Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des composants
        btnraz = findViewById(R.id.RAZButton);
        btncalc = findViewById(R.id.CalculButton);
        btnquit = findViewById(R.id.QuitterButton);
        radioPlus = findViewById(R.id.radioButtonPlus);
        radioMoins = findViewById(R.id.radioButtonMoins);
        radioMultiplié = findViewById(R.id.radioButtonMultiplié);
        radioDivisé = findViewById(R.id.radioButtonDivisé);
        edtop1 = findViewById(R.id.op1);
        edtop2 = findViewById(R.id.op2);
        rslt = findViewById(R.id.rslt);

        //interface qui permet de définir ce qui se passe lorsqu'un utilisateur clique sur un composant
        //attacher un écouteur (Listener) à un composant de type View (ici, un bouton).
        //La méthode setOnClickListener() :
        //Elle attend un objet qui implémente l'interface View.OnClickListener.
        //Cette interface définit la méthode onClick(View v), que tu dois implémenter pour spécifier ce qui se passe lorsqu'un clic se produit.
        //Le new View.OnClickListener() :
        //Ici, on crée un objet anonyme de type View.OnClickListener. Cela veut dire qu'on n'a pas besoin de créer une nouvelle classe séparée qui implémente l'interface OnClickListener.
        //Au lieu de cela, on crée directement l'objet avec un bloc de code qui définit la méthode onClick().
        //La méthode onClick() :
        //L'interface OnClickListener te demande de redéfinir cette méthode, et c'est ce que nous faisons dans ce bloc de code.
        //L'annotation @Override indique que nous redéfinissons la méthode onClick() de l'interface OnClickListener et que le code dans onClick() est exécuté chaque fois que l'utilisateur clique sur l'élément auquel le OnClickListener est attaché.
        btnraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtop1.setText("0");
                edtop2.setText("0");
            }
        });

        // Listener for the calculate button
        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer();
            }
        });
    }

    private void calculer() {
        // Get the values from EditText fields
        String op1 = edtop1.getText().toString();
        String op2 = edtop2.getText().toString();

        // Check if inputs are valid floats
        if (isFloat(op1) && isFloat(op2)) {
            if (radioPlus.isChecked()) {
                rslt.setText(String.valueOf(Float.parseFloat(op1) + Float.parseFloat(op2)));
            } else if (radioMoins.isChecked()) {
                rslt.setText(String.valueOf(Float.parseFloat(op1) - Float.parseFloat(op2)));
            } else if (radioMultiplié.isChecked()) {
                rslt.setText(String.valueOf(Float.parseFloat(op1) * Float.parseFloat(op2)));
            } else if (radioDivisé.isChecked()) {
                if (Float.parseFloat(op2) == 0) {
                    sbb.show(); // Show Snackbar on division by zero
                } else {
                    rslt.setText(String.valueOf(Float.parseFloat(op1) / Float.parseFloat(op2)));
                }
            }
        } else {
            sbb.setText("Op1 et Op2 doivent être des nombres"); // Set the error message for invalid input
            sbb.show();
        }
    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void quitter(View view) {
        this.finish();
    }
}
