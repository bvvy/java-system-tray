package bvvy;

import bvvy.system_tray.JSystemTray;
import bvvy.system_tray.JTrayIcon;

import javax.swing.*;
import java.awt.*;

public class JSystemTrayTest {

    public static void main(String[] args) {
        new JSystemTrayTest().testJSystemTray();
    }


    public void testJSystemTray() {
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

}
