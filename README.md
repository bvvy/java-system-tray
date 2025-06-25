# Java System Tray (Unicode Fix)

A lightweight **Swing-based** system tray implementation that solves Unicode character rendering issues (e.g., emojis, CJK, symbols) in Java’s native `SystemTray`.

### 🔍 **Why?**
Java’s default `SystemTray` struggles with Unicode menus/tooltips (displays garbled text). This library replaces it with a reliable `JPopupMenu`-based solution.

### ✨ **Features**
✅ **Full Unicode support** – Correctly displays emojis, non-Latin scripts, etc.  
✅ **Pure Swing** – No JNI/OS-specific hacks.  
✅ **Drop-in compatible** – Matches Java’s `SystemTray` API style.  
✅ **Tiny footprint** – Zero extra dependencies.

### 🛠️ **Usage**
```java

public static void main(String[] args) {
    JSystemTray systemTray = new JSystemTray();
    Image icon = Toolkit.getDefaultToolkit().getImage(JSystemTrayTest.class.getResource("/vite.png"));

    JPopupMenu popupMenu = new JPopupMenu();
    JTrayIcon trayIcon = new JTrayIcon(icon);

    ImageIcon imageIcon = new ImageIcon(icon);
    Image scaledInstance = imageIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    JMenuItem test = new JMenuItem("测试");
    test.addActionListener(e -> trayIcon.displayMessage("Vite", "测试", TrayIcon.MessageType.INFO));
    popupMenu.add(test, new ImageIcon(scaledInstance));
    popupMenu.add(new JMenuItem("关于"), new ImageIcon(scaledInstance));
    popupMenu.addSeparator();
    popupMenu.add(new JMenuItem("这是一个长的"), new ImageIcon(scaledInstance));
    popupMenu.addSeparator();
    JMenuItem exitItem = new JMenuItem("退出");
    exitItem.addActionListener(e -> {
        System.exit(0);
    });
    popupMenu.add(exitItem);

    popupMenu.setPreferredSize(new Dimension(150, popupMenu.getPreferredSize().height));
    trayIcon.setImageAutoSize(true);
    trayIcon.setPopupMenu(popupMenu);
    trayIcon.setToolTip("Vite一个打包工具");

    systemTray.add(trayIcon);
}


```

### 📦 **Install**

Gradle
```gradle
implementation("io.github.bvvy:java-system-tray:1.0.0")
```

Maven
```xml
<dependency>
    <groupId>io.github.bvvy</groupId>
    <artifactId>java-system-tray</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

### Need more?
- **Found a bug?** Open an [issue](https://github.com/bvvy/java-system-tray/issues).

---

This format:
1. **Solves curiosity** (explains the "why" upfront)
2. **Highlights technical uniqueness** (Unicode + Swing)
3. **Provides immediate value** (copy-paste usage example)

