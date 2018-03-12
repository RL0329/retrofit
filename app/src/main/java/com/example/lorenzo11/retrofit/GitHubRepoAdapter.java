package com.example.lorenzo11.retrofit;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {

    private Context context;
    private List<GitHubRepo> values;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
        super(context, R.layout.list_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView emailTv = row.findViewById(R.id.eMailTv);
        TextView unameTv = row.findViewById(R.id.uNameTv);
        TextView pwordTv = row.findViewById(R.id.pWordTv);
        TextView fnameTv = row.findViewById(R.id.fNameTv);
        TextView lnameTv = row.findViewById(R.id.lNameTv);

        GitHubRepo item = values.get(position);

        String Email=item.getEmail();
        String Uname=item.getUsername();
        String Pword = item.getPassword();
        String Fname = item.getFirstname();
        String Lname = item.getLastname();
        emailTv.setText(Email);
        unameTv.setText(Uname);
        pwordTv.setText(Pword);
        fnameTv.setText(Fname);
        lnameTv.setText(Lname);

        return row;
    }
}