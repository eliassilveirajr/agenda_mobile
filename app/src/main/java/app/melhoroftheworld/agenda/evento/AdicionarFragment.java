package app.melhoroftheworld.agenda.evento;

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
    private EditText etDescricao;

    public AdicionarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.evento_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_evento);
        etDescricao = v.findViewById(R.id.editText_descricao_evento);
        
        Button btnAdicionar = v.findViewById(R.id.button_adicionar_evento);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome da evento", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, insira conteúdo na evento", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Evento e = new Evento();
            e.setNome(etNome.getText().toString());
            e.setDescricao(etDescricao.getText().toString());
            databaseHelper.createEvento(e);
            Toast.makeText(getActivity(), "Evento salva", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_evento, new ListarFragment()).commit();

        }
    }
}