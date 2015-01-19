package com.example.student7.kucharzenie;

        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.student7.kucharzenie.adapter.ListaPrzepisowAdapter;
        import com.example.student7.kucharzenie.data.Kucharzenie;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.data.User;

        import org.androidannotations.annotations.AfterViews;
        import org.androidannotations.annotations.Bean;
        import org.androidannotations.annotations.Click;
        import org.androidannotations.annotations.EActivity;
        import org.androidannotations.annotations.Extra;
        import org.androidannotations.annotations.ItemClick;
        import org.androidannotations.annotations.NonConfigurationInstance;
        import org.androidannotations.annotations.OptionsMenu;
        import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_lista)
@OptionsMenu(R.menu.login)
public class Glowna extends ActionBarActivity {

    @Extra
    User user;

    @ViewById
    ListView list;
    @Bean
    ListaPrzepisowAdapter adapter;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init(){
        list.setAdapter(adapter);
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładuje się...");
        ringProgressDialog.setIndeterminate(true);
//        Toast.makeText(this,user.sessionId,Toast.LENGTH_LONG).show();
    }

    @ItemClick
    void listItemClicked(Przepis recipe){
//        Toast.makeText(this, item.title,Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe",recipe);
        bundle.putSerializable("user",user);

        SelectedView_.intent(this).bundle(bundle).start();
    }

    @Click
    void refreshClicked(){
        ringProgressDialog.show();
        restBackgroundTask.getKucharzenie();
    }

    @Click
    void addClicked(){
        //check if user is logged in
        if(user == null) {
            Login_.intent(this).user(user).start();
        } else {
            //go to commentview activity with packed bundle
            DodajPrzepis_.intent(this).user(user).start();
        }
    }


    public void updateKucharzenie(Kucharzenie kucharzenie){
        ringProgressDialog.dismiss();
        adapter.update(kucharzenie);
    }

    public void showError(Exception e){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Nie bangla, spróbuj jeszcze raz\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }

//    @OptionsItem
//    void settingsSelected(){
//
//    }



    //region On... methods - DO NOT DELETE THIS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
    //##############################################
}