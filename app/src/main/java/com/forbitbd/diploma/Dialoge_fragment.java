package com.forbitbd.diploma;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

import static android.content.ContentValues.TAG;

public class Dialoge_fragment extends DialogFragment {

    private TextView tvone;
    private MaterialButton btncancel;
    private ImageView pass, fail;
    private MyListener listener;

    public Dialoge_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listener = (MyListener) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_dialoge_fragment, null);

        tvone = view.findViewById(R.id.result);
        btncancel = view.findViewById(R.id.btncancel);
        pass = view.findViewById(R.id.pass);
        fail = view.findViewById(R.id.fail);

        String getArgument = getArguments().getString("data");
        if (getArgument.length() > 8) {
            fail.setVisibility(View.VISIBLE);
            tvone.setText("You've failed in" + getArgument);
            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),R.raw.checkmark);
            mediaPlayer.start();
        } else {
            pass.setVisibility(View.VISIBLE);
            tvone.setText("Your result is " + getArgument);
            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),R.raw.checkmark);
            mediaPlayer.start();
        }

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("jjjjjj", "onClick: ad clicked");
                if (listener != null) {
                    listener.onRewardButtonClick();
                    dismiss();
                }
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Dialog_Alert).create();
        alertDialog.setView(view);
        return alertDialog;
    }
}