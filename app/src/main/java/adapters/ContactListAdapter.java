package adapters;

import android.content.Context;
import android.widget.*;
import entities.Contact;
import java.util.*;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crudwithsqlitedatabase.R;

public class ContactListAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private List<Contact> contacts;

    public ContactListAdapter(@NonNull Context context, List<Contact> contacts) {
        super(context, R.layout.contact_layout, contacts);
        this.context = context;
        this.contacts= contacts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.contact_layout, parent, false);

        TextView textViewName = (TextView)  view.findViewById(R.id.textViewName);
        textViewName.setText(contacts.get(position).getName());

        TextView textViewPhone = (TextView)  view.findViewById(R.id.textViewPhone);
        textViewPhone.setText(contacts.get(position).getPhone());

        return view;
    }
}
