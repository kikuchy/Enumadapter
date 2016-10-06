package net.kikuchy.enumadapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Specified {@link ArrayAdapter} to show and select {@code enum} constants with Enum -> String converter.
 *
 * @see android.widget.ArrayAdapter
 */

public class EnumArrayAdapter<E extends Enum<E>> extends ArrayAdapter<E> {
    private Stringify<E> mStringify;
    private int mFieldId = 0;

    public EnumArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull E[] objects, @Nullable Stringify<E> stringify) {
        super(context, resource, objects);
        mStringify = stringify;
    }

    public EnumArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull E[] objects, @Nullable Stringify<E> stringify) {
        super(context, resource, textViewResourceId, objects);
        this.mStringify = stringify;
        this.mFieldId = textViewResourceId;
    }

    public EnumArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<E> objects, @Nullable Stringify<E> stringify) {
        super(context, resource, objects);
        this.mStringify = stringify;
    }

    public EnumArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<E> objects, @Nullable Stringify<E> stringify) {
        super(context, resource, textViewResourceId, objects);
        this.mStringify = stringify;
        this.mFieldId = textViewResourceId;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return overwriteTextLabel(super.getDropDownView(position, convertView, parent), position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return overwriteTextLabel(super.getView(position, convertView, parent), position);
    }

    private View overwriteTextLabel(View original, int position) {
        final TextView text;
        try {
            if (mFieldId == 0) {
                text = (TextView) original;
            } else {
                text = (TextView) original.findViewById(mFieldId);

                if (text == null) {
                    throw new RuntimeException("Failed to find view with ID "
                            + getContext().getResources().getResourceName(mFieldId)
                            + " in item layout");
                }
            }
        } catch (ClassCastException e) {
            throw new IllegalStateException(
                    "EnumArrayAdapter requires the resource ID to be a TextView", e);
        }

        final E item = getItem(position);
        if (mStringify != null) {
            text.setText(mStringify.invoke(item));
        } else {
            // Throw NPE if item is null.
            text.setText(item.toString());
        }

        return original;
    }
}
