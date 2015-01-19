package com.example.student7.kucharzenie;

        import com.example.student7.kucharzenie.data.Komentarz;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.data.User;

        import org.androidannotations.annotations.Background;
        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;
        import org.androidannotations.annotations.UiThread;
        import org.androidannotations.annotations.rest.RestService;


@EBean
public class RestBackgroundDodajPrzepis {
    @RootContext
    DodajPrzepis activity; //activity with listview

    @RestService
    KucharzenieRestclient restClient; //use that cool interface


    //load recipes in background without blocking main application ;-)
    @Background
    void addRecipe(Przepis recipe, User user){
        try{
            //rest client headers for POST
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);

            //recipe is filled in AddRecipeView so it only requires to pass it to restClient.addRecipe
            restClient.addRecipe(recipe);
        } catch(Exception e){
            publishError(e);
        }

    }

    @UiThread
    void publishSuccess(){
        activity.showSuccess();
    }

    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}