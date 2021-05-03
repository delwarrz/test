package info.delwarhossain.bjitacademyhometask.Model;

public class InstallmentItemModel {
    String installmentDate;
    int serialNumber, interestAmount, openingBalance, principleAmount, installmentPayment, endingBalance;

    public InstallmentItemModel(String installmentDate, int serialNumber, int interestAmount, int openingBalance, int principleAmount, int installmentPayment, int endingBalance) {
        this.installmentDate = installmentDate;
        this.serialNumber = serialNumber;
        this.interestAmount = interestAmount;
        this.openingBalance = openingBalance;
        this.principleAmount = principleAmount;
        this.installmentPayment = installmentPayment;
        this.endingBalance = endingBalance;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(String installmentDate) {
        this.installmentDate = installmentDate;
    }

    public int getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(int interestAmount) {
        this.interestAmount = interestAmount;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(int openingBalance) {
        this.openingBalance = openingBalance;
    }

    public int getPrincipleAmount() {
        return principleAmount;
    }

    public void setPrincipleAmount(int principleAmount) {
        this.principleAmount = principleAmount;
    }

    public int getInstallmentPayment() {
        return installmentPayment;
    }

    public void setInstallmentPayment(int installmentPayment) {
        this.installmentPayment = installmentPayment;
    }

    public int getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(int endingBalance) {
        this.endingBalance = endingBalance;
    }
}
