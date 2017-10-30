package fast.kopach.math;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.RatingBar;

public class AppEvaluationDialog extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setTitle("Please rate this game");

        View linearlayout = getActivity().getLayoutInflater().inflate(R.layout.dialog_app_evaluation, null);
        builder.setView(linearlayout);

        final RatingBar rating = (RatingBar)linearlayout.findViewById(R.id.ratingbar);
        rating.setRating(0);

        builder.setPositiveButton("Rate it",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (rating.getRating() > 3){
                            Intent intent_history_ukr = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=kopach.vova.zno_app"));
                            startActivity(intent_history_ukr);
                        }
                        dialog.dismiss();
                    }
                })

                .setNeutralButton("Later",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        return builder.create();
    }
}
