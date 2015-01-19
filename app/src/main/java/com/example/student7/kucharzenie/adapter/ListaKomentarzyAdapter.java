package com.example.student7.kucharzenie.adapter;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;

        import com.example.student7.kucharzenie.data.Komentarz;
        import com.example.student7.kucharzenie.data.ListaKomentarzy;
        import com.example.student7.kucharzenie.itemView.KomentarzItem;
        import com.example.student7.kucharzenie.itemView.KomentarzItem_;

        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;

        import java.util.ArrayList;
        import java.util.List;

@EBean
public class ListaKomentarzyAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<Komentarz> comments = new ArrayList<Komentarz>();

    public ListaKomentarzyAdapter() {
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Komentarz getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        KomentarzItem komentarzItem;
        if (convertView == null) {
            komentarzItem = KomentarzItem_.build(context);
        } else {
            komentarzItem = (KomentarzItem) convertView;
        }

        komentarzItem.bind(getItem(position));

        return komentarzItem;
    }

    public void update(ListaKomentarzy listaKomentarzy) {
        comments.clear();
        comments.addAll(listaKomentarzy.records);
        notifyDataSetChanged();
    }
}