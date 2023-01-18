package upgrade.sword.event;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
    private ItemStack stack;
    private Player player;
    private UpgradeBase base;
    public GUI(Player player, UpgradeBase base) {
        this.player = player;
        this.base = base;
    }

    public GUI setupInv(Player player) {
        i = Bukkit.createInventory(player, 54, "강화");
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
        if (e.getInventory().getName().equalsIgnoreCase("강화") || e.getInventory().equals(i) ||
                e.getInventory().contains(start)) {
            if (e.getSlot() == 53) {
                e.setCancelled(true);
            }
            Player p = (Player) e.getWhoClicked();

            try {
                ItemStack weapon = e.getClickedInventory().getItem(4) == null ? new ItemStack(Material.AIR) : e.getClickedInventory().getItem(4);
                if (e.getSlot() == 4) {
                    stack = e.getInventory().getItem(4);
                }
                p.sendMessage("try catch문");
                if (e.getSlot() == 22 || e.getSlot() == 23) {
                    e.setCancelled(true);
                    p.sendMessage("if문 1");
                    if (!weapon.getType().equals(Material.AIR) || weapon.getType() != Material.AIR) {
                        p.sendMessage("if문 2");
                        Bukkit.getLogger().warning(YELLOW + p.getName() + "님이 강화를 시도함! (world=" + p.getWorld().getName() + ")");
                        boolean checkSucuess = false;
                        boolean breaked = false;
                        boolean safed = false;
                        p.sendMessage("if문 3-1");
                        boolean inf = checkAir(i, 38);
                        p.sendMessage("if문 3-2");
                        Type type = checkAddPer(e.getClickedInventory().getItem(42));
                        p.sendMessage("if문 3-3");
                        addchancecmd: switch (type.getId()) {
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
                        p.sendMessage("if문 4");
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

                        p.sendMessage("if문 5");
                        if (breaked) {
                            safed = WrapperRandom.INSTANCE.get15PerCheck();
                        }
                        p.sendMessage("if문 6");
                        if (checkSucuess || inf) {
                            p.sendMessage( ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 성공하였습니다");
                            this.modfiy(weapon, damage, up, p);
                            changeUpgradeButton(true);
                            if (ItemUpgradeLevelUtil.INSTANCE.findLevel(weapon) >= 10) {
                                Main.addChatMessage(ChatColor.GREEN + p.getName() + "가" + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 성공하였습니다");
                            }
                        } else {
                            if (breaked && up != 0 && !safed) {
                                p.sendMessage( ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + p.getName() +  ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 파괴되었습니다");
                            } else {
                                p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + p.getName() + ChatColor.GREEN + weapon.getItemMeta().getDisplayName() + "강화에 실패하였습니다");
                                changeUpgradeButton(false);
                            }
                        }
                        p.sendMessage("if문 7");
                    }
                }
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                ex.getStackTrace();
            }
        }
    }

    private boolean checkAir(Inventory inventory, int index) {
        try {
            return inventory.getItem(index).getType() == Material.AIR || inventory.getItem(index).getType() == Material.AIR;
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return true;
    }

    public void changeUpgradeButton(boolean sucuess) {
        if (sucuess) {
            ui.setDurability((short) 11);
            i.setItem(53, ui);
        } else {
            ui.setDurability((short) 10);
            i.setItem(53, ui);
        }
    }

    public void modfiy(ItemStack item, int damages, @NotNull int up, Player p) {
        int lvl = ItemUpgradeLevelUtil.INSTANCE.findLevel(item);
        ItemMeta meta = item.getItemMeta();
        int i = 0;
        for (String str : item.getLore()) {
            if (str.startsWith("공격력")) {
                break;
            }
            i+=1;
        }

        List<String> nowLore = item.getLore();
        String textLore = nowLore.get(i - 1);
        String damage = textLore.split(" ")[1];
        int newDamage = new Data(damage).getDamage() + damages;
        nowLore.set(i - 1, GRAY + "공격력 " + newDamage);
        meta.setLore(nowLore);
        item.setItemMeta(meta);
        ItemStack clonedStack = item.clone();
        p.sendMessage(GREEN + "공격력 로어 위치 : " + i);
        Inventory newInv = p.getOpenInventory().getTopInventory();
        newInv.setItem(4, clonedStack);
        p.closeInventory();
        p.openInventory(newInv);
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
            for (ItemStack inventory : p.getInventory()) {
                inventory.setType(stack.getType());
                inventory.setDurability(stack.getDurability());
                inventory.setItemMeta(stack.getItemMeta());
            }
        }
    }
}
