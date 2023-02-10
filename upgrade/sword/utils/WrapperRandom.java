package upgrade.sword.utils;

import java.util.concurrent.ThreadLocalRandom;

public class WrapperRandom {
    public static final WrapperRandom INSTANCE = new WrapperRandom();

    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public boolean isTrue(final int upgrade, int addchance) {
        final double value = 100 - (upgrade * 3.3);
        int chance = RANDOM.nextInt(100) + addchance;
        System.out.println("value: " + value);
        System.out.println("chance: " + chance);
        return chance <= value;
    }

    public boolean get15PerCheck() {
        final int static_chance = 15;
        int i = RANDOM.nextInt(101);
        if (i == 0) {
            i++;
        }
        if (i > static_chance) {
            return true;
        }
        return false;
    }

    public boolean get10PerCheck() {
        final int static_chance = 10;
        int i = RANDOM.nextInt(101);
        if (i == 0) {
            i++;
        }
        if (i > static_chance) {
            return true;
        }
        return false;
    }
}
