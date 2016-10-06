package net.kikuchy.enumadapter;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Enum to String resource converter.
 */

public abstract class ResourceStringify<E extends Enum<E>> implements Stringify<E> {
    private Context mContext;

    public ResourceStringify(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public String invoke(E target) {
        return mContext.getString(getStringResource(target));
    }

    /**
     * Return a string resource that represent to the given Enum constant.
     *
     * @param target Enum constant needs string resource
     * @return String resource ID
     */
    public abstract
    @StringRes
    int getStringResource(E target);
}
