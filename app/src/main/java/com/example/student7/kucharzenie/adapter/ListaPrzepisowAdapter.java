package com.example.student7.kucharzenie.adapter;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;

        import com.example.student7.kucharzenie.data.Kucharzenie;
        import com.example.student7.kucharzenie.data.Przepis;
        import com.example.student7.kucharzenie.itemView.PrzepisItem;
        import com.example.student7.kucharzenie.itemView.PrzepisItem_;

        import org.androidannotations.annotations.EBean;
        import org.androidannotations.annotations.RootContext;

        import java.util.ArrayList;
        import java.util.List;

@EBean
public class ListaPrzepisowAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<Przepis> recipes = new ArrayList<Przepis>();

    public ListaPrzepisowAdapter() {
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Przepis getItem(int i) {
        return recipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PrzepisItem przepisItem;
        if (convertView == null) {
            przepisItem = PrzepisItem_.build(context);
        } else {
            przepisItem = (PrzepisItem) convertView;
        }

        przepisItem.bind(getItem(position));

        return przepisItem;
    }

    public void update(Kucharzenie kucharzenie) {
        recipes.clear();
        recipes.addAll(kucharzenie.records);
        notifyDataSetChanged();
    }
}