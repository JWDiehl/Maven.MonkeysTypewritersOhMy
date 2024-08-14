package io.zipcoder;

import java.util.Objects;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {
    private final Object lock = new Object();

    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    @Override
    public void run() {
        while (stringIterator.hasNext()) {
            String word;
            synchronized (lock) {
                if (stringIterator.hasNext()) {
                    word = stringIterator.next();
                    copied += word + " ";
                }
            }
        }
    }
}
