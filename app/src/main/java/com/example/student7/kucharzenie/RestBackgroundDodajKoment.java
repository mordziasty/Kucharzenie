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
public class RestBackgroundDodajKoment {
    @RootContext
    KomentarzAct activity; //activity with listview

    @RestService
    KucharzenieRestclient restClient; //use that cool interface


    //load recipes in background without blocking main application ;-)
    @Background
    void addComment(User user, Przepis recipe, String text){
        try{
            //rest client headers for POST
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);

            //new class
            Komentarz comment = new Komentarz();
            comment.recipeId = recipe.id;
            comment.ownerId = user.id;
            comment.text = text;

            restClient.addComment(comment);
        } catch(Exception e){
            publishError(e);
        }

    }


    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){activity.showError(e); }

}