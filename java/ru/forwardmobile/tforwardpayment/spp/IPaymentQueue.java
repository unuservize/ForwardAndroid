package ru.forwardmobile.tforwardpayment.spp;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collection;

import ru.forwardmobile.tforwardpayment.network.HttpTransport;

public interface IPaymentQueue extends Runnable {

    public void start() throws Exception;
    public void stop();
    public boolean isActive();
    public void processPayment(IPayment payment) throws Exception;

    public Collection<IPayment> getActivePayments();
    public Collection<IPayment> getStoredPayments();

    /** @deprecated */
    public Collection<IPayment> getActivePaymentsCopy();
    /** @deprecated */
    public Collection<IPayment> getStoredPaymentsCopy();

    public int getActivePaymentsCount();
    public int getStoredPaymentsCount();

    public void cancelPayment(IPayment payment) throws Exception;
    public void deletePayment(IPayment payment) throws Exception;
    public void repeatPayment(IPayment payment) throws Exception;
    public void startPayment(IPayment payment) throws Exception;

    public int hasActivePayments();

    public void setTransport(HttpTransport transport);
    public void setDatabaseHelper(SQLiteOpenHelper helper);
}