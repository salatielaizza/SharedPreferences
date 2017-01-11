package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameText;
    private Button buttonSave;
    private TextView textShowed;

    //criação de uma constante
    private static final String ARQUIVO_PREFERENCIA = "Arquivo Preferencial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.answerID);
        buttonSave = (Button) findViewById(R.id.buttonID);
        textShowed = (TextView) findViewById(R.id.showedTextID);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //o parâmetro 0 quer dizer que é um preferencia privada, apenas para este aplicativo
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if ( nameText.getText().toString().equals(" ")) {
                    Toast.makeText(MainActivity.this, "Please write some name", Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString("name", nameText.getText().toString() );
                    editor.commit();
                    textShowed.setText( "Favorite Language is: " + nameText.getText().toString() );

                }
            }
        });

        //Recuparar os dados salvos
        //Instanciar novamente o SharedPreferances
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        //verificar se existe uma chave chamada "name" no SharedPreference
        if (sharedPreferences.contains("name")){
            String userName = sharedPreferences.getString("name", "User not defined");
            textShowed.setText(userName);
        }else {
            textShowed.setText("Hello! User not defined");
        }
    }
}
