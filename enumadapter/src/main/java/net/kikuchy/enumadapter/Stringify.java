package net.kikuchy.enumadapter;

/**
 * Enum to String converter.
 */

public interface Stringify<E extends Enum<E>> {
    String invoke(E target);
}
