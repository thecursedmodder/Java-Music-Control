package net.cursedmodder.javatriggers.util.debug.watch;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class DebugWindow {

    private final JFrame frame;
    private final JTextPane textPane;

    // group -> (key -> value)
    private final Map<String, Map<String, DebugValue>> data = new ConcurrentHashMap<>();

    public DebugWindow() {
        frame = new JFrame("Debug Window");
        textPane = new JTextPane();

        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.WHITE);
        textPane.setCaretColor(Color.WHITE);
        textPane.setEditable(false);
        textPane.setFont(new Font("Consolas", Font.PLAIN, 14));

        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textPane), BorderLayout.CENTER);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

        // Refresh ~10 times/sec
        new Timer(100, e -> refresh()).start();
    }

    public void watch(String group, String key, Supplier<Object> supplier, Supplier<Color> colorSupplier) {
        data
                .computeIfAbsent(group, g -> new ConcurrentHashMap<>())
                .put(key, new DebugValue(supplier, colorSupplier));
    }

    private void refresh() {
        SwingUtilities.invokeLater(() -> {

            StringBuilder sb = new StringBuilder();

            for (var groupEntry : data.entrySet()) {
                sb.append("[").append(groupEntry.getKey()).append("]\n");

                for (var entry : groupEntry.getValue().entrySet()) {
                    Object val = entry.getValue().get();
                    sb.append(entry.getKey())
                            .append(": ")
                            .append(val)
                            .append("\n");
                }

                sb.append("\n");
            }

            textPane.setText(sb.toString());
            applyColors();
        });
    }

    private void applyColors() {
        var doc = textPane.getStyledDocument();
        String text = textPane.getText();

        for (var groupEntry : data.entrySet()) {
            for (var entry : groupEntry.getValue().entrySet()) {

                String key = entry.getKey();
                Object val = entry.getValue().get();
                Color color = entry.getValue().getColor();

                String search = key + ": " + val;
                int pos = text.indexOf(search);

                if (pos >= 0) {
                    var style = textPane.addStyle("style_" + key, null);
                    StyleConstants.setForeground(style, color);
                    doc.setCharacterAttributes(pos, search.length(), style, false);
                }
            }
        }
    }

    private static class DebugValue {
        Supplier<Object> supplier;
        Supplier<Color> colorSupplier;

        DebugValue(Supplier<Object> supplier, Supplier<Color> colorSupplier) {
            this.supplier = supplier;
            this.colorSupplier = colorSupplier;
        }

        Object get() {
            try {
                Object val = supplier.get();
                return val == null ? "null" : val;
            } catch (Exception e) {
                return "ERR";
            }
        }

        Color getColor() {
            try {
                return colorSupplier.get();
            } catch (Exception e) {
                return Color.RED;
            }
        }
    }
}
