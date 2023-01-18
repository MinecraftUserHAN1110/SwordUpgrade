package upgrade.sword.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Random;

public class WrapperRandom {
    public static final WrapperRandom INSTANCE = new WrapperRandom();

    private static final Random r = new Random();

    public boolean isTrue(final int upgrade, int addchance) {
        final Float f = new Float((100/(1.15 * ((1.15/2) * upgrade))) + addchance);
        int chance = r.nextInt(101);
        if (chance == 0) {
            chance+=1;
        }
        if (f.intValue() >= chance) {
            return true;
        }
        return false;
    }

    public boolean get15PerCheck() {
        final int static_chance = 15;
        int i = r.nextInt(101);
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
        int i = r.nextInt(101);
        if (i == 0) {
            i++;
        }
        if (i > static_chance) {
            return true;
        }
        return false;
    }
}
