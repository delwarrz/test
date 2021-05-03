package info.delwarhossain.bjitacademyhometask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.delwarhossain.bjitacademyhometask.Model.InstallmentItemModel;
import info.delwarhossain.bjitacademyhometask.R;

public class EmiInstallmentChartAdapter extends RecyclerView.Adapter<EmiInstallmentChartAdapter.EmiInstallmentChartViewHolder> {
    Context context;
    List<InstallmentItemModel> installmentList;

    public EmiInstallmentChartAdapter(Context context, List<InstallmentItemModel> installmentList) {
        this.context = context;
        this.installmentList = installmentList;
    }

    @NonNull
    @Override
    public EmiInstallmentChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.emi_installment_chart_item, parent, false);
        return new EmiInstallmentChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmiInstallmentChartViewHolder holder, int position) {
        if(installmentList != null && installmentList.size() > 0){
            InstallmentItemModel model = installmentList.get(position);
            holder.installmentSerialColumn.setText(Integer.toString(model.getSerialNumber()));
            holder.installmentDateColumn.setText(model.getInstallmentDate());
            holder.installmentInterestColumn.setText(Integer.toString(model.getInterestAmount()));
            holder.openingBalanceColumn.setText(Integer.toString(model.getOpeningBalance()));
            holder.principleAmountColumn.setText(Integer.toString(model.getPrincipleAmount()));
            holder.installmentAmountColumn.setText(Integer.toString(model.getInstallmentPayment()));
            holder.endingBalanceColumn.setText(Integer.toString(model.getEndingBalance()));
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return installmentList.size();
    }

    public class EmiInstallmentChartViewHolder extends RecyclerView.ViewHolder {
        public TextView installmentDateColumn, installmentSerialColumn, installmentInterestColumn, openingBalanceColumn, principleAmountColumn, installmentAmountColumn, endingBalanceColumn;
        public EmiInstallmentChartViewHolder(@NonNull View itemView) {
            super(itemView);
            installmentSerialColumn = itemView.findViewById(R.id.installmentSerialTxt);
            installmentDateColumn = itemView.findViewById(R.id.installmentDateTxt);
            installmentInterestColumn = itemView.findViewById(R.id.installmentInterestTxt);
            openingBalanceColumn = itemView.findViewById(R.id.openingBalanceTxt);
            principleAmountColumn = itemView.findViewById(R.id.principleAmountTxt);
            installmentAmountColumn = itemView.findViewById(R.id.installmentAmountTxt);
            endingBalanceColumn = itemView.findViewById(R.id.endingBalanceTxt);
        }
    }
}
