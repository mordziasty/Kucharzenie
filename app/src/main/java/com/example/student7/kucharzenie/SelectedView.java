package com.example.student7.kucharzenie;

        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.student7.kucharzenie.adapter.ListaKomentarzyAdapter;
        import com.example.student7.kucharzenie.data.ListaKomentarzy;
        import com.example.student7.kucharzenie.data.ListaLike;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.data.User;

        import org.androidannotations.annotations.AfterViews;
        import org.androidannotations.annotations.Bean;
        import org.androidannotations.annotations.Click;
        import org.androidannotations.annotations.EActivity;
        import org.androidannotations.annotations.Extra;
        import org.androidannotations.annotations.NonConfigurationInstance;
        import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_thatrecepta)
public class SelectedView extends ActionBarActivity {
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
    RestBackgroundKomentarz restBackgroundKomentarz;

    @Bean
    @NonConfigurationInstance
    RestBackgroundLike restBackgroundLike;
    //endregion

    @Bean
    ListaKomentarzyAdapter adapter;

    //region list with comments
    @ViewById
    ListView listComment;
    //endregion

    //buttons
    @ViewById
    Button comment;
    @ViewById
    Button like;

    //region TextVievs
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    @ViewById
    TextView ingredients;
    @ViewById
    TextView created;
    @ViewById
    TextView preparationMinutes;
    @ViewById
    TextView cookingMinutes;
    @ViewById
    TextView servings;
    @ViewById
    TextView likes;
//endregion

    @AfterViews
    void init() {
        listComment.setAdapter(adapter);

        //unpack bundle
        recipe = (Przepis)bundle.getSerializable("recipe");
        user = (User)bundle.getSerializable("user");

        //process dialog
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładuje się...");
        ringProgressDialog.setIndeterminate(true);

        //for loading comments
        ringProgressDialog.show();

        //set textviews
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
        ingredients.setText(recipe.ingredients);
        created.setText(recipe.created);
        preparationMinutes.setText(recipe.preparationMinutes);
        cookingMinutes.setText(recipe.cookingMinutes);
        servings.setText(recipe.servings);


        //fill comments and likes
        restBackgroundKomentarz.getComment("recipeId=" + Integer.toString(recipe.id));
        restBackgroundLike.getLike("recipeId=" + Integer.toString(recipe.id));
    }

    @Click
    void likeClicked(){
        //check if user is logged in
        if(user == null) {
            Login_.intent(this).user(user).start();
        } else {
            ringProgressDialog.show();
            //add like to the recipe
            restBackgroundLike.setLike(user, recipe);
            //refresh like's
            restBackgroundLike.getLike("recipeId=" + Integer.toString(recipe.id));
        }
    }

    @Click
    void commentClicked(){
        //check if user is logged in
        if(user == null) {
            Login_.intent(this).user(user).start();
        } else {
            //pack bundle to another activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("recipe",recipe);
            bundle.putSerializable("user",user);

            //go to commentview activity with packed bundle
            KomentarzAct_.intent(this).bundle(bundle).start();
        }
    }


    public void updateComments(ListaKomentarzy commentList){
        if(commentList != null) {
//            ListIterator listIterator = commentList.records.listIterator();
//            CommentList commentList1 = new CommentList();
//
//            while (listIterator.hasNext()) {
//                Comment comment = (Comment) listIterator.next();
//
//                if (comment.recipeId == recipe.id) {
//                    commentList1.records.add(comment);
//                }
//            }
//
//            if (commentList1 != null) {
            //adapter.update(commentList1);
            adapter.update(commentList);
            //stop progress dialog after loading comments
            ringProgressDialog.dismiss();
        }
//        }
    }

    public void updateLikes(ListaLike likeList){
//        if(likeList != null){
//            ListIterator listIterator = likeList.records.listIterator();
//            int counter = 0;
//
//            while(listIterator.hasNext()){
//                Like like = (Like) listIterator.next();
//
//                if(like.recipeId == recipe.id) {
//                    counter++;
//                }
//            }
//
//            if(counter != 0){
//                likes.setText(String.valueOf(counter) + " osób lubi to.");
//            }
//        }
        if(likeList != null){
            likes.setText(Integer.toString(likeList.records.size()) + " osób lubi to.");
        }
        //stop progressdialog after loading likes
        ringProgressDialog.dismiss();

    }


    public void showError(Exception e){
        Toast.makeText(this, "Nie bangla, spróbuj jeszcze raz\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}