package upgrade.sword.event;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import upgrade.sword.Main;
import upgrade.sword.utils.Data;
import upgrade.sword.utils.ItemUpgradeLevelUtil;
import upgrade.sword.utils.WrapperRandom;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.ChatColor.*;

public class GUI implements Listener {
    private Inventory i;
    private final ItemStack start = new ItemStack(Material.IRON_AXE);
    private final ItemStack ui = new ItemStack(Material.IRON_AXE);
    private final ItemStack layout  = new ItemStack(Material.STAINED_GLASS_PANE);
    private ItemStack stack;
    private Player player;
    private UpgradeBase base;
    public GUI(Player player, UpgradeBase base) {
        this.player = player;
        this.base = base;
    }

    public GUI setupInv(Player player) {
        i = Bukkit.createInventory(player, 54, "강화");
        ui.setDurability((short) 9);
        ItemMeta itemMeta = start.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "강화 시작");
        start.setItemMeta(itemMeta);
        start.setDurability((short) 247);
        i.setItem(22, start);
        i.setItem(53, ui);
        return this;
    }

    private JavaPlugin plugin;

    public GUI(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public Inventory getInv() {
        return i;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            if (e.getClickedInventory().getName().equalsIgnoreCase("강화") || e.getClickedInventory().equals(i) ||
                    e.getClickedInventory().contains(start)) {
                Player p = (Player) e.getWhoClicked();

                if (e.getClick().isShiftClick() || e.getClick().isRightClick()) {
                    e.setCancelled(true);
                    return;
                }

                try {
                    ItemStack weapon = e.getClickedInventory().getItem(4) == null ? new ItemStack(Material.AIR) : e.getClickedInventory().getItem(4);
                    if (e.getSlot() == 4) {
                        stack = e.getInventory().getItem(4);
                    }
                    if (!(e.getSlot() == 22 || e.getSlot() == 42 || e.getSlot() == 4 || e.getSlot() == 31 || e.getSlot() == 38)) {
                        e.setCancelled(true);
                        return;
                    }
                    if (e.getSlot() == 22 || e.getSlot() == 23) {
                        e.setCancelled(true);
                        if (!weapon.getType().equals(Material.AIR) || weapon.getType() != Material.AIR) {
                            Bukkit.getLogger().warning(YELLOW + p.getName() + "님이 강화를 시도함! (world=" + p.getWorld().getName() + ")");
                            if (e.getClickedInventory().getItem(31) == null) {
                                p.sendMessage(RED + "강화에 필요한 재료가 없습니다");
                                return;
                            }

                            if (e.getClickedInventory().getItem(4).getItemMeta().getDisplayName().contains("30")) {
                                p.sendMessage(GRAY + "30레벨이 넘어서 강화가 불가능합니다.");
                                return;
                            }

                            ItemStack stack = e.getClickedInventory().getItem(31);
                            stack.setAmount(e.getClickedInventory().getItem(31).getAmount() - 1);
                            e.getClickedInventory().setItem(31, stack);
                            boolean checkSucuess = false;
                            boolean breaked = false;
                            boolean safed = false;
                            boolean inf = checkAir(i, 38);
                            newInventory = UpgradeBase.inv.get(p.getUniqueId());
                            Type type = checkAddPer(e.getClickedInventory().getItem(42));
                            addchancecmd:
                            switch (type.getId()) {
                                case 1:
                                    checkSucuess = WrapperRandom.INSTANCE.isTrue(ItemUpgradeLevelUtil.INSTANCE.findLevel(weapon), 0);
                                    break addchancecmd;
                                case 2:
                                    checkSucuess = WrapperRandom.INSTANCE.isTrue(ItemUpgradeLevelUtil.INSTANCE.findLevel(weapon), 1);
                                    break addchancecmd;
                                case 3:
                                    checkSucuess = WrapperRandom.INSTANCE.isTrue(ItemUpgradeLevelUtil.INSTANCE.findLevel(weapon), 5);
                                    break addchancecmd;
                                case 4:
                                    checkSucuess = WrapperRandom.INSTANCE.isTrue(ItemUpgradeLevelUtil.INSTANCE.findLevel(weapon), 10);
                                    break addchancecmd;
                                default:
                                    System.out.println("Unknow Process");
                                    p.sendMessage("42번칸 공기");
                                    break addchancecmd;
                            }
                            int damage = 0;
                            int up = 0;
                            Type2 type2 = checkAddUpLevel(e.getInventory().getItem(42));
                            switch (type2.getId()) {
                                case 1:
                                    up = 0;
                                    breaked = WrapperRandom.INSTANCE.get10PerCheck();
                                    break;
                                case 2:
                                    up = 5;
                                    breaked = WrapperRandom.INSTANCE.get10PerCheck();
                                    break;
                                case 3:
                                    up = 10;
                                    breaked = WrapperRandom.INSTANCE.get10PerCheck();
                                    break;
                                default:
                                    System.out.println("Unknow Process");
                                    break;
                            }

                            if (breaked) {
                                safed = WrapperRandom.INSTANCE.get15PerCheck();
                            }
                            if (checkSucuess) {
                                p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 성공하였습니다");
                                p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 2, 1); //minecraft:block.enchantment_table.use
                                this.modfiy(e.getClickedInventory().getItem(4), damage, up, p);
                                changeUpgradeButton(p, true);
                                if (ItemUpgradeLevelUtil.INSTANCE.findLevel(weapon) >= 10) {
                                    Main.addChatMessage(ChatColor.GREEN + p.getName() + "가" + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 성공하였습니다");
                                }
                            } else {
                                if (breaked && up != 0 && !safed) {
                                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + p.getName() + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 파괴되었습니다");
                                    newInventory.setItem(4, new ItemStack(Material.AIR));
                                } else { //entity.zombie.break_wooden_door
                                    p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 2, 1);
                                    p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + p.getName() + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 실패하였습니다");
                                    changeUpgradeButton(p, false);
                                }
                            }
                        }
                    }
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                    ex.getStackTrace();
                    ex.getLocalizedMessage();
                    ex.getSuppressed();
                    ex.printStackTrace();
                }
            }
        }
    }

    private boolean checkAir(Inventory inventory, int index) {
        try {
            return !(inventory.getItem(index).getType() == Material.AIR || inventory.getItem(index).getType() == Material.AIR ||
                    inventory.getItem(index).getType() == null);
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return true;
    }

    public void changeUpgradeButton(Player clicker, boolean sucuess) {
        if (sucuess) {
            ui.setDurability((short) 13);
            newInventory.setItem(53, ui);
            clicker.openInventory(newInventory);
        } else {
            ui.setDurability((short) 14);
            newInventory.setItem(53, ui);
            clicker.openInventory(newInventory);
        }
    }


    public void modfiy(ItemStack item, boolean subdamage, @NotNull int up, Player p) {
        if (checkLevel(item)) {
            ItemMeta meta = item.getItemMeta();
            int i = 0;
            for (String str : item.getLore()) {
                if (str.endsWith("보조공격력")) {
                    continue;
                }
                if (str.endsWith("공격력")) {
                    break;
                }
                i+=1;
            }

            List<String> nowLore = item.getLore();
            String textLore = nowLore.get(i);
            String damage = textLore.split(" ")[0];
            damage = damage.substring(2);
            int newDamage = (int) (Integer.parseInt(damage) * 1.2);
            nowLore.set(i, GRAY + (newDamage + " 공격력"));
            meta.setLore(nowLore);
            item.setItemMeta(meta);
            meta = item.getItemMeta();
            meta = applyMeta(p, meta);
            item.setItemMeta(meta);
            newInventory.setItem(4, item);
        }
    }

    private boolean checkLevel(ItemStack item) {
        int i = 0;
        for (String str : item.getLore()) {
            if (str.endsWith("레벨")) {
                continue;
            }
            i+=1;
        }

        

        return stack;
    }

    private Inventory newInventory;

    private ItemMeta applyMeta(Player p, ItemMeta meta) {
        if (meta.getDisplayName().contains("+10")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +11";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+11")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +12";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+12")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +13";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+13")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +14";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+14")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +15";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+15")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +16";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+16")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +17";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+17")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +18";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+18")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +19";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+19")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +20";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+20")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +21";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+21")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +22";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+22")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +23";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+23")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +24";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+24")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +25";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+25")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +26";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+26")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +27";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+27")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +28";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+28")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +29";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+29")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +30";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+30")) {
            return meta;
        } else if (meta.getDisplayName().contains("+1")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +2";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+2")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +3";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+3")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +4";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+4")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +5";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+5")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +6";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+6")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +7";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+7")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +8";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+8")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +9";
            meta.setDisplayName(displayName);
            return meta;
        } else if (meta.getDisplayName().contains("+9")) {
            String beforedisplayName = meta.getDisplayName().split(" +")[0];
            String afterdisplayName = meta.getDisplayName().split(" +")[1];
            String displayName = beforedisplayName + " " + afterdisplayName + " +10";
            meta.setDisplayName(displayName);
            return meta;
        } else {
            String displayName = meta.getDisplayName() + " +1";
            meta.setDisplayName(displayName);
            return meta;
        }
    }

    public enum Type {
        NONE(1),
        IPER(2),
        VPER(3),
        XPER(4);

        @Getter
        private final int id;
        private Type(int id) {
            this.id = id;
        }
    }

    public enum Type2 {
        NONE(1),
        PLUS_FIVE(2),
        PLUS_TEN(3);

        @Getter
        private final int id;
        private Type2(int id) {
            this.id = id;
        }
    }

    public Type checkAddPer(ItemStack stack) {
        if (stack == UpgradeBase.getThis(Main.main).getSafetyItem()) {
            return Type.IPER;
        } else if (stack == UpgradeBase.getThis(Main.main).getSafetyItem2()) {
            return Type.VPER;
        } else if (stack == UpgradeBase.getThis(Main.main).getSafetyItem3()) {
            return Type.XPER;
        }
        return Type.NONE;
    }

    public Type2 checkAddUpLevel(ItemStack stack) {
        if (stack == UpgradeBase.getThis(Main.main).getUnknowgrade()) {
            return Type2.PLUS_FIVE;
        } else if (stack == UpgradeBase.getThis(Main.main).getUnknowgrade2()) {
            return Type2.PLUS_TEN;
        }
        return Type2.NONE;
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(i)) {
            Player p = (Player) e.getPlayer();
            p.getInventory().addItem(stack);
        }
    }
}
