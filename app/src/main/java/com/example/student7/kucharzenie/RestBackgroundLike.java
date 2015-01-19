package com.example.student7.kucharzenie;

        import com.example.student7.kucharzenie.data.ListaKomentarzy;
        import com.example.student7.kucharzenie.data.Like;
        import com.example.student7.kucharzenie.data.ListaLike;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.data.User;

        import org.androidannotations.annotations.Background;
        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;
        import org.androidannotations.annotations.UiThread;
        import org.androidannotations.annotations.rest.RestService;


@EBean
public class RestBackgroundLike {
    @RootContext
    SelectedView activity; //activity with listview

    @RestService
    KucharzenieRestclient restClient; //use that cool interface


    //load recipes in background without blocking main application ;-)
    @Background
    void getLike(String path){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            ListaLike likeList = restClient.getLike(path);
            publishResult(likeList);
        } catch (Exception e){
            publishError(e);
        }
    }

    @Background
    void setLike(User user, Przepis recipe){
        try{
            //rest client headers for POST
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);

            //new class
            Like like = new Like();
            like.recipeId = recipe.id;
            like.ownerId = user.id;

            //add like
            restClient.setLike(like);

            //refresh
            getLike("recipeId=" + Integer.toString(recipe.id));
        } catch(Exception e){
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(ListaLike likeList){
        activity.updateLikes(likeList);
    }
    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}