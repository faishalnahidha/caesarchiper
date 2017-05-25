package com.izzan.caesarchiper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DekripsiFragment extends Fragment {

    private String plainText;
    private String chiperText;
    private char[][] key = new char[][]
            {{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
                    {'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3'}};


    public DekripsiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dekripsi, container, false);

        final EditText editTextInput = (EditText) view.findViewById(R.id.chiperEditText);
        final TextView textViewOutput = (TextView) view.findViewById(R.id.textViewChiperText);
        final CardView cardViewOutput = (CardView) view.findViewById(R.id.cardOutputDekripsi);
        Button buttonDekripsi = (Button) view.findViewById(R.id.button);

        buttonDekripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiperText = editTextInput.getText().toString();
                plainText = dekripsi(chiperText);
                textViewOutput.setText(plainText);

                cardViewOutput.setVisibility(View.VISIBLE);

                try  {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextInput.getWindowToken(), 0);
                } catch (Exception e) {

                }

                Toast.makeText(getContext(), "Teks berhasil dienkripsi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private String dekripsi(String chiperText) {

        chiperText = chiperText.toUpperCase();
        char[] chiperTextChar = chiperText.toCharArray();
        char[] plainTextChar = new char[chiperText.length()];

        /**
         *iterasi chiperTextChar dengan key[] untuk mengenkripsi per karakter
         */

        for (int i = 0; i < chiperTextChar.length; i++) {

            int match = 0;
            for (int e = 0; e < key[1].length; e++) {

                System.out.print(key[1][e]);
                if (chiperTextChar[i] == key[1][e]) {
                    plainTextChar[i] = key[0][e];
                    match = 1;
                    break;
                }
            }

            System.out.println();
            if (match == 0) {
                plainTextChar[i] = chiperTextChar[i];
            }
        }

        String plainText = new String(plainTextChar);
        return plainText;
    }

}
