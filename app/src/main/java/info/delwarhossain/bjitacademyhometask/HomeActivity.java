package info.delwarhossain.bjitacademyhometask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import info.delwarhossain.bjitacademyhometask.Adapter.NoteAdapter;
import info.delwarhossain.bjitacademyhometask.Datatabse.BaseDatabaseHelper;
import info.delwarhossain.bjitacademyhometask.Model.NoteModel;

public class HomeActivity extends BaseActivity {
    private FloatingActionButton noteAddButton;

    private BaseDatabaseHelper db;

    private ArrayList<NoteModel> notes;

    private NoteAdapter noteAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_home, frameLayout);
        menuTitleText.setText("Important Notes");
        navigationView.setCheckedItem(R.id.nav_home);

        db = new BaseDatabaseHelper(this);
        notes = db.getAllNotes();


        noteAdapter = new NoteAdapter(this,  notes);

        recyclerView = findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);
    }

    public void noteAddButtonAction(View view){
        Intent intent = new Intent(HomeActivity.this, NoteAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            ExitApp();
        }
    }

    private void ExitApp() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.app_name);
        builder.setMessage("Do You Want To Exit?");
        builder.setIcon(R.drawable.single_logo);
        //final AlertDialog dialog = builder.create();
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
