package com.example.ddao_sizebook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ShopperDbHelper mHelper;
    private ListView mShopperListView;
    private ArrayAdapter<String> mAdapter;
    private ShopperAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShopperListView = (ListView) findViewById(R.id.list_todo);
        mShopperListView.setAdapter(sAdapter);
        mHelper = new ShopperDbHelper(this);

        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private ArrayList<Shopper> GetShopperData(String shopperName){
        ArrayList<Shopper> results = new ArrayList<Shopper>();

        String querySelection;
        String[] queryArgs;

        if (shopperName == null) {
            querySelection = null;
            queryArgs = null;
        } else {
            querySelection = ShopperInformation.ShopperEntry.Name + " = ?";
            queryArgs = new String[]{shopperName};
        }

        SQLiteDatabase db = mHelper.getReadableDatabase();

        // Cursor cursor = db.rawQuery("SELECT * FROM " + ShopperInformation.ShopperEntry.TABLE + " WHERE name = ?", queryArgs);

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
                querySelection, queryArgs, null, null, null);

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
            shopper.setBust(cursor.getString(idx_bust));
            shopper.setChest(cursor.getString(idx_chest));
            shopper.setHip(cursor.getString(idx_hip));
            shopper.setInseam(cursor.getString(idx_inseam));
            shopper.setWaist(cursor.getString(idx_waist));
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
            case R.id.action_add_shopper:
                createEntryDialog(null);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createEntryDialog(Shopper existingShopper){
        /* Add multiple text field in add box */
        /* http://stackoverflow.com/questions/12876624/multiple-edittext-objects-in-alertdialog */
        //text_entry is an Layout XML file containing two text field to display in alert dialog

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText shopperNameView = new EditText(this);
        final EditText shopperDateView = new EditText(this);
        final EditText shopperNeckView = new EditText(this);
        final EditText shopperBustView = new EditText(this);
        final EditText shopperChestView = new EditText(this);
        final EditText shopperWaistView = new EditText(this);
        final EditText shopperHipView = new EditText(this);
        final EditText shopperInseamView  = new EditText(this);
        final EditText shopperCommentView  = new EditText(this);

        shopperNameView.setInputType(InputType.TYPE_CLASS_TEXT);
        shopperDateView.setInputType(InputType.TYPE_CLASS_TEXT);
        shopperNeckView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperBustView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperChestView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperWaistView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperHipView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperInseamView.setInputType(InputType.TYPE_CLASS_NUMBER);
        shopperCommentView.setInputType(InputType.TYPE_CLASS_TEXT);

        LinearLayout NameLayout = new LinearLayout(this);
        LinearLayout DateLayout = new LinearLayout(this);
        LinearLayout NeckLayout = new LinearLayout(this);
        LinearLayout BustLayout = new LinearLayout(this);
        LinearLayout ChestLayout = new LinearLayout(this);
        LinearLayout WaistLayout = new LinearLayout(this);
        LinearLayout HipLayout = new LinearLayout(this);
        LinearLayout InseamLayout = new LinearLayout(this);
        LinearLayout CommentLayout = new LinearLayout(this);

        final TextView NameLabel = new TextView(this);
        final TextView DateLabel = new TextView(this);
        final TextView NeckLabel = new TextView(this);
        final TextView BustLabel = new TextView(this);
        final TextView ChestLabel = new TextView(this);
        final TextView WaistLabel = new TextView(this);
        final TextView HipLabel = new TextView(this);
        final TextView InseamLabel = new TextView(this);
        final TextView CommentLabel = new TextView(this);

        String action = "Add";
        if (existingShopper != null) {
            shopperNameView.setText(existingShopper.getName());
            shopperDateView.setText(existingShopper.getDate());
            shopperNeckView.setText(String.valueOf(existingShopper.getNeck()));
            shopperBustView.setText(String.valueOf(existingShopper.getBust()));
            shopperChestView.setText(String.valueOf(existingShopper.getChest()));
            shopperWaistView.setText(String.valueOf(existingShopper.getWaist()));
            shopperHipView.setText(String.valueOf(existingShopper.getHip()));
            shopperInseamView.setText(String.valueOf(existingShopper.getInseam()));
            shopperCommentView.setText(existingShopper.getComment());
            action = "Edit";
        }

        NameLabel.setText("Name: ");
        NameLayout.addView(NameLabel);
        NameLayout.addView(shopperNameView);

        DateLabel.setText("Date (mm-dd-yyyy): ");
        DateLayout.addView(DateLabel);
        DateLayout.addView(shopperDateView);

        NeckLabel.setText("Neck: ");
        NeckLayout.addView(NeckLabel);
        NeckLayout.addView(shopperNeckView);

        BustLabel.setText("Bust: ");
        BustLayout.addView(BustLabel);
        BustLayout.addView(shopperBustView);

        ChestLabel.setText("Chest: ");
        ChestLayout.addView(ChestLabel);
        ChestLayout.addView(shopperChestView);

        WaistLabel.setText("Waist: ");
        WaistLayout.addView(WaistLabel);
        WaistLayout.addView(shopperWaistView);

        HipLabel.setText("Hip: ");
        HipLayout.addView(HipLabel);
        HipLayout.addView(shopperHipView);

        InseamLabel.setText("Inseam: ");
        InseamLayout.addView(InseamLabel);
        InseamLayout.addView(shopperInseamView);

        CommentLabel.setText("Comment: ");
        CommentLayout.addView(CommentLabel);
        CommentLayout.addView(shopperCommentView);

        layout.addView(NameLayout);
        //layout.addView(shopperNameView);
        layout.addView(DateLayout);
        layout.addView(NeckLayout);
        layout.addView(BustLayout);
        layout.addView(ChestLayout);
        layout.addView(WaistLayout);
        layout.addView(HipLayout);
        layout.addView(InseamLayout);
        layout.addView(CommentLayout);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(layout)
                .setPositiveButton(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Put value into database
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.put(ShopperInformation.ShopperEntry.Name, String.valueOf(shopperNameView.getText()));
                        values.put(ShopperInformation.ShopperEntry.Date, String.valueOf(shopperDateView.getText()));
                        values.put(ShopperInformation.ShopperEntry.Neck, shopperNeckView.getText().toString());
                        values.put(ShopperInformation.ShopperEntry.Bust, shopperBustView.getText().toString());
                        values.put(ShopperInformation.ShopperEntry.Chest, shopperChestView.getText().toString());
                        values.put(ShopperInformation.ShopperEntry.Waist, shopperWaistView.getText().toString());
                        values.put(ShopperInformation.ShopperEntry.Hip, shopperHipView.getText().toString());
                        values.put(ShopperInformation.ShopperEntry.Inseam, shopperInseamView.getText().toString());
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

    public void editShopper(View view) {
        View parent = (View) view.getParent().getParent();
        TextView shopperTextView = (TextView) parent.findViewById(R.id.rowName);
        String shopperName = String.valueOf(shopperTextView.getText());
        shopperName = shopperName.split(":")[1].trim();

        // Fetch from database this shopper
        ArrayList<Shopper> editedShopper = GetShopperData(shopperName);
        Shopper existingShopper = editedShopper.get(0);

        // Delete from database
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(ShopperInformation.ShopperEntry.TABLE,
                ShopperInformation.ShopperEntry.Name + " = ?",
                new String[]{shopperName});
        db.close();

        // Add to database edited information
        createEntryDialog(existingShopper);
    }

    private void updateUI() {
        ArrayList<Shopper> Shoppers = GetShopperData(null);

        if (sAdapter == null) {
            sAdapter = new ShopperAdapter(this, Shoppers);
            mShopperListView.setAdapter(sAdapter);
        } else {
            sAdapter.clear();
            sAdapter.addAll(Shoppers);
            sAdapter.notifyDataSetChanged();
        }

        // Set number of records in the view
        TextView num_record = (TextView) this.findViewById(R.id.num_record);
        num_record.setText(String.valueOf(Shoppers.size()));
    }

    // Need to re-write delete
    public void deleteShopper(View view) {
        View parent = (View) view.getParent().getParent();
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
