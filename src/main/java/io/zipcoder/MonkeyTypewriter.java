package io.zipcoder;

public class MonkeyTypewriter {
    public static void main(String[] args) {
        String introduction = "It was the best of times,\n" +
                "it was the blurst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.

        //UnsafeCopier THreads
        UnsafeCopier[] unsafeMonkeys = new UnsafeCopier[5];
        Thread[] unsafeThreads = new Thread[unsafeMonkeys.length];
        for (int i = 0; i < unsafeMonkeys.length; i++) {
            unsafeMonkeys[i] = new UnsafeCopier(introduction);
            unsafeThreads[i] = new Thread(unsafeMonkeys[i]);
            unsafeThreads[i].start();
        }

        //create and start SafeCopier threads
        SafeCopier[] safeMonkeys = new SafeCopier[5];
        Thread[] safeThreads = new Thread[safeMonkeys.length];
        for (int i = 0; i < safeMonkeys.length; i++) {
            safeMonkeys[i] = new SafeCopier(introduction);
            safeThreads[i] = new Thread(safeMonkeys[i]);
            safeThreads[i].start();
        }

        //wait for all UnsafeCopier threads to finish
        for (Thread thread : unsafeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("UnsafeCopier thread interrupted");
            }
        }

        //wait for all SafeCopier threads to finish
        for (Thread thread : safeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("SafeCopier thread interrupted");
            }
        }

        //print out the copied versions
        System.out.println("Results from UnsafeCopier:");
        for (UnsafeCopier monkey : unsafeMonkeys) {
            System.out.println(monkey.copied);
        }

        System.out.println("\nResults from SafeCopier:");
        for (SafeCopier monkey : safeMonkeys) {
            System.out.println(monkey.copied);
        }
    }
}
