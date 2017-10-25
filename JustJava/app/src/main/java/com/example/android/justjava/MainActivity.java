package com.example.android.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import static com.example.android.justjava.R.id.quantity_text_view;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    double price = 0;
    int pricePerCup = 5;
    double whippedCreamPrice = 0;
    double chocolateToppingPrice = 0;
    String orderMessage;
    boolean checkWhippedCream;
    String checkWhippedCreamText;
    boolean checkChocolateTopping;
    String checkChocolateToppingText;
    String nameFieldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        isWhippedCream();
        isChocolateTopping();
        isName();
        calculatePrice();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL,"p.menown@hotmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order from " + nameFieldText);
            intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }



    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private double calculatePrice() {
        price = quantity * (pricePerCup + whippedCreamPrice + chocolateToppingPrice);
        return price;
    }

    /**
     * Creates the order summary
     *
     * '@param the price of the order
     * @return order summary
     */
    private String createOrderSummary(){
        DecimalFormat precision = new DecimalFormat(".00");
        orderMessage = "Name: " + nameFieldText;
        orderMessage += "\nAdd whipped cream? " + checkWhippedCreamText;
        orderMessage += "\nAdd chocolate topping? " + checkChocolateToppingText;
        orderMessage += "\nQuantity: " + quantity;
        orderMessage += "\nTotal: £" + precision.format(price);
        orderMessage += "\nThank you!";
        return orderMessage;
    }


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    private boolean isWhippedCream() {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.check_Box_1);
        checkWhippedCream = checkBox1.isChecked();
        if (checkWhippedCream) {
            whippedCreamPrice = .53;
            checkWhippedCreamText = "Yes";
        } else {
            whippedCreamPrice = 0;
            checkWhippedCreamText = "No";
        }
        return checkWhippedCream;
    }

    private boolean isChocolateTopping() {
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.check_Box_2);
        checkChocolateTopping = checkBox2.isChecked();
        if (checkChocolateTopping) {
            chocolateToppingPrice = .75;
            checkChocolateToppingText = "Yes";
        } else {
            chocolateToppingPrice = 0;
            checkChocolateToppingText = "No";
        }
        return checkWhippedCream;
    }

    private String isName() {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        nameFieldText = nameField.getText().toString();
        return nameFieldText;
    }

}