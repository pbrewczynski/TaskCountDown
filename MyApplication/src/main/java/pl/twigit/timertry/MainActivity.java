package pl.twigit.timertry;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Button   startButton;
    private EditText taskNameEditText;
    private EditText hoursEditText;
    private EditText minutesEditText;
    private EditText secondsEditText;

    private int      counter;
    private static final String TAG = "TWIGIT";

    List<Task> taskList = new ArrayList<Task>();

    private TaskListAdapter taskListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(MainActivity.TAG, "Name of the UI thread ? :" + Thread.currentThread().getName() );


        this.startButton      = (Button)   this.findViewById(R.id.start_button);
        this.taskNameEditText = (EditText) this.findViewById(R.id.task_name_edit_text);
        this.hoursEditText    = (EditText) this.findViewById(R.id.hours_edit_text);
        this.minutesEditText  = (EditText) this.findViewById(R.id.minutes_edit_text);
        this.secondsEditText  = (EditText) this.findViewById(R.id.seconds_edit_text);


        ListView lv = (ListView)this.findViewById(R.id.task_list_view);
        taskListAdapter = new TaskListAdapter();
        lv.setAdapter(taskListAdapter);

        this.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = MainActivity.this.taskNameEditText.getText().toString();
                long endTime    = MainActivity.this.getEndTimeFromEditTexts();

                MainActivity.this.taskList.add(new Task(taskName, endTime));
                taskCount += 1;

                taskListAdapter.notifyDataSetChanged();
                Log.v(MainActivity.TAG, "click");

                //timerHandler.postDelayed(MainActivity.this.timerRunnable, 1000);
            }
        });
    }
    private int getIntFromEditText (EditText et) throws NumberFormatException  {
        return Integer.parseInt(et.getText().toString());
    }

    private long getEndTimeFromEditTexts () {
        int seconds = this.getIntFromEditText(this.secondsEditText);
        int minutes = this.getIntFromEditText(this.minutesEditText);
        int hours   = this.getIntFromEditText(this.hoursEditText);

        long endTimeMs = System.currentTimeMillis() + (seconds * 1000) + (minutes * 1000 * 60) + (hours * 1000 * 60 * 60);

        return endTimeMs;
    }



    private int taskCount = 0;

    private class TaskListAdapter extends BaseAdapter {
        public int getCount() {
            return MainActivity.this.taskList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Log.v(MainActivity.TAG, "getView executed");

            LayoutInflater layoutInflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View taskRoot = layoutInflater.inflate(R.layout.one_task, null);
            ((TextView) taskRoot.findViewById(R.id.seconds_edit_text)).setText("00");
            ((TextView) taskRoot.findViewById(R.id.minutes_edit_text)).setText("00");
            ((TextView) taskRoot.findViewById(R.id.hours_edit_text)).setText("00");
            return taskRoot;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


  /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        */

//this.targetDate = new GregorianCalendar(2023,11,5,23,0,0);  // on video is just diffent date


