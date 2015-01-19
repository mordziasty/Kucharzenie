package com.example.student7.kucharzenie;

        import com.example.student7.kucharzenie.data.Komentarz;
        import com.example.student7.kucharzenie.data.ListaKomentarzy;
        import com.example.student7.kucharzenie.data.Kucharzenie;
        import com.example.student7.kucharzenie.data.EmailAndPassword;
        import com.example.student7.kucharzenie.data.Like;
        import com.example.student7.kucharzenie.data.ListaLike;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.data.User;

        import org.androidannotations.annotations.rest.Get;
        import org.androidannotations.annotations.rest.Post;
        import org.androidannotations.annotations.rest.RequiresHeader;
        import org.androidannotations.annotations.rest.Rest;
        import org.androidannotations.api.rest.RestClientHeaders;
        import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


@Rest(rootUrl = "http://pegaz.wzr.ug.edu.pl:8080/rest",
        converters = { MappingJackson2HttpMessageConverter.class })
@RequiresHeader({"X-Dreamfactory-Application-Name"})
public interface KucharzenieRestclient extends RestClientHeaders {

    //Get&Post for selecting/adding recipes
    @Get("/db/recipes")
    Kucharzenie getKucharzenie();

    @RequiresHeader({"X-Dreamfactory-Application-Name","X-Dreamfactory-Session-Token"})
    @Post("/db/recipes")
    void addRecipe(Przepis recipe);



    //region Get&Post for selecting/adding comments
    @Get("/db/comments?filter={path}")
    ListaKomentarzy getComment(String path);

    @RequiresHeader({"X-Dreamfactory-Application-Name","X-Dreamfactory-Session-Token"})
    @Post("/db/comments")
    void addComment(Komentarz comment);
    //endregion



    //region Get&Post likes
    @Get("/db/likes?filter={path}")
    ListaLike getLike(String path);

    @RequiresHeader({"X-Dreamfactory-Application-Name","X-Dreamfactory-Session-Token"})
    @Post("/db/likes")
    void setLike(Like like);
    //endregion



    //Login for adding recipes/comments
    @Post("/user/session")
    User login(EmailAndPassword emailAndPassword);
}