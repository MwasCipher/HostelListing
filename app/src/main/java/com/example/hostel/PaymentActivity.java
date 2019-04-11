package com.example.hostel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.util.TransactionType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    TextView hostelNameTV,successTV, successMessageTV ;
    EditText mpesaPhoneET, amountET;
    Button payButton;
    ArrayList<BookedSuccessful> bookedSuccessfulArrayList;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth;
    String hostel_name, hostel_amount;
    Hostel hostel;
    EditText studentNamePaymentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();

        bookedSuccessfulArrayList = new ArrayList<>();

        hostelNameTV = findViewById(R.id.hostelNameTextView);
        hostel = new Hostel();



        studentNamePaymentEditText = findViewById(R.id.payment_student_name_ET);
        mpesaPhoneET = findViewById(R.id.mpesaPhone);
        amountET = findViewById(R.id.Amount);

        payButton = findViewById(R.id.makePayment);

        Intent intent = getIntent();

        if (intent.hasExtra("hostelName" ) && intent.hasExtra("hostelPrice")){
            hostel_name = intent.getStringExtra("hostelName");
            hostel_amount = intent.getStringExtra("hostelPrice");

            hostelNameTV.setText(hostel_name);
            amountET.setText(hostel_amount);

        }


        final Daraja daraja = Daraja.with("wR3nLvFGpnRUd28EChtmffWcyQyiyQ5v", "M4ii61ybGSrqAjE5", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {

                Log.i(PaymentActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());


            }

            @Override
            public void onError(String error) {

                Log.e(PaymentActivity.this.getClass().getSimpleName(), error);

            }
        });



        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amountPaid = amountET.getText().toString().trim();
                String phoneNumber = mpesaPhoneET.getText().toString().trim();

                if (TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(amountPaid)) {

                    mpesaPhoneET.setError("Enter Phone");
                    amountET.setError("Enter Amount");
                }

                final LNMExpress lnmExpress = new LNMExpress(

                        "174379",
                        "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                        TransactionType.CustomerPayBillOnline,
                        amountPaid,
                        "254728748905",
                        "174379",
                        phoneNumber,
                        "http://mycallbackurl.com/checkout.php",
                        "001ABC",
                        "Goods Payment"
                );


                Toast.makeText(PaymentActivity.this, ""+lnmExpress.getAmount()+" by "+ lnmExpress.getPhoneNumber(), Toast.LENGTH_SHORT).show();

                daraja.requestMPESAExpress(lnmExpress, new DarajaListener<LNMResult>() {
                    @Override
                    public void onResult(@NonNull LNMResult lnmResult) {
                        Log.i(PaymentActivity.this.getClass().getSimpleName(), lnmResult.ResponseDescription);

                        String responseDescription = lnmResult.ResponseDescription;

                        Log.d("PAYMENT RESULT", "CODE: " + lnmResult.ResponseCode);
                        Log.d("PAYMENT RESULT", "DESC: " + lnmResult.ResponseDescription);
                        Log.d("PAYMENT RESULT", "ID: " + lnmResult.CheckoutRequestID);
                        Log.d("PAYMENT RESULT", "CM: " + lnmResult.CustomerMessage);
                        Log.d("PAYMENT RESULT", "M_ID: " + lnmResult.MerchantRequestID);

                        Log.d("PAYMENT RESULT", "Trans_Desc: " + lnmExpress.getTransactionDesc());
                        Log.d("PAYMENT RESULT", "call_back_url: " + lnmExpress.getCallBackURL());



                        if (lnmResult.ResponseCode.equals(String.valueOf(0))){

                            showCustomDialog();

                        }

                    }

                    @Override
                    public void onError(String error) {
                        Log.e(getApplicationContext().getClass().getSimpleName(), error);

                    }
                });



            }
        });
    }

    private void showCustomDialog() {

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        LayoutInflater layoutInflater = this.getLayoutInflater();

        View dialogView = layoutInflater.inflate(R.layout.alert_dialog, null);

        successTV = dialogView.findViewById(R.id.success_text_view);
        successMessageTV = dialogView.findViewById(R.id.success_message_text_view);
        Button okButton = dialogView.findViewById(R.id.buttonOk);

        alertDialog.setView(dialogView);
        alertDialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                uploadBookingDetails();

                Intent bookedRoomsActivity = new Intent(getApplicationContext(), BookedStudent.class);
                bookedRoomsActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bookedRoomsActivity);
            }
        });
    }

    private void uploadBookingDetails() {

        databaseReference = firebaseDatabase.getReference("Booked");

        String studentName, hostelName, registrationNumber, hostelAmount, id, hostelOwnerId, phoneNumber;


        studentName = studentNamePaymentEditText.getText().toString().trim();

        hostelName = hostelNameTV.getText().toString();
        phoneNumber = mpesaPhoneET.getText().toString().trim();
        registrationNumber = new NewUser().getnStudentRegistration();

        hostelAmount = amountET.getText().toString();
        id = firebaseAuth.getCurrentUser().getUid();
        hostelOwnerId = hostel.getHostelOwnerId();

        BookedSuccessful bookedSuccessful = new BookedSuccessful(id,studentName, phoneNumber,hostelName,
                hostelOwnerId, hostelAmount);
        assert id != null;
        databaseReference.child(id).setValue(bookedSuccessful);


    }


}
