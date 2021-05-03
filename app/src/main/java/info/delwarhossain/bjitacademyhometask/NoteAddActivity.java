package info.delwarhossain.bjitacademyhometask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import info.delwarhossain.bjitacademyhometask.Datatabse.BaseDatabaseHelper;
import info.delwarhossain.bjitacademyhometask.Model.NoteModel;

public class NoteAddActivity extends BaseActivity {
    private Context context;

    private EditText titleInput, detailsInput;
    private BaseDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_note_add, frameLayout);
        context = this;

        menuTitleText.setText("Add Note");
        navigationView.setCheckedItem(R.id.nav_home);

        db = new BaseDatabaseHelper(this);
    }

    public void noteSubmitAction(View view){
        titleInput = findViewById(R.id.note_title_input);
        detailsInput = findViewById(R.id.note_details_input);

        if(TextUtils.isEmpty(titleInput.getText().toString().trim())  || TextUtils.isEmpty(detailsInput.getText().toString().trim())){
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show();
        }else{
            NoteModel note = new NoteModel(titleInput.getText().toString().trim(), detailsInput.getText().toString().trim());
            db.insertNote(note);
            Intent intent = new Intent(NoteAddActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}