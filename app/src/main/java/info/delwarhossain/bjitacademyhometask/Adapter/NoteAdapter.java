package info.delwarhossain.bjitacademyhometask.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.delwarhossain.bjitacademyhometask.Datatabse.BaseDatabaseHelper;
import info.delwarhossain.bjitacademyhometask.Model.NoteModel;
import info.delwarhossain.bjitacademyhometask.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context context;
    private List<NoteModel> notes;

    public NoteAdapter(Context context, List<NoteModel> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        NoteModel model = notes.get(position);
        holder.noteItemViewTxt.setText(model.getTitle());
        holder.noteViewItemDetails.setText(model.getDetails());

        holder.note_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemDeleteConfirmation(model, position);
            }
        });

        holder.noteViewItemDetails.setVisibility(model.isExpanded()?View.VISIBLE:View.GONE);

    }

    private void itemDeleteConfirmation(NoteModel model, int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Delete Confirmation");
        builder.setMessage("Are you sure to delete?");
        builder.setIcon(R.drawable.ic_delete_forever_24);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseDatabaseHelper db = new BaseDatabaseHelper(context);
                db.deleteNote(model.getId());
                notes.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position, notes.size());
                notifyDataSetChanged();
                Toast.makeText(context, "Note item is deleted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteItemViewTxt, noteViewItemDetails;
        ImageView note_delete_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemViewTxt = itemView.findViewById(R.id.noteViewItemTxt);
            noteViewItemDetails = itemView.findViewById(R.id.noteViewItemDetails);
            note_delete_button = itemView.findViewById(R.id.note_delete_button);

            noteItemViewTxt.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    NoteModel note = notes.get(getAdapterPosition());
                    note.setExpanded(!note.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
