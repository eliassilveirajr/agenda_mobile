package app.melhoroftheworld.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.melhoroftheworld.agenda.nota.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new MainFragment()).commit();
        }
    }
}