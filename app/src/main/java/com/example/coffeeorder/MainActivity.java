package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity {
    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        /**
         * This method is called when the order button is clicked.
         */
        public void submitOrder(View view) {
            //display(quantity);
            //displayPrice(quantity*5);
            EditText text=findViewById(R.id.userName);
            String name =text.getText().toString();

            Log.v("MainActivity", "Name:" + name);

            CheckBox whippedCreamCheckBox =findViewById(R.id.whippedCreamCheckBox);
            boolean hasWhippedCream=whippedCreamCheckBox.isChecked();

            CheckBox chocolateCheckbox=findViewById(R.id.chocolateCheckbox);
            boolean hasChocolate= chocolateCheckbox.isChecked();

            int price=calculatePrice(hasWhippedCream,hasChocolate);

            String priceMessage=createOrderSummary(name,price,hasWhippedCream,hasChocolate);

//            Intent intent=new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto"));
//            intent.putExtra(Intent.EXTRA_EMAIL,"ayushshrivastava675@gmail.com");
//            intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order for "+name);
//            startActivity(Intent.createChooser(intent,"Choose an email client"));

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for"+name);
            intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
            startActivity(Intent.createChooser(intent,"Choose an email client"));


            displayMessage(priceMessage);

        }

    /**
     * Order Summary total
     * @param price
     * @return priceMessage
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
            String priceMessage= "Name: "+name;
            priceMessage+="\nAdd Whipped Cream ?"+addWhippedCream;
            priceMessage+="\nAdd Chocolate ?"+addChocolate;
            priceMessage=priceMessage+"\nQuantity: "+quantity;
            priceMessage=priceMessage+ "\nTotal $"+price;
            priceMessage=priceMessage+"\nThank you!";
            return priceMessage;

        }

    /**
     * Calculates the price of the order.
     * return price
     */
    private int calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        //Base price of Coffee
        int basePrice=5;
        //If whipped cream is added then plus 1 Dollar
        if (addWhippedCream){
            basePrice+=1;
        }
        //If Chocolate id added then plus 2 dollar
        if (addChocolate){
            basePrice+=2;
        }
        return quantity * basePrice;
    }


        /**
         * This method displays the given quantity value on the screen.
         */
        private void displayQuantity(int numberOfCoffees) {
            TextView quantityTextView = findViewById(R.id.quantityDisplay);
            quantityTextView.setText("" + numberOfCoffees);
        }
    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.priceDisplay);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//when plus button is click
    public void Increment(View view) {
        if (quantity==100){
            Toast.makeText(this, "You cannot have more than 100 Cups of Coffee ", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity+=1;
        displayQuantity(quantity);
    }
    /**When minus button is click */
    public void Decrement(View view) {
        Toast.makeText(this, "You cannot have less than 1 Cup of Coffee", Toast.LENGTH_SHORT).show();
        if (quantity==1){
            return;
        }
        quantity-=1;
        displayQuantity(quantity);
    }
    /**Method Displays the given Text on screen*/
    private void displayMessage(String message){
        TextView OrderSummary =findViewById(R.id.orderSummary);
        OrderSummary.setText(message);

    }

}

