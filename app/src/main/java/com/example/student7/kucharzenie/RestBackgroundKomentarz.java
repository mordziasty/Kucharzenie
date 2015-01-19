package com.example.student7.kucharzenie;

        import com.example.student7.kucharzenie.data.ListaKomentarzy;
        import com.example.student7.kucharzenie.data.Kucharzenie;

        import org.androidannotations.annotations.Background;
        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;
        import org.androidannotations.annotations.UiThread;
        import org.androidannotations.annotations.rest.RestService;

@EBean
public class RestBackgroundKomentarz {
    @RootContext
    SelectedView activity; //activity with listview

    @RestService
    KucharzenieRestclient restClient; //use that cool interface


    //load recipes in background without blocking main application ;-)
    @Background
    void getComment(String path){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            ListaKomentarzy commentList = restClient.getComment(path);
            publishResult(commentList);
        } catch (Exception e){
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(ListaKomentarzy commentList){
        activity.updateComments(commentList);
    }
    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}