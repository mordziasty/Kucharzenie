package com.example.student7.kucharzenie;

        import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student7.kucharzenie.data.Przepis;
import com.example.student7.kucharzenie.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;
import org.springframework.util.StringUtils;

@EActivity(R.layout.activity_addkoment)
public class Komentarz extends ActionBarActivity {
    @Extra
    Przepis recipe;
    @Extra
    User user;

    @Extra
    Bundle bundle;

    //process dialog
    ProgressDialog ringProgressDialog;

    //region background tasks
    @Bean
    @NonConfigurationInstance
    RestBackgroundDodajKoment restBackgroundDodajKoment;

    @ViewById
    EditText text;

    //region TextVievs
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
//endregion

    @AfterViews
    void init() {
        //process dialog
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładuje się...");
        ringProgressDialog.setIndeterminate(true);

        //unpack bundle
        recipe = (Przepis)bundle.getSerializable("recipe");
        user = (User)bundle.getSerializable("user");

        //set textviews
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
    }


    @Click
    void addcommentClicked(){
        //check if comment is null
        if (!StringUtils.hasText(text.getText().toString())) {
            Toast.makeText(this,"Wypełnij pola!",Toast.LENGTH_LONG).show();
        } else {
            //POST operation
            restBackgroundDodajKoment.addComment(user, recipe, text.getText().toString());

            //pass bundle to SelectedView activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("recipe", recipe);
            bundle.putSerializable("user", user);

            //go back to SelectedView
            SelectedView_.intent(this).bundle(bundle).start();
        }
    }


    public void showSuccess(){
        Toast.makeText(this,"Udało się!",Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        Toast.makeText(this, "Nie bangla, spróbuj jeszcze raz\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}