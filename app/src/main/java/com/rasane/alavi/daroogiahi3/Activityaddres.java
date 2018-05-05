package com.rasane.alavi.daroogiahi3;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amin on 04/03/2018.
 */

public class Activityaddres extends ActivityEnhanced {
    private Spinner shipping;
    private LinearLayout linearpardakht;
//    public Info.info;

    String Amount="";
    String Count="";
    String Title="";
//    String IdBasket="";
    String IdProduct="";
//    String Weight="";





    EditText email;
    EditText name;
    EditText number;
    EditText desc;
    EditText address;
    List<String> shippingmethod = new ArrayList<>();

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Amount = getIntent().getExtras().getString("Amount");
        Count = getIntent().getExtras().getString("Count");
        Title = getIntent().getExtras().getString("Title");
//        IdBasket = getIntent().getExtras().getString("IdBasket");
        IdProduct = getIntent().getExtras().getString("IdProduct");
//        Weight = getIntent().getExtras().getString("Weight");

        linearpardakht = (LinearLayout) findViewById(R.id.linearpardakht);
         name = (EditText) findViewById(R.id.edtName);
         number= (EditText) findViewById(R.id.edtnumber);
         email = (EditText) findViewById(R.id.edtemail);
         desc = (EditText) findViewById(R.id.edtdesc);
         address= (EditText) findViewById(R.id.edtaddress);
        shipping = (Spinner) findViewById(R.id.shipping);


        final List<String> shipping_list = new ArrayList<>();
        shipping_list.add(getString(R.string.choose_shipping));

        shipping_list.add("پرداخت آنلاین");
//        shipping_list.addAll(shipping);

        // Initialize and set Adapter
        ArrayAdapter adapter_shipping = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shipping_list.toArray());
        adapter_shipping.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shipping.setAdapter(adapter_shipping);

        progressDialog = new ProgressDialog(Activityaddres.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(R.string.title_please_wait);
        progressDialog.setMessage(getString(R.string.content_submit_checkout));

        findViewById(R.id.linearpay22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                submitForm();

            }});

        }
        private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private void delaySubmitOrderData() {
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (shippingmethod.equals("پرداخت در محل")) {
//                    submitOrderData();
                    Toast.makeText(G.context, "پرداخت در محل",  Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();

                } else if(shippingmethod.equals("پرداخت آنلاین")){


                    Toast.makeText(G.context, "پرداخت مستقیم",  Toast.LENGTH_SHORT).show();
//                    pay();
                    progressDialog.cancel();
                }

            }
        }, 2000);
    }
    private boolean validateEmail() {
        String str = email.getText().toString().trim();
        if (str.isEmpty() || !G.isValidEmail(str)) {
            email.setError(getString(R.string.invalid_email));
            requestFocus(email);
            return false;
        } else {
//            Toast.makeText(G.context, "ادرس ایمیل اشتباه هست",  Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    private boolean validateName() {
        String str = name.getText().toString().trim();
        if (str.isEmpty()) {
            name.setError(getString(R.string.invalid_name));
            requestFocus(name);
            return false;
        } else {
//            Toast.makeText(G.context, "نام اشتباه هست",  Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private boolean validatePhone() {
        String str = number.getText().toString().trim();
        if (str.isEmpty()) {
            number.setError(getString(R.string.invalid_phone));
            requestFocus(number);
            return false;
        } else {
//            Toast.makeText(G.context, R.string.invalid_phone,  Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    private boolean validateAddress() {
        String str = address.getText().toString().trim();
        if (str.isEmpty()) {
            address.setError(getString(R.string.invalid_address));
            requestFocus(address);
            return false;
        } else {
//            Toast.makeText(G.context, R.string.invalid_address,  Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    private boolean validateShipping() {
        int pos = shipping.getSelectedItemPosition();
        if (pos == 0) {
            return false;
        }
        return true;
    }
    private void submitForm() {
        if (!validateName()) {
            Snackbar.make(linearpardakht , R.string.invalid_name, Snackbar.LENGTH_SHORT).show();
            return ;
        }
        if (!validatePhone()) {
            Snackbar.make(linearpardakht, R.string.invalid_phone, Snackbar.LENGTH_SHORT).show();
            return ;
        }
        if (!validateEmail()) {
            Snackbar.make(linearpardakht, R.string.invalid_email, Snackbar.LENGTH_SHORT).show();
            return ;
        }
        if (!validateAddress()) {
            Snackbar.make(linearpardakht, R.string.invalid_address, Snackbar.LENGTH_SHORT).show();
            return ;
        }
        if (!validateShipping()) {
            Snackbar.make(linearpardakht, R.string.invalid_shipping, Snackbar.LENGTH_SHORT).show();
            return ;
        }

        hideKeyboard();

        // show dialog confirmation
        dialogConfirmCheckout();

    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void dialogConfirmCheckout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmation);
        builder.setMessage(getString(R.string.confirm_checkout));
        builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//               delaySubmitOrderData();
                progressDialog.cancel();



//                Intent intent = new Intent(G.context,Payment.class);
//                intent.putExtra("Amount",et_amount.getText().toString());
//                intent.putExtra("Description",et_desc.getText().toString());
//                intent.putExtra("Email",et_email.getText().toString());
//                intent.putExtra("Mobile",et_mobile.getText().toString());
//                intent.putExtra("Mahsol",et_mahsol.getText().toString());
//                startActivity(intent);


            }
        });
        builder.setNegativeButton(R.string.NO, null);
        builder.show();
    }
}