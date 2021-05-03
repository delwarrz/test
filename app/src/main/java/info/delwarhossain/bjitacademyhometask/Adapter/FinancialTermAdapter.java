package info.delwarhossain.bjitacademyhometask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.delwarhossain.bjitacademyhometask.Model.FinancialTermModel;
import info.delwarhossain.bjitacademyhometask.R;

public class FinancialTermAdapter extends RecyclerView.Adapter<FinancialTermAdapter.ViewHolder> {
    Context context;
    List<FinancialTermModel> termsList;

    public FinancialTermAdapter(Context context, List<FinancialTermModel> termsList) {
        this.context = context;
        this.termsList = termsList;
    }

    @NonNull
    @Override
    public FinancialTermAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_financial_term, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialTermAdapter.ViewHolder holder, int position) {
        FinancialTermModel model = termsList.get(position);
        holder.termItemTitle.setText(model.getTitle());
        holder.termItemDetails.setText(model.getDetails());
        holder.expandableContent.setVisibility(model.isExpanded()?View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return termsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemHeader, expandableContent;
        public TextView termItemTitle, termItemDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            termItemTitle = itemView.findViewById(R.id.financial_term_item_title);
            termItemDetails = itemView.findViewById(R.id.financial_term_item_details);
            itemHeader = itemView.findViewById(R.id.itemHeader);
            expandableContent = itemView.findViewById(R.id.expandableContent);

            itemHeader.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    FinancialTermModel term = termsList.get(getAdapterPosition());
                    term.setExpanded(!term.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
