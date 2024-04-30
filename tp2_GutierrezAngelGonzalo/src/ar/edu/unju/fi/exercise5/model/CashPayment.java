package ar.edu.unju.fi.exercise5.model;

import ar.edu.unju.fi.exercise5.interfaces.Payment;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CashPayment implements Payment {
    private static final Integer DISCOUNT = 10;
    private Double amountPaid = 0.0;
    private LocalDate paymentDate = LocalDate.now();

    public CashPayment() {
    }

    public CashPayment(Double amountPaid, LocalDate paymentDate) {
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public void makePayment(double amount) {
        this.amountPaid += amount;
        this.amountPaid -= this.amountPaid * DISCOUNT / 100;
    }

    @Override
    public void printRecipe() {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es", "AR"));
        numberFormat.setMaximumFractionDigits(2);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        System.out.printf("""
                        ---- Recibo ----
                        Fecha de pago: %s
                        Monto pagado: $ %s
                        """,
                paymentDate.format(dateTimeFormatter),
                numberFormat.format(amountPaid)
        );
    }
}
