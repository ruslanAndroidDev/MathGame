package fast.kopach.math;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

/**
 * Created by Руслан on 12.09.2017.
 */

public class ReplayDialog extends AlertDialog {
    protected ReplayDialog(@NonNull Context context) {
        super(context);
    }

    protected ReplayDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected ReplayDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
