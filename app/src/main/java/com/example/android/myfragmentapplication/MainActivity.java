package com.example.android.myfragmentapplication;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//3rd determine whether or not the activity is in two-pane mode, i.e. running on a tablet.
/**
 * 1st create a normal application,
 * 2nd create an empty activity which will display de item details
 * 3rd create a fragment to show the item details content ( ItemDetailFragment )
 * 4th create 2 versions of the item_list (Android will find out which to use):
 *      one when the width of the device is greater than 900dp:
 *          file name: item_list
 *          Root element: LinearLayout
 *          Directory name: layout-w900dp
 *
 *          once generated include change the LinearLayout orientation to horizontal and
 *              add a textView, a button and a FrameLayout (id = item_detail_container) to determine
 *              if the device has at least 900dp width)
 *
 *      another one for the rest sizes
 *          file name: item_list
 *          Root element: ConstraintLayout
 *          Directory name: layout
 *
 *          once generated include a Text View and a button
 *
 * 5th modify your activity_main layout, include:
 *      A FrameLayout that will contain the item_list layout created in step 4 (remember we have 2,
 *      depending on the width of the device) Android will decide at runtime which layout is going
 *      to be rendered.
 *      Place the whole include inside the FrameLayout and replace the layout with: item_list
 *      do not forget to include app:layout_behavior="@string/appbar_scrolling_view_behavior" in your
 *      FrameLayout, this will make your component (The textView in the list) not to be overlapped
 *      by the appBar
 *
 * 6th implement the button logic to launch the itemDetailActivity and show info there or
 *      show the fragment and the info there. For that reason create the boolean attribute:
 *      isTwoPaneMode
 *
 * 7th code the fragment and the Activity to receive and show the data.
 *      for the Acivity, do not forget to modify the file activity_item_detail.xml to include the
 *      a FrameLayout with the id = item_detail_container
 *
 *
 *
 */


public class MainActivity extends AppCompatActivity {

    private boolean isTwoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        if(findViewById(R.id.item_detail_container) != null)
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            isTwoPaneMode = true;


        View textView = findViewById(R.id.textView);
        if (!(textView != null)) throw new AssertionError();

        Button button = findViewById(R.id.button);
        if(button != null){
//            Toast.makeText(this, "Button is NOT null", Toast.LENGTH_SHORT).show();
        }else{
//            Toast.makeText(this, "Button is null", Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetails();
            }
        });


    }

    private void viewDetails() {
        //create a person instance to pass
        Person person = new Person("Super", "Man", 65);
        if(isTwoPaneMode){
            Bundle arguments = new Bundle();
            arguments.putSerializable(Constants.PERSON_ID, person);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        }else{
            Intent intent = new Intent(this, ItemDetailActivity.class);
            intent.putExtra(Constants.PERSON_ID, person);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
