package com.example.student7.kucharzenie;

        import com.example.student7.kucharzenie.data.ListaKomentarzy;
        import com.example.student7.kucharzenie.data.Kucharzenie;

        import org.androidannotations.annotations.Background;
        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;
        import org.androidannotations.annotations.UiThread;
        import org.androidannotations.annotations.rest.RestService;

@EBean
public class RestBackgroundTask {
    @RootContext
    Glowna activity; //activity with listview

    @RestService
    KucharzenieRestclient restClient; //use that cool interface


    //load recipes in background without blocking main application ;-)
    @Background
    void getKucharzenie(){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            Kucharzenie kucharzenie = restClient.getKucharzenie();
            publishResult(kucharzenie);
        } catch (Exception e){
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(Kucharzenie kucharzenie){
        activity.updateKucharzenie(kucharzenie);
    }
    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}