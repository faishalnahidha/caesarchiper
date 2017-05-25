package com.izzan.caesarchiper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EnkripsiFragment extends Fragment {

    private String plainText;
    private String chiperText;
    private char[][] key = new char[][]
            {{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'},
            {'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3'}};

    public EnkripsiFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_enkripsi, container, false);

        final EditText editTextInput = (EditText) view.findViewById(R.id.plainEditText);
        final TextView textViewOutput = (TextView) view.findViewById(R.id.textViewChiperText);
        final CardView cardViewOutput = (CardView) view.findViewById(R.id.cardOutputEnkripsi);
        Button buttonEnkripsi = (Button) view.findViewById(R.id.button);

        buttonEnkripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plainText = editTextInput.getText().toString();
                chiperText = enkripsi(plainText);
                textViewOutput.setText(chiperText);

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

    /**
     * method untuk mengenkripsi input text
     * input text dikonversi dari String ke array_of_char / char[]
     * lalu tiap indeksnya diiterasi dengan tiap index (e) di array key[0][e]
     * jika karakter pada tiap index char[] ditemukan pada array key[0][e], set chiperText-nya
     * dengan key[1][e]. Jika tidak ketemu karakternya, tidak perlu diubah pada chiperText-nya
     */

    public String enkripsi(String plainText) {

        plainText = plainText.toUpperCase();
        char[] plainTextChar = plainText.toCharArray();
        char[] chiperTextChar = new char[plainText.length()];

        /**
        *iterasi plainTextChar dengan key[] untuk mengenkripsi per karakter
         */

        for (int i = 0; i < plainTextChar.length; i++) {

            int match = 0;
            for (int e = 0; e < key[0].length; e++) {

                System.out.print(key[0][e]);
                if (plainTextChar[i] == key[0][e]) {
                    chiperTextChar[i] = key[1][e];
                    match = 1;
                    break;
                }
            }

            System.out.println();
            if (match == 0) {
                chiperTextChar[i] = plainTextChar[i];
            }
        }

        String chiperText = new String(chiperTextChar);
        return chiperText;
    }

}
