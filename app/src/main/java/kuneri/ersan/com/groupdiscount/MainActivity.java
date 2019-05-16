package kuneri.ersan.com.groupdiscount;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonCreateStudent = findViewById(R.id.buttonCreateStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new SearchButtonListener());
        countRecords();
        readRecords();

    }
    public void countRecords() {
        int recordCount = new TableControllerProduct(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }
    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();


        List<ObjectProduct> productss = new TableControllerProduct(this).read();

        if (productss.size() > 0) {

            for (ObjectProduct obj : productss) {

                View view = new View(MainActivity.this);
                final Context context = view.getRootView().getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                final View productView = inflater.inflate(R.layout.product, null, false);


                int id = obj.id;
                String productN = obj.name;
                String productP = obj.price.toString();
                String productC = obj.count.toString();


                final TextView productName =  productView.findViewById(R.id.from_name);
                //productName.setPadding(50, 10, 0, 10);
                productName.setText(productN);
                productName.setTag(Integer.toString(id));
                productName.setOnLongClickListener(new OnLongClickListenerStudentRecord());

                final TextView productPrice =  productView.findViewById(R.id.plist_price_text);
                //productName.setPadding(50, 10, 0, 10);
                productPrice.setText(productP + "$");
                productPrice.setTag(Integer.toString(id));
                productPrice.setOnLongClickListener(new OnLongClickListenerStudentRecord());

                final TextView productCount =  productView.findViewById(R.id.cart_product_quantity_tv);
                //productName.setPadding(50, 10, 0, 10);
                productCount.setText(productC);
                productCount.setTag(Integer.toString(id));
                productCount.setOnLongClickListener(new OnLongClickListenerStudentRecord());


                /*TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(50, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());*/


                linearLayoutRecords.addView(productView);

                /*ImageView img = new ImageView(this);
                img.setImageResource(R.drawable.ic_basket);
                img.setPadding(0,10,0,10);

                linearLayoutRecords.addView(img);*/
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }

    /*public void searchRecords(){
        View view = new View(MainActivity.this);
        final Context context = view.getRootView().getContext();

        final EditText searchedProduct = (EditText) findViewById(R.id.searchForm);
        String productN = searchedProduct.getText().toString();

        final ObjectProduct obj = new TableControllerProduct(context).search(productN);

        if(obj == null){
            searchedProduct.setText("No Result");
        }else {
            final CharSequence[] items = {"Edit", "Delete"};

            new AlertDialog.Builder(context).setTitle("Product Record")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                final TableControllerProduct tableControllerProduct = new TableControllerProduct(context);
                                ObjectProduct objectProduct = tableControllerProduct.readSingleRecord(obj.id);
                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);
                                final EditText productName = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
                                final EditText productPrice = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);
                                final EditText productCount = (EditText) formElementsView.findViewById(R.id.editTextStudentCount);
                                productName.setText(objectProduct.name);
                                productPrice.setText(objectProduct.price.toString());
                                productCount.setText(objectProduct.count.toString());
                                new AlertDialog.Builder(context)
                                        .setView(formElementsView)
                                        .setTitle("Edit Record")
                                        .setPositiveButton("Save Changes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        ObjectProduct objectProduct = new ObjectProduct();
                                                        objectProduct.id = obj.id;
                                                        objectProduct.name = productName.getText().toString();
                                                        objectProduct.price = Double.parseDouble(productPrice.getText().toString());
                                                        objectProduct.count = Integer.parseInt(productCount.getText().toString());
                                                        boolean updateSuccessful = tableControllerProduct.update(objectProduct);

                                                        if(updateSuccessful){
                                                            Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                                        }
                                                        ((MainActivity) context).countRecords();
                                                        ((MainActivity) context).readRecords();
                                                        dialog.cancel();
                                                    }

                                                }).show();

                            }
                            else if (item == 1) {

                                boolean deleteSuccessful = new TableControllerProduct(context).delete(obj.id);

                                if (deleteSuccessful) {
                                    Toast.makeText(context, "Product record was deleted.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Unable to delete product record.", Toast.LENGTH_SHORT).show();
                                }

                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();

                            }
                            dialog.dismiss();

                        }
                    }).show();


        }
    }*/

}
