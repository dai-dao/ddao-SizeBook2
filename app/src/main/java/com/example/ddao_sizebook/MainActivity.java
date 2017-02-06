package com.example.ddao_sizebook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ddao_sizebook.db.ShopperDbHelper;
import com.example.ddao_sizebook.db.ShopperInformation;
import com.example.ddao_sizebook.db.TaskContract;
import com.example.ddao_sizebook.db.TaskDbHelper;

import java.sql.Date;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ShopperDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private ShopperAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        mTaskListView.setAdapter(sAdapter);
        mHelper = new ShopperDbHelper(this);

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private ArrayList<Shopper> GetShopperData(){
        ArrayList<Shopper> results = new ArrayList<Shopper>();

        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor =db.query(ShopperInformation.ShopperEntry.TABLE,
                new String[]{   ShopperInformation.ShopperEntry._ID,
                                ShopperInformation.ShopperEntry.Name,
                                ShopperInformation.ShopperEntry.Date,
                                ShopperInformation.ShopperEntry.Bust,
                                ShopperInformation.ShopperEntry.Chest,
                                ShopperInformation.ShopperEntry.Hip,
                                ShopperInformation.ShopperEntry.Inseam,
                                ShopperInformation.ShopperEntry.Waist,
                                ShopperInformation.ShopperEntry.Comment
                },
                null, null, null, null, null);

        Shopper shopper;
        while (cursor.moveToNext()) {

            shopper = new Shopper();

            int idx_name = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Name);
            int idx_date = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Date);
            int idx_bust = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Bust);
            int idx_chest = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Chest);
            int idx_hip = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Hip);
            int idx_inseam = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Inseam);
            int idx_waist = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Waist);
            int idx_comment = cursor.getColumnIndex(ShopperInformation.ShopperEntry.Comment);

            shopper.setName(cursor.getString(idx_name));
            shopper.setDate(cursor.getString(idx_date));
            shopper.setBust(Integer.parseInt(cursor.getString(idx_bust)));
            shopper.setChest(Integer.parseInt(cursor.getString(idx_chest)));
            shopper.setHip(Integer.parseInt(cursor.getString(idx_hip)));
            shopper.setInseam(Integer.parseInt(cursor.getString(idx_inseam)));
            shopper.setWaist(Integer.parseInt(cursor.getString(idx_waist)));
            shopper.setComment(cursor.getString(idx_comment));

            results.add(shopper);
        }

        cursor.close();
        db.close();

        return results;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                createEntryDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createEntryDialog(){
        /* Add multiple text field in add box */
        /* http://stackoverflow.com/questions/12876624/multiple-edittext-objects-in-alertdialog */
        //text_entry is an Layout XML file containing two text field to display in alert dialog

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText shopperNameView = new EditText(this);
        shopperNameView.setInputType(InputType.TYPE_CLASS_TEXT);
        shopperNameView.setHint("Name");
        layout.addView(shopperNameView);

        final EditText shopperDateView = new EditText(this);
        shopperDateView.setInputType(InputType.TYPE_CLASS_TEXT);
        shopperDateView.setHint("Date (mm-dd-yyyy)");
        layout.addView(shopperDateView);

        final EditText shopperNeckView = new EditText(this);
        shopperNeckView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperNeckView .setHint("Neck");
        layout.addView(shopperNeckView);

        final EditText shopperBustView = new EditText(this);
        shopperBustView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperBustView.setHint("Bust");
        layout.addView(shopperBustView );

        final EditText shopperChestView = new EditText(this);
        shopperChestView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperChestView.setHint("Chest");
        layout.addView(shopperChestView);

        final EditText shopperWaistView = new EditText(this);
        shopperWaistView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperWaistView.setHint("Waist");
        layout.addView(shopperWaistView);

        final EditText shopperHipView = new EditText(this);
        shopperHipView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperHipView.setHint("Hip");
        layout.addView(shopperHipView);

        final EditText shopperInseamView  = new EditText(this);
        shopperInseamView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperInseamView.setHint("Inseam");
        layout.addView(shopperInseamView);

        final EditText shopperCommentView  = new EditText(this);
        shopperCommentView.setInputType(InputType.TYPE_CLASS_TEXT);
        shopperCommentView.setHint("Comment");
        layout.addView(shopperCommentView);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new Shopper")
                .setMessage("What do you want to do next?")
                // .setView(taskEditText)
                .setView(layout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Put value into database
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.put(ShopperInformation.ShopperEntry.Name, String.valueOf(shopperNameView.getText()));
                        values.put(ShopperInformation.ShopperEntry.Date, String.valueOf(shopperDateView.getText()));
                        values.put(ShopperInformation.ShopperEntry.Neck, Integer.valueOf(shopperNeckView.getText().toString()));
                        values.put(ShopperInformation.ShopperEntry.Bust, Integer.valueOf(shopperBustView.getText().toString()));
                        values.put(ShopperInformation.ShopperEntry.Chest, Integer.valueOf(shopperChestView.getText().toString()));
                        values.put(ShopperInformation.ShopperEntry.Waist, Integer.valueOf(shopperWaistView.getText().toString()));
                        values.put(ShopperInformation.ShopperEntry.Hip, Integer.valueOf(shopperHipView.getText().toString()));
                        values.put(ShopperInformation.ShopperEntry.Inseam, Integer.valueOf(shopperInseamView.getText().toString()));
                        values.put(ShopperInformation.ShopperEntry.Comment, shopperCommentView.getText().toString());

                        db.insertWithOnConflict(ShopperInformation.ShopperEntry.TABLE,
                                null,
                                values,
                                SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private void updateUI() {
        ArrayList<Shopper> Shoppers = GetShopperData();

        if (sAdapter == null) {
            sAdapter = new ShopperAdapter(this, Shoppers);
            mTaskListView.setAdapter(sAdapter);
        } else {
            sAdapter.clear();
            sAdapter.addAll(Shoppers);
            sAdapter.notifyDataSetChanged();
        }
    }

    // Need to re-write delete
    public void deleteShopper(View view) {
        View parent = (View) view.getParent();
        TextView shopperTextView = (TextView) parent.findViewById(R.id.rowName);
        String shopperName = String.valueOf(shopperTextView.getText());
        shopperName = shopperName.split(":")[1].trim();

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(ShopperInformation.ShopperEntry.TABLE,
                  ShopperInformation.ShopperEntry.Name + " = ?",
                new String[]{shopperName});
        db.close();
        updateUI();
    }
}
