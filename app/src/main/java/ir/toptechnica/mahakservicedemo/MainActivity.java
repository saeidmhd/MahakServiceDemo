package ir.toptechnica.mahakservicedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import ir.toptechnica.mahakservicedemo.model.Person;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private EditText edit_name;
    private EditText edit_age;
    private TextView textLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         edit_name = (EditText) findViewById(R.id.edit_name);
         edit_age = (EditText) findViewById(R.id.edit_age);
         textLog  = (TextView) findViewById(R.id.textLog);

        realm = Realm.getDefaultInstance();


    }



    public void save_click(View view) {
        
        //// TODO: 2/28/17 add validation for stuff 
        save_into_database(edit_name.getText().toString().trim(),Integer.parseInt(edit_age.getText().toString().trim()));
        refreshVi();


        


    }

    private void refreshVi() {
        // read from db show in Logtext
        // Or alternatively do the same all at once (the "Fluent interface"):
        RealmResults<Person> personResult = realm.where(Person.class)

                .findAll();
        String output = "";
        for (Person person : personResult) {
            output += person.toString();
        }

        textLog.setText(output);

    }

    private void save_into_database( final String name, final int age) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Person user = bgRealm.createObject(Person.class);
                user.setName(name);
                user.setAge(age);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
              Log.v("database",">>>>>>>stored ok<<<<<<<<<");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("database",error.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
