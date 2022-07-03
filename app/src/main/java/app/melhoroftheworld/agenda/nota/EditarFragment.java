package app.melhoroftheworld.agenda.nota;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


public class EditarFragment extends Fragment {

    private EditText etNome;
    private EditText etConteudo;
    private DatabaseHelper databaseHelper;
    private Nota n;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.nota_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_nota);
        etConteudo = v.findViewById(R.id.editText_conteudo_nota);

        Bundle b = getArguments();
        int id_nota = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        n = databaseHelper.getByIdNota(id_nota);

        etNome.setText(n.getNome());
        etConteudo.setText(n.getConteudo());

        Button btnEditar = v.findViewById(R.id.button_editar_nota);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_nota);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_nota);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Desejar excluir essa Nota?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_nota);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void editar(int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome da nota", Toast.LENGTH_LONG).show();
        } else if (etConteudo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, insira conteúdo na nota", Toast.LENGTH_LONG).show();
        } else {
            databaseHelper = new DatabaseHelper(getActivity());
            n = new Nota();
            n.setId(id);
            n.setNome(etNome.getText().toString());
            n.setConteudo(etConteudo.getText().toString());
            databaseHelper.updateNota(n);
            Toast.makeText(getActivity(), "Nota atualizada", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_nota, new ListarFragment()).commit();

        }
    }

    private void excluir(int id) {
        n = new Nota();
        n.setId(id);
        databaseHelper.deleteNota(n);
        Toast.makeText(getActivity(), "Nota excluída", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_nota, new ListarFragment()).commit();

    }
}