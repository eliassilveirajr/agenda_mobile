package app.melhoroftheworld.agenda.nota;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.melhoroftheworld.agenda.R;
import app.melhoroftheworld.agenda.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etConteudo;

    public AdicionarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.nota_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_nota);
        etConteudo = v.findViewById(R.id.editText_conteudo_nota);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_nota);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome da nota", Toast.LENGTH_LONG).show();
        } else if (etConteudo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, insira conteúdo na nota", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Nota n = new Nota();
            n.setNome(etNome.getText().toString());
            n.setConteudo(etConteudo.getText().toString());
            databaseHelper.createNota(n);
            Toast.makeText(getActivity(), "Nota salva", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_nota, new ListarFragment()).commit();

        }
    }
}