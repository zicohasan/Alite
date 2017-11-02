package mp.zico.org.mqlitexy;

/**
 * Created by zico on 17/10/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class NewUser extends Activity{
    EditText userName;
    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_user);
        userName = (EditText) findViewById(R.id.userName);
    }

    /**
     * Called when Save button is clicked
     * @param view
     */
    public void addNewUser(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("userName", userName.getText().toString());
        if (userName.getText().toString() != null
                && userName.getText().toString().trim().length() != 0) {
            controller.insertUser(queryValues);
            this.callHomeActivity(view);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter User name",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Navigate to Home Screen
     * @param view
     */
    public void callHomeActivity(View view) {
        Intent objIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(objIntent);
    }

    /**
     * Called when Cancel button is clicked
     * @param view
     */
    public void cancelAddUser(View view) {
        this.callHomeActivity(view);
    }
}