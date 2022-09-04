package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=2;
    boolean checked=false;
    String subject="Order Summary";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox checked_1= (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean check_1 =checked_1.isChecked();
        CheckBox checked_2= (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean check_2 =checked_2.isChecked();
        EditText word = (EditText) findViewById(R.id.Name);
        String value= word.getText().toString();
        int price= calculatePrice(check_1,check_2);
        String st=createOrderSummary(price,check_1,check_2,value);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
    intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
    intent.putExtra(Intent.EXTRA_TEXT, st);
    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
    }


    }
    private String createOrderSummary(int price,boolean hasWhippedCream,boolean hasChocolate,String stri){
       String str="Name:"+stri+"\nAdd whipped cream?"+hasWhippedCream+"\nAdd chocolate ?"+hasChocolate+"\nQuantity: "+quantity+"\nTotal:$"+price+"\nThank You!";
        return str;

    }
    private int calculatePrice( boolean addCream ,  boolean addChocolate)
    {
        int price = quantity * 5;
        if(addCream) {
            price= price+quantity*1;
        }
        if(addChocolate){
            price = price +quantity*2;
        }
        return price;
    }
    public void increment(View view) {
        if(quantity==10){
            Toast.makeText(this,"You cannot have more than 10 cups of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);

    }
    public void decrement(View view) {
        if(quantity==1) {
            Toast.makeText(this,"You cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */

}