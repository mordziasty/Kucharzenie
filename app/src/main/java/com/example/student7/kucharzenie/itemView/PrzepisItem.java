package com.example.student7.kucharzenie.itemView;

        import android.content.Context;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.example.student7.kucharzenie.R;
        import com.example.student7.kucharzenie.data.Przepis;

        import org.androidannotations.annotations.EViewGroup;
        import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.lista_przepisow)
public class PrzepisItem extends RelativeLayout {
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    @ViewById
    TextView acronym;

    public PrzepisItem(Context context){
        super(context);
    }

    public void bind(Przepis recipe){
        title.setText(recipe.title);
//        created.setText(recipe.created.toString());
        introduction.setText(recipe.introduction);
        try {
            acronym.setText(recipe.title.substring(0, 1));
        } catch (StringIndexOutOfBoundsException e){
            acronym.setText("E");
        }
    }
}