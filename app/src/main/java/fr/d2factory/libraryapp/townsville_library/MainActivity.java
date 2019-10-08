package fr.d2factory.libraryapp.townsville_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    FloatingActionButton floatingActionButton;
    private String memberType = "MEMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        radioGroup = findViewById(R.id.chose_member);
        floatingActionButton = findViewById(R.id.fab_chose_member_type);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                if (selectedRadioButton == null) {
                    return;
                } else {
                    Intent toLibrary = new Intent(getApplication(), LibraryActivity.class);
                    toLibrary.putExtra(memberType,selectedRadioButton.getText().toString());
                    startActivity(toLibrary);
                }
            }
        });
    }
}
