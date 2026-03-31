package org.example.nordicStew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {
    private static final int MAX_ADDITIVES = 3;

    private JTable orderTable;
    private DefaultTableModel tableModel;
    private List<JCheckBox> checkBoxes;
    private JButton orderButton;

    private final List<String> selectedAdditives = new ArrayList<>();

    public Gui() {
        setTitle("Заказ Нордского рагу");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("История заказов"));
        tableModel = new DefaultTableModel(new String[]{"Время", "Название", "Цена (септимы)"}, 0);
        orderTable = new JTable(tableModel);
        orderTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout(5, 10));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Добавки (не более " + MAX_ADDITIVES + ")"));
        rightPanel.setPreferredSize(new Dimension(200, 0));

        JPanel checkBoxPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        checkBoxes = new ArrayList<>();
        String[] additiveNames = {"Огненный соус (+10)", "Двойная порция оленины (+20)",
                "Снежные ягоды (+5)", "Нордская лепешка (+7)"};
        for (String name : additiveNames) {
            JCheckBox cb = new JCheckBox(name);
            cb.addActionListener(this::onCheckBoxChanged);
            checkBoxPanel.add(cb);
            checkBoxes.add(cb);
        }
        rightPanel.add(checkBoxPanel, BorderLayout.CENTER);

        orderButton = new JButton("Оформить заказ");
        orderButton.addActionListener(this::onOrderButtonClick);
        rightPanel.add(orderButton, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.EAST);
    }
    

    private void onCheckBoxChanged(ActionEvent e) {
        int selectedCount = 0;
        for (JCheckBox cb : checkBoxes) {
            if (cb.isSelected()) selectedCount++;
        }
        if (selectedCount > MAX_ADDITIVES) {
            JCheckBox source = (JCheckBox) e.getSource();
            source.setSelected(false);
            JOptionPane.showMessageDialog(this,
                    "Можно выбрать не более " + MAX_ADDITIVES + " добавок.",
                    "Предупреждение",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            selectedAdditives.clear();
            for (JCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    String text = cb.getText();
                    String additive = text.split(" \\(")[0];
                    selectedAdditives.add(additive);
                }
            }
        }
    }


    private void onOrderButtonClick(ActionEvent e) {
        Dish dish = new NordicStew();
        for (String additive : selectedAdditives) {
            dish = wrapWithAdditive(dish, additive);
        }

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String description = dish.getDescription();
        int price = dish.getPrice();

        tableModel.addRow(new Object[]{time, description, price});
    }


    private Dish wrapWithAdditive(Dish dish, String additiveName) {
        switch (additiveName) {
            case "Огненный соус":
                return new FireSause(dish);
            case "Двойная порция оленины":
                return new DoubleVenison(dish);
            case "Снежные ягоды":
                return new SnowBerries(dish);
            case "Нордская лепешка":
                return new NordicFlatbread(dish);
            default:
                throw new IllegalArgumentException("Неизвестная добавка: " + additiveName);
        }
    }
}

