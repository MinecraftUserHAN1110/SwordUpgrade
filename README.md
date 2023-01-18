# SwordUpgrade
강화 플러그인

## ISSUE
Error Lists ( Fix / Fixing )

### Fixing
#### Code A1 - NullPointerException
Gui 클래스에서 강화는 성공하나 modify 메서드의 마지막 4줄에서 NullPointerException이 발생함

```java
Inventory newInv = p.getOpenInventory().getTopInventory();
newInv.setItem(4, item);
p.closeInventory();
p.openInventory(newInv);
```

이 부분에서 오류가 나는걸 보아 item이 null로 추정.

단, 코드를 호출하는부분이 이 뿐이다.

```java
this.modfiy(weapon, damage, up, p);
```

(인수는 이렇게 되있음)

```java
public void modfiy(ItemStack item, int damages, @NotNull int up, Player p) {
```

그럼으로 보아 weapon이 Null이라는건데
앞쪽에서 Null을 걸러주는
```java
if (!weapon.getType().equals(Material.AIR) || weapon.getType() != Material.AIR) {
```

이 코드가 있고

더 앞에서

```java
ItemStack weapon = e.getClickedInventory().getItem(4) == null ? new ItemStack(Material.AIR) : e.getClickedInventory().getItem(4);
```

로 null이면 강제로 공기로 만드는 코드가 있는데도 불구하고 오류가 있다.
