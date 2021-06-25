package soares.matheus.appdevelopment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class AdapterDevs extends BaseAdapter {

    private List<Dev> devList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterDevs(Context context, List<Dev> listaDevs) {
        this.devList = listaDevs;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return devList.size();
    }

    @Override
    public Object getItem(int i) {
        return devList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return devList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista,null);
            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvEmail = convertView.findViewById(R.id.tvListaEmail);
            item.tvDevelopment = convertView.findViewById(R.id.tvListaDevs);
            item.tvDescr = convertView.findViewById(R.id.tvListaDescr);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag(item);
        }else{
            item = (ItemSuporte) convertView.getTag();
        }
            Dev dev = devList.get(i);
            item.tvNome.setText(dev.nome);
            item.tvEmail.setText(dev.email);
            item.tvDevelopment.setText(String.valueOf(dev.getDevelopment()));
            item.tvDescr.setText(dev.descr);

            if(i%2 == 0){

                item.layout.setBackgroundColor(Color.rgb(230,230,230));
            }else{
                item.layout.setBackgroundColor(Color.WHITE);
            }

        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvEmail, tvDescr, tvDevelopment;
        //TextView tvNome, tvDevelopment;
        LinearLayout layout;
    }
}
