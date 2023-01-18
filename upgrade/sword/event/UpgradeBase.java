package upgrade.sword.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import upgrade.sword.api.event.EventType;
import upgrade.sword.api.event.SetupEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class UpgradeBase {
    private Player p;

    private JavaPlugin plugin;

    //제련의 시약 1%
    private ItemStack upgradeItem;
    //제련의 시약 5%
    private ItemStack upgradeItem2;
    //제련의 시약 10%
    private ItemStack upgradeItem3;
    //제련의 시약 1%
    private ItemStack safetyItem;
    //제련의 시약 5%
    private ItemStack safetyItem2;
    //제련의 시약 10%
    private ItemStack safetyItem3;
    /**
     * 빛바랜 도안
     */
    private ItemStack supergrade;
    /**
     * 흑색 도안
     */
    private ItemStack supergrade2;
    /**
     * 녹색 도안
     */
    private ItemStack supergrade3;
    /**
     * 적색 도안
     */
    private ItemStack supergrade4;
    /**
     * 허름한 도안
     */
    private ItemStack supergrade5;
    private ItemStack blackgrade;
    /**
     * 알수없는 도안
     */
    private ItemStack unknowgrade;
    /**
     * 알수없는 도안
     */
    private ItemStack unknowgrade2;
    @Override
    public String toString() {
        return new String("upgrade.sword.event.UpgradeBase::1f");
    }
    static HashMap<UUID, Inventory> inv = new HashMap<UUID, Inventory>();

    public UpgradeBase(Player p) {
        this.p = p;
        GUI gui = new GUI(p, this);

        gui.setupInv(p);

        inv.put(p.getUniqueId(), gui.getInv());
        openInventory(p);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv.get(ent.getUniqueId()));
    }

    public UpgradeBase(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * 여기에 아이템 생성
     */
    public void setup() {
        SetupEvent event = new SetupEvent(this);
        event.setType(EventType.PRE);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }
        setupUpgradeItem();
        setupSafetyNoDeleteItem();
        setupSuperGradeItem();
        setupUnknownGrade();

        event.setType(EventType.POST);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
    }

    public void setupUnknownGrade() {
        unknowgrade = new ItemStack(Material.IRON_HOE);
        unknowgrade2 = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = unknowgrade.getItemMeta();
        ItemMeta meta2 = unknowgrade2.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "알수없는 도안 +5");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 한번에 끌어 올리는 도안.", ChatColor.GRAY + "1~10 강화 사이에 쓸수 있는 도안",
                ChatColor.GREEN + "10%의 확률로 성공하고 ", ChatColor.RED + "90%의 확률로 장비가 파괴된다 .", ChatColor.GRAY + "최대 제한 +10강화"));
        meta2.setDisplayName(ChatColor.RED + "알수없는 도안 +15");
        meta2.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 한번에 끌어 올리는 도안.", ChatColor.GRAY + "5~15 강화 사이에 쓸수 있는 도안",
                ChatColor.GREEN + "10%의 확률로 성공하고 ", ChatColor.RED + "90%의 확률로 장비가 파괴된다 .", ChatColor.GRAY + "최대 제한 +15강화"));
        unknowgrade.setItemMeta(meta);
        unknowgrade2.setItemMeta(meta2);
        unknowgrade.setDurability((short) 11);
        unknowgrade2.setDurability((short) 12);
    }

    /**
     * 기타 무기 스텟 업그레이드 도안들
     */
    public void setupSuperGradeItem() {
        supergrade = new ItemStack(Material.IRON_HOE);
        supergrade2 = new ItemStack(Material.IRON_HOE);
        supergrade3 = new ItemStack(Material.IRON_HOE);
        supergrade4 = new ItemStack(Material.NETHER_WARTS);
        supergrade5 = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta meta = supergrade.getItemMeta();
        ItemMeta meta2 = supergrade2.getItemMeta();
        ItemMeta meta3 = supergrade3.getItemMeta();
        ItemMeta meta4 = supergrade4.getItemMeta();
        ItemMeta meta5 = supergrade5.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "빛바랜 도안");
        meta2.setDisplayName(ChatColor.GRAY + "흙색 도안");
        meta3.setDisplayName(ChatColor.GREEN + "녹색 도안");
        meta4.setDisplayName(ChatColor.RED + "적색 도안");
        meta5.setDisplayName(ChatColor.GRAY + "허름한 도안");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 이끌어 주는 신비한 도안."));
        meta2.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 이끌어 주는 신비한 도안."));
        meta3.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 이끌어 주는 신비한 도안."));
        meta4.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 이끌어 주는 신비한 도안."));
        meta5.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 이끌어 주는 신비한 도안."));
        supergrade.setItemMeta(meta);
        supergrade2.setItemMeta(meta2);
        supergrade3.setItemMeta(meta3);
        supergrade4.setItemMeta(meta4);
        supergrade5.setItemMeta(meta5);
        supergrade.setDurability((short) 1);
        supergrade2.setDurability((short) 2);
        supergrade3.setDurability((short) 3);
        supergrade4.setDurability((short) 4);
        supergrade5.setDurability((short) 5);
        
        //검은 도안 추가 옵션
        blackgrade = new ItemStack(Material.IRON_HOE);
        ItemMeta blackmeta = blackgrade.getItemMeta();
        blackmeta.setDisplayName(ChatColor.GOLD + "검은 도안");
        blackmeta.setLore(Arrays.asList(ChatColor.GRAY + "무기의 힘을 한번더 이끌어 주는 신비한 도안."));
        blackgrade.setItemMeta(blackmeta);
        blackgrade.setDurability((short) 5);
    }

    /**
     * 공허의 도안
     */
    public void setupSafetyNoDeleteItem() {
        /**
         * 1%
         */
        safetyItem = new ItemStack(Material.FLINT);
        ItemMeta meta = safetyItem.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "공허의 도안 1%");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "강화 횟수를 복구 시켜주는 도안이다 ."));
        safetyItem.setItemMeta(meta);
        safetyItem.setDurability((short) 6);
        /**
         * 5%
         */
        safetyItem2 = new ItemStack(Material.FLINT);
        ItemMeta meta2 = safetyItem2.getItemMeta();
        meta2.setDisplayName(ChatColor.GRAY + "공허의 도안 5%");
        meta2.setLore(Arrays.asList(ChatColor.GRAY + "강화 횟수를 복구 시켜주는 도안이다 ."));
        safetyItem2.setItemMeta(meta2);
        safetyItem2.setDurability((short) 7);
        /**
         * 10%
         */
        safetyItem3 = new ItemStack(Material.FLINT);
        ItemMeta meta3 = safetyItem2.getItemMeta();
        meta3.setDisplayName(ChatColor.GRAY + "공허의 도안 10%");
        meta3.setLore(Arrays.asList(ChatColor.GRAY + "강화 횟수를 복구 시켜주는 도안이다 ."));
        safetyItem3.setItemMeta(meta3);
        safetyItem3.setDurability((short) 8);
    }

    /**
     * 제련의 시약 아이템마다 설정
     */
    public void setupUpgradeItem() {
        upgradeItem = new ItemStack(Material.NETHER_BRICK_ITEM);
        ItemMeta meta = upgradeItem.getItemMeta();

        //이름
        meta.setDisplayName(ChatColor.GRAY + "제련의 도안 1%");

        //설명
        meta.setLore(Arrays.asList(ChatColor.GRAY + "톱니의 제국 에서 훔친 강화 확률을 상승 시켜주는  미완성 도안 이다.                   1%"));

        //아이템 추가
        upgradeItem.setItemMeta(meta);
        upgradeItem.setDurability((short) 9);

        upgradeItem2 = new ItemStack(Material.NETHER_BRICK_ITEM);
        ItemMeta meta2 = upgradeItem2.getItemMeta();

        //이름
        meta.setDisplayName(ChatColor.GRAY + "제련의 도안 5%");

        //설명
        meta.setLore(Arrays.asList(ChatColor.GRAY + "톱니의 제국 에서 훔친 강화 확률을 상승 시켜주는  미완성 도안을 개조한 도안 이다.                   5%"));

        //아이템 추가
        upgradeItem2.setItemMeta(meta2);
        upgradeItem2.setDurability((short) 10);

        upgradeItem3 = new ItemStack(Material.NETHER_BRICK_ITEM);
        ItemMeta meta3 = upgradeItem3.getItemMeta();

        //이름
        meta3.setDisplayName(ChatColor.GRAY + "제련의 도안 10%");

        //설명
        meta3.setLore(Arrays.asList(ChatColor.GRAY + "톱니의 제국 에서 개발한 확률을 상승 시켜주는 도안의 완성본이다. 10%"));

        upgradeItem3.setItemMeta(meta3);
        upgradeItem3.setDurability((short) 11);
    }

    public static UpgradeBase getThis(JavaPlugin javaPlugin) {
        return new UpgradeBase(javaPlugin);
    }

}
