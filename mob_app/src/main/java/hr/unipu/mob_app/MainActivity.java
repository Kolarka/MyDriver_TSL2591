package hr.unipu.mob_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.beggining );


        if(findViewById(R.id.FrameContainer) != null) {
            if(savedInstanceState != null) {
                return;
            }
            Main prvi = new Main();
            getSupportFragmentManager().beginTransaction().add(R.id.FrameContainer, prvi).commit();
        }
    }
}
