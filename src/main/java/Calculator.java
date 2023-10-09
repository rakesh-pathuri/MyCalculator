import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import javax.swing.*;
import java.lang.Math;

public class Calculator {

    private static final int WINDOW_WIDTH = 410;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 70;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 60;

    private JFrame window;
    private JComboBox<String> comboCalcType, comboTheme;
    private JTextField inText;
    private JButton btnC, btnBack, btnMod, btnDiv, btnMul, btnSub, btnAdd,
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnPoint, btnEqual, btnRoot, btnPower, btnLog;

    private char opt = ' ';
    private boolean go = true;
    private boolean addWrite = true;
    private double val = 0;

    public Calculator() {
        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);

        comboTheme = initCombo(new String[]{"Simple", "Colored", "DarkTheme"}, 230, 30, "Theme", this::themeSwitchEventConsumer);
        comboCalcType = initCombo(new String[]{"Standard", "Scientific"}, 20, 30, "Calculator type", this::calcTypeSwitchEventConsumer);

        int[] x = {MARGIN_X, MARGIN_X + 90, 200, 290, 380};
        int[] y = {MARGIN_Y, MARGIN_Y + 100, MARGIN_Y + 180, MARGIN_Y + 260, MARGIN_Y + 340, MARGIN_Y + 420};

        inText = initTextField("0", x[0], y[0]);
        btnC = initButton("C", x[0], y[1], this::clearButtonClick);
        btnBack = initButton("<-", x[1], y[1], this::backspaceButtonClick);
        btnMod = initButton("%", x[2], y[1], this::modButtonClick);
        btnDiv = initButton("/", x[3], y[1], this::divisionButtonClick);
        btn7 = initButton("7", x[0], y[2], this::numberButtonClick);
        btn8 = initButton("8", x[1], y[2], this::numberButtonClick);
        btn9 = initButton("9", x[2], y[2], this::numberButtonClick);
        btnMul = initButton("*", x[3], y[2], this::multiplicationButtonClick);
        btn4 = initButton("4", x[0], y[3], this::numberButtonClick);
        btn5 = initButton("5", x[1], y[3], this::numberButtonClick);
        btn6 = initButton("6", x[2], y[3], this::numberButtonClick);
        btnSub = initButton("-", x[3], y[3], this::subtractionButtonClick);
        btn1 = initButton("1", x[0], y[4], this::numberButtonClick);
        btn2 = initButton("2", x[1], y[4], this::numberButtonClick);
        btn3 = initButton("3", x[2], y[4], this::numberButtonClick);
        btnAdd = initButton("+", x[3], y[4], this::additionButtonClick);
        btnPoint = initButton(".", x[0], y[5], this::pointButtonClick);
        btn0 = initButton("0", x[1], y[5], this::numberButtonClick);
        btnEqual = initButton("=", x[2], y[5], this::equalButtonClick);
        btnEqual.setSize(2 * BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        btnRoot = initButton("√", x[4], y[1], this::squareRootButtonClick);
        btnPower = initButton("pow", x[4], y[2], this::powerButtonClick);
        btnPower.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        btnLog = initButton("ln", x[4], y[3], this::logButtonClick);
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private JTextField initTextField(String text, int x, int y) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, 350, 70);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
        window.add(textField);
        return textField;
    }

    private JComboBox<String> initCombo(String[] items, int x, int y, String toolTip, Consumer<ItemEvent> eventConsumer) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(eventConsumer::accept);
        window.add(combo);
        return combo;
    }

    private JButton initButton(String label, int x, int y, ActionListener event) {
        JButton button = new JButton(label);
        button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(event);
        button.setFocusable(false);
        window.add(button);
        return button;
    }

    private void clearButtonClick(ActionEvent event) {
        repaintFont();
        inText.setText("0");
        opt = ' ';
        val = 0;
    }

    private void backspaceButtonClick(ActionEvent event) {
        repaintFont();
        String str = inText.getText();
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < (str.length() - 1); i++) {
            str2.append(str.charAt(i));
        }
        if (str2.toString().equals("")) {
            inText.setText("0");
        } else {
            inText.setText(str2.toString());
        }
    }

    private void modButtonClick(ActionEvent event) {
        repaintFont();
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '%';
                go = false;
                addWrite = false;
            }
    }

    private void divisionButtonClick(ActionEvent event) {
        repaintFont();
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '/';
                go = false;
                addWrite = false;
            } else {
                opt = '/';
            }
    }

    private void numberButtonClick(ActionEvent event) {
        repaintFont();
        JButton button = (JButton) event.getSource();
        String buttonText = button.getText();
        if (addWrite) {
            if (Pattern.matches("[0]*", inText.getText())) {
                inText.setText(buttonText);
            } else {
                inText.setText(inText.getText() + buttonText);
            }
        } else {
            inText.setText(buttonText);
            addWrite = true;
        }
        go = true;
    }

    private void multiplicationButtonClick(ActionEvent event) {
        repaintFont();
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '*';
                go = false;
                addWrite = false;
            } else {
                opt = '*';
            }
    }

    private void subtractionButtonClick(ActionEvent event) {
        repaintFont();
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '-';
                go = false;
                addWrite = false;
            } else {
                opt = '-';
            }
    }

    private void additionButtonClick(ActionEvent event) {
        repaintFont();
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '+';
                go = false;
                addWrite = false;
            } else {
                opt = '+';
            }
    }

    private void pointButtonClick(ActionEvent event) {
        repaintFont();
        if (addWrite) {
            if (!inText.getText().contains(".")) {
                inText.setText(inText.getText() + ".");
            }
        } else {
            inText.setText("0.");
            addWrite = true;
        }
        go = true;
    }

    private void equalButtonClick(ActionEvent event) {
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '=';
                addWrite = false;
            }
    }

    private void squareRootButtonClick(ActionEvent event) {
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = Math.sqrt(Double.parseDouble(inText.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = "√";
                addWrite = false;
            }
    }

    private void powerButtonClick(ActionEvent event) {
        repaintFont();
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = calc(val, inText.getText(), opt);
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = '^';
                go = false;
                addWrite = false;
            } else {
                opt = '^';
            }
    }

    private void logButtonClick(ActionEvent event) {
        if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
            if (go) {
                val = Math.log(Double.parseDouble(inText.getText()));
                if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
                    inText.setText(String.valueOf((int) val));
                } else {
                    inText.setText(String.valueOf(val));
                }
                opt = 'l';
                addWrite = false;
            }
    }

    public double calc(double x, String input, char opt) {
        inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
        double y = Double.parseDouble(input);
        switch (opt) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            case '%':
                return x % y;
            case '^':
                return Math.pow(x, y);
            default:
                inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
                return y;
        }
    }

    private void themeSwitchEventConsumer(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String selectedTheme = (String) event.getItem();
            switch (selectedTheme) {
                case "Simple":
                    setSimpleTheme();
                    break;
                case "Colored":
                    setColoredTheme();
                    break;
                case "DarkTheme":
                    setDarkTheme();
                    break;
                default:
                    break;
            }
        }
    }

    private void setSimpleTheme() {
        window.getContentPane().setBackground(new Color(240, 240, 240));
        inText.setBackground(Color.WHITE);
    }

    private void setColoredTheme() {
        window.getContentPane().setBackground(new Color(255, 232, 186));
        inText.setBackground(Color.LIGHT_GRAY);
    }

    private void setDarkTheme() {
        window.getContentPane().setBackground(new Color(30, 30, 30));
        inText.setBackground(Color.DARK_GRAY);
        inText.setForeground(Color.WHITE);
    }

    private void calcTypeSwitchEventConsumer(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String selectedCalcType = (String) event.getItem();
            if (selectedCalcType.equals("Scientific")) {
                setScientificMode();
            } else {
                setStandardMode();
            }
        }
    }

    private void setStandardMode() {
        setSizeAndPosition(350, 600);
        hideScientificButtons();
    }

    private void setScientificMode() {
        setSizeAndPosition(500, 600);
        showScientificButtons();
    }

    private void setSizeAndPosition(int width, int height) {
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
    }

    private void hideScientificButtons() {
        btnRoot.setVisible(false);
        btnPower.setVisible(false);
        btnLog.setVisible(false);
    }

    private void showScientificButtons() {
        btnRoot.setVisible(true);
        btnPower.setVisible(true);
        btnLog.setVisible(true);
    }

    private void repaintFont() {
        inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
