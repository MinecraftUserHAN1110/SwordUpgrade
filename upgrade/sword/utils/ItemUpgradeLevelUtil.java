package upgrade.sword.utils;

import org.bukkit.inventory.ItemStack;

public class ItemUpgradeLevelUtil {
    public static final ItemUpgradeLevelUtil INSTANCE = new ItemUpgradeLevelUtil();

    public int findLevel(ItemStack item) {
        if (!item.getI18NDisplayName().isEmpty()) {
            String name = item.getI18NDisplayName();

            if (name.endsWith("+1")) {
                return 1;
            } else if (name.endsWith("+2")) {
                return 2;
            } else if (name.endsWith("+3")) {
                return 3;
            } else if (name.endsWith("+4")) {
                return 4;
            } else if (name.endsWith("+5")) {
                return 5;
            } else if (name.endsWith("+6")) {
                return 6;
            } else if (name.endsWith("+7")) {
                return 7;
            } else if (name.endsWith("+8")) {
                return 8;
            } else if (name.endsWith("+9")) {
                return 9;
            } else if (name.endsWith("+10")) {
                return 10;
            } else if (name.endsWith("+11")) {
                return 11;
            } else if (name.endsWith("+12")) {
                return 12;
            } else if (name.endsWith("+13")) {
                return 13;
            } else if (name.endsWith("+14")) {
                return 14;
            } else if (name.endsWith("+15")) {
                return 15;
            } else if (name.endsWith("+16")) {
                return 16;
            } else if (name.endsWith("+17")) {
                return 17;
            } else if (name.endsWith("+18")) {
                return 18;
            } else if (name.endsWith("+19")) {
                return 19;
            } else if (name.endsWith("+20")) {
                return 20;
            } else if (name.endsWith("+21")) {
                return 21;
            } else if (name.endsWith("+22")) {
                return 22;
            } else if (name.endsWith("+23")) {
                return 23;
            } else if (name.endsWith("+24")) {
                return 24;
            } else if (name.endsWith("+25")) {
                return 25;
            } else if (name.endsWith("+26")) {
                return 26;
            } else if (name.endsWith("+27")) {
                return 27;
            } else if (name.endsWith("+28")) {
                return 28;
            } else if (name.endsWith("+29")) {
                return 29;
            } else if (name.endsWith("+30")) {
                return 30;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
