package com.example.student7.kucharzenie;

        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.student7.kucharzenie.data.EmailAndPassword;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.data.User;

        import org.androidannotations.annotations.AfterViews;
        import org.androidannotations.annotations.Bean;
        import org.androidannotations.annotations.Click;
        import org.androidannotations.annotations.EActivity;
        import org.androidannotations.annotations.Extra;
        import org.androidannotations.annotations.NonConfigurationInstance;
        import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class Login extends ActionBarActivity {
    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    @ViewById
    EditText email;
    @ViewById
    EditText password;

    ProgressDialog ringProgressDialog;

    //Bundle
//    @Extra
//    Bundle bundle;
//    @Extra
//    Recipe recipe;
    @Extra
    User user;

    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);
    }

    @Click
    void loginClicked(){
        ringProgressDialog.show();
        EmailAndPassword emailAndPassword = new EmailAndPassword();
        emailAndPassword.email = email.getText().toString(); //example@example.com
        emailAndPassword.password = password.getText().toString(); //test00
        restLoginBackgroundTask.login(emailAndPassword);
    }

    public void loginSuccess(User user){
        ringProgressDialog.dismiss();
        Glowna_.intent(this).user(user).start();
        Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Nie bangla, spróbuj jeszcze raz\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
}