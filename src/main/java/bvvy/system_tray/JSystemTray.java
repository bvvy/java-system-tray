package bvvy.system_tray;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * 系统托盘
 */
public class JSystemTray {


    private final SystemTray systemTray;
    private final java.util.List<JTrayIcon> trayIcons = new ArrayList<>();


    public JSystemTray() {
        systemTray = SystemTray.getSystemTray();
    }

    public void add(JTrayIcon trayIcon) {

        try {
            systemTray.add(trayIcon.getTrayIcon());
        } catch (AWTException e) {
            throw new JSystemTrayException(e);
        }
    }

    public void remove(JTrayIcon trayIcon) {
        systemTray.remove(trayIcon.getTrayIcon());
    }

    public static boolean isSupported() {
        return SystemTray.isSupported();
    }

    public JTrayIcon[] getTrayIcons() {
        return this.trayIcons.toArray(new JTrayIcon[0]);
    }

    public java.util.List<JTrayIcon> getTrayIconList() {
        return this.trayIcons;
    }

    public Dimension getTrayIconSize() {
        return systemTray.getTrayIconSize();
    }

    public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        systemTray.addPropertyChangeListener(propertyName, listener);
    }

    public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        systemTray.removePropertyChangeListener(propertyName, listener);
    }

    public synchronized PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return systemTray.getPropertyChangeListeners(propertyName);
    }
}