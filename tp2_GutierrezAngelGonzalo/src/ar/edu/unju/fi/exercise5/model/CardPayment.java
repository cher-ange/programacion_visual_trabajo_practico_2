package ar.edu.unju.fi.exercise5.model;

import ar.edu.unju.fi.exercise5.interfaces.Payment;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CardPayment implements Payment {
    private static final Integer RECHARGE = 15;
    private String cardNumber;
    private LocalDate paymentDate = LocalDate.now();
    private Double amountPaid = 0.0;

    public CardPayment() {
    }

    public CardPayment(String cardNumber, LocalDate paymentDate, Double amountPaid) {
        this.cardNumber = cardNumber;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public void makePayment(double amount) {
        this.amountPaid += amount;
        this.amountPaid += this.amountPaid * RECHARGE / 100;
    }

    @Override
    public void printRecipe() {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es", "AR"));
        numberFormat.setMaximumFractionDigits(2);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        System.out.printf("""
                        ---- Recibo ----
                        Número de tarjeta: %s
                        Fecha de pago: %s
                        Monto pagado: $ %s
                        """,
                cardNumber,
                paymentDate.format(dateTimeFormatter),
                numberFormat.format(amountPaid)
                );
    }
}
