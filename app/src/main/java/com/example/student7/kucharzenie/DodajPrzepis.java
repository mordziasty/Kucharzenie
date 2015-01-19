package com.example.student7.kucharzenie;

        import android.support.v7.app.ActionBarActivity;
        import android.widget.EditText;
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

@EActivity(R.layout.activity_addprzepis)
public class DodajPrzepis extends ActionBarActivity {
    //passed user
    @Extra
    User user;

    //background tasks
    @Bean
    @NonConfigurationInstance
    RestBackgroundDodajPrzepis restBackgroundDodajPrzepis;

    //region EditTexts
    @ViewById
    EditText title;
    @ViewById
    EditText introduction;
    @ViewById
    EditText ingredients;
    @ViewById
    EditText steps;
    @ViewById
    EditText servings;
    @ViewById
    EditText preparationMinutes;
    @ViewById
    EditText cookingMinutes;
    //endregion


    @AfterViews
    void init() {

    }


    @Click
    void addrecipeClicked(){
        //check for required fields

        if(StringUtils.hasText(title.getText().toString()) | StringUtils.hasText(ingredients.getText().toString()) |
                StringUtils.hasText(steps.getText().toString()) | StringUtils.hasText(servings.getText().toString())){

            //new class
            Przepis recipe = new Przepis();
            recipe.title = title.getText().toString();
            recipe.introduction = introduction.getText().toString();
            recipe.ingredients = ingredients.getText().toString();
            recipe.steps = steps.getText().toString();
            recipe.servings = servings.getText().toString();
            recipe.preparationMinutes = preparationMinutes.getText().toString();
            recipe.cookingMinutes = cookingMinutes.getText().toString();
            //owner id
            recipe.ownerId = user.id;

            //POST operation
            restBackgroundDodajPrzepis.addRecipe(recipe,user);

            //go back to MainView intention and pass user instance
            Glowna_.intent(this).user(user).start();
        } else{
            Toast.makeText(this,"Wypełnij pola!",Toast.LENGTH_LONG).show();
        }
    }


    public void showSuccess(){
        Toast.makeText(this,"Udało się!",Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        Toast.makeText(this, "Nie bangla, próbuj dalej\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}