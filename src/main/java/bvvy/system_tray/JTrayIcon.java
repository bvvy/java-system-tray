package bvvy.system_tray;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;

public class JTrayIcon {

    private final TrayIcon trayIcon;
    private JPopupMenu popupMenu;
    private final JDialog hiddenDialog;

    public JTrayIcon(Image image) {
        this.trayIcon = new TrayIcon(image);
        this.hiddenDialog = new JDialog((Frame) null);
        // 用一个隐藏的dialog承载popup menu
        hiddenDialog.setUndecorated(true);
        hiddenDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        hiddenDialog.setAlwaysOnTop(true);

        hiddenDialog.getContentPane().setLayout(null);

        hiddenDialog.setType(Window.Type.POPUP);

        hiddenDialog.pack();
        hiddenDialog.setBounds(0, 0, 0, 0);


        this.trayIcon.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showPopupMenu(e);
                }
            }
        });
    }

    public JTrayIcon(Image image, String tooltip) {
        this(image);
        this.setToolTip(tooltip);
    }

    public JTrayIcon(Image image, String tooltip, JPopupMenu popupMenu) {
        this(image);
        this.setToolTip(tooltip);
        this.setPopupMenu(popupMenu);
    }


    private void showPopupMenu(MouseEvent e) {
        if (popupMenu == null) {
            throw new JSystemTrayException("Popup menu is null, set popup menu first!");
        }
        int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();

        double scale = 96.0 / screenResolution;
        int x = (int) (e.getX() * scale);
        int y = (int) (e.getY() * scale);

        popupMenu.pack();
        int menuHeight = popupMenu.getPreferredSize().height;
        y = y - menuHeight;


        hiddenDialog.setLocation(x, y);
        hiddenDialog.setVisible(true);

        popupMenu.show(hiddenDialog, 0, 0);
        popupMenu.requestFocusInWindow();
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
        this.popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                hiddenDialog.setVisible(false);
                hiddenDialog.toBack();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

    }

    public TrayIcon getTrayIcon() {
        return this.trayIcon;
    }

    public void setImageAutoSize(boolean imageAutoSize) {
        this.trayIcon.setImageAutoSize(imageAutoSize);
    }

    public void setToolTip(String toolTip) {
        this.trayIcon.setToolTip(toolTip);
    }

    public String getToolTip() {
        return trayIcon.getToolTip();
    }

    public void setImage(Image image) {
        this.trayIcon.setImage(image);
    }

    public JPopupMenu getPopupMenu() {
        return this.popupMenu;
    }

    public Image getImage() {
        return trayIcon.getImage();
    }

    public boolean isImageAutoSize() {
        return trayIcon.isImageAutoSize();
    }

    public synchronized void addMouseListener(MouseListener listener) {
        trayIcon.addMouseListener(listener);
    }

    public synchronized void removeMouseListener(MouseListener listener) {
        trayIcon.removeMouseListener(listener);
    }

    public synchronized MouseListener[] getMouseListeners() {
        return trayIcon.getMouseListeners();
    }

    public synchronized void addMouseMotionListener(MouseMotionListener listener) {
        trayIcon.addMouseMotionListener(listener);
    }

    public synchronized void removeMouseMotionListener(MouseMotionListener listener) {
        trayIcon.removeMouseMotionListener(listener);
    }

    public synchronized MouseMotionListener[] getMouseMotionListeners() {
        return trayIcon.getMouseMotionListeners();
    }

    public String getActionCommand() {
        return trayIcon.getActionCommand();
    }

    public void setActionCommand(String command) {
        trayIcon.setActionCommand(command);
    }

    public synchronized void addActionListener(ActionListener listener) {
        trayIcon.addActionListener(listener);
    }

    public synchronized void removeActionListener(ActionListener listener) {
        trayIcon.removeActionListener(listener);
    }

    public void displayMessage(String caption, String text, TrayIcon.MessageType messageType) {
        trayIcon.displayMessage(caption, text, messageType);
    }

    public Dimension getSize() {
        return trayIcon.getSize();
    }

    public synchronized ActionListener[] getActionListeners() {
        return trayIcon.getActionListeners();
    }
}
