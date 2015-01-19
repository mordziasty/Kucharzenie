package com.example.student7.kucharzenie.itemView;

        import android.content.Context;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.example.student7.kucharzenie.R;
        import com.example.student7.kucharzenie.data.Komentarz;

        import org.androidannotations.annotations.EViewGroup;
        import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.lista_komentow)
public class KomentarzItem extends RelativeLayout {
    @ViewById
    TextView ownerId;
    @ViewById
    TextView created;
    @ViewById
    TextView text;


    public KomentarzItem(Context context){
        super(context);
    }

    public void bind(Komentarz comment){
        ownerId.setText(String.valueOf(comment.ownerId));
        created.setText(comment.created);
        text.setText(comment.text);
    }
//        created.setText(recipe.created.toString());
}