package com.example.ing_richardavid.seccion_07_sqlite.models.implementions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ing_richardavid.seccion_07_sqlite.models.DAO.CustomerDAO;
import com.example.ing_richardavid.seccion_07_sqlite.models.databases.sqlite.SQLiteDatabaseConnection;
import com.example.ing_richardavid.seccion_07_sqlite.models.databases.sqlite.SQLiteStructDatabase;
import com.example.ing_richardavid.seccion_07_sqlite.models.entities.Customer;
import com.example.ing_richardavid.seccion_07_sqlite.utils.Functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ing_richardavid on 29-08-17.
 */

public class CustomerImplementationDAO implements CustomerDAO {

    /**
     * Objects, variables and constants.
     */

    private static CustomerImplementationDAO INSTANCE;
    private static SQLiteDatabaseConnection sqLiteDatabaseConnection;
    private ContentValues contentValues;
    private Cursor cursor;

    /**
     * Builder.
     */

    private CustomerImplementationDAO() {}

    public static CustomerImplementationDAO getInstance(Context context) {
        sqLiteDatabaseConnection = SQLiteDatabaseConnection.getInstance(context);

        if (INSTANCE == null) {
            INSTANCE = new CustomerImplementationDAO();
        }

        return INSTANCE;
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        if (this.selectCustomer(customer) == null) {
            sqLiteDatabaseConnection.openDB();

            this.contentValues = new ContentValues();
            this.contentValues.put(SQLiteStructDatabase.CUSTOMER_FIELD_DNI, customer.getDni());
            this.contentValues.put(SQLiteStructDatabase.CUSTOMER_FIELD_NAME, customer.getName());
            this.contentValues.put(SQLiteStructDatabase.CUSTOMER_FIELD_DATE_CREATION, customer.getDate_creation());

            sqLiteDatabaseConnection.getSqLiteDatabase().insert(
                    SQLiteStructDatabase.CUSTOMER_TABLE_NAME,
                    null,
                    this.contentValues);

            sqLiteDatabaseConnection.closeDB();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        if (this.selectCustomer(customer) != null) {
            sqLiteDatabaseConnection.openDB();

            this.contentValues = new ContentValues();
            this.contentValues.put(SQLiteStructDatabase.CUSTOMER_FIELD_NAME, customer.getName());

            sqLiteDatabaseConnection.getSqLiteDatabase().update(
                    SQLiteStructDatabase.CUSTOMER_TABLE_NAME,
                    this.contentValues,
                    SQLiteStructDatabase.CUSTOMER_FIELD_DNI + "=" + customer.getDni(),
                    null
            );

            sqLiteDatabaseConnection.closeDB();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer selectCustomer(Customer customer) {
        Customer customerFound = null;

        sqLiteDatabaseConnection.openDB();

        this.cursor = sqLiteDatabaseConnection.getSqLiteDatabase().rawQuery("SELECT *FROM CUSTOMER WHERE dni=" + customer.getDni(), null);

        if (this.cursor != null && this.cursor.getCount() > 0) {
            while (this.cursor.moveToNext()){
                customerFound = new Customer(this.cursor.getInt(0), cursor.getString(1));
            }
        }

        sqLiteDatabaseConnection.closeDB();

        return customerFound;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        if (this.selectCustomer(customer) != null) {
            sqLiteDatabaseConnection.openDB();

            sqLiteDatabaseConnection.getSqLiteDatabase().delete(
                    SQLiteStructDatabase.CUSTOMER_TABLE_NAME,
                    SQLiteStructDatabase.CUSTOMER_FIELD_DNI + "=" + String.valueOf(customer.getDni()),
                    null);

            sqLiteDatabaseConnection.closeDB();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<Customer>();

        sqLiteDatabaseConnection.openDB();

        this.cursor = sqLiteDatabaseConnection.getSqLiteDatabase().rawQuery("SELECT *FROM CUSTOMER;", null);

        if (this.cursor != null && this.cursor.getCount() > 0) {
            while (this.cursor.moveToNext()){
                customers.add(new Customer(
                        this.cursor.getInt(0),
                        this.cursor.getString(this.cursor.getColumnIndex(SQLiteStructDatabase.CUSTOMER_FIELD_NAME))));
            }
        }

        sqLiteDatabaseConnection.closeDB();

        return customers;
    }

}
