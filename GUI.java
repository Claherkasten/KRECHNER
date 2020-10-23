import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class GUI extends JFrame {
    Random x = new Random();
    String calculationType = "";
    int numberRange;
    int aufruf = 1;
    long start;
    long end;

    ArrayList<String[]> tasks = new ArrayList<>();

    JPanel titlePage;
    JPanel taskPage;

    JLabel title;
    JTextArea task;

    JTextField input;

    JMenuBar menu;
    JMenu rechenart;
    JMenu zahlenbereich;
    JMenuItem plus;
    JMenuItem minus;
    JMenuItem mal;
    JMenuItem geteilt;
    JMenuItem gemischt;
    JMenuItem einhundert;
    JMenuItem zweihundert;
    JMenuItem fuenfhundert;
    JMenuItem tausend;
    JMenuItem zehntausend;

    Dimension screensize;

    public GUI(){
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screensize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        super.setTitle("KRECHNEN");
        setResizable(true);
        setLayout(null);

        createTitlePage();

        setVisible(true);
    }

    private void createTitlePage(){
        titlePage = new JPanel();
        titlePage.setLayout(null);
        titlePage.setBounds(0, 0, screensize.width, screensize.height);

        title = new JLabel("KRECHNEN");
        title.setFont(new Font("Arial Black", Font.BOLD, 100));
        title.setBounds(screensize.width / 2 - 300, screensize.height / 2 - 100, 700, 100);

        MenuBarHandler mbl = new MenuBarHandler();

        menu = new JMenuBar();

        rechenart = new JMenu("Rechenart");
        zahlenbereich = new JMenu("Zahlenbereich");

        plus = new JMenuItem("+");
        minus = new JMenuItem("-");
        mal = new JMenuItem("*");
        geteilt = new JMenuItem("/");
        gemischt = new JMenuItem("gemischt");
        einhundert = new JMenuItem("0 - 100");
        zweihundert = new JMenuItem("0 - 200");
        fuenfhundert = new JMenuItem("0 - 500");
        tausend = new JMenuItem("0 - 1000");
        zehntausend = new JMenuItem("0 - 10000");

        plus.addActionListener(mbl);
        minus.addActionListener(mbl);
        mal.addActionListener(mbl);
        geteilt.addActionListener(mbl);
        gemischt.addActionListener(mbl);
        einhundert.addActionListener(mbl);
        zweihundert.addActionListener(mbl);
        fuenfhundert.addActionListener(mbl);
        tausend.addActionListener(mbl);
        zehntausend.addActionListener(mbl);

        rechenart.add(plus);
        rechenart.add(minus);
        rechenart.add(mal);
        rechenart.add(geteilt);
        rechenart.add(gemischt);
        zahlenbereich.add(einhundert);
        zahlenbereich.add(zweihundert);
        zahlenbereich.add(fuenfhundert);
        zahlenbereich.add(tausend);
        zahlenbereich.add(zehntausend);
        menu.add(rechenart);
        menu.add(zahlenbereich);
        setJMenuBar(menu);

        titlePage.add(title);

        add(titlePage);
    }

    private void createTaskPage(){
        if(aufruf == 1) {
            setJMenuBar(null);
            remove(menu);
            remove(titlePage);
            validate();

            taskPage = new JPanel();
            taskPage.setLayout(null);
            taskPage.setBounds(0, 0, screensize.width, screensize.height);

            task = new JTextArea("", 1, 10);
            task.setFont(new Font("Serif", Font.PLAIN, 30));
            task.setBounds(screensize.width / 2 - 100, screensize.height / 2 - 15, 200, 35);
            task.setOpaque(false);

            input = new JTextField();
            input.setBounds(task.getX() + task.getWidth(), task.getY(), 100, 30);

            taskPage.add(task);
            taskPage.add(input);
            add(taskPage);
            input.addKeyListener(new KeyHandler());
            //setFocusable(true);
            input.requestFocus();

            createTasks();

            start = System.currentTimeMillis();
        }

        task.setText(tasks.get(aufruf - 1)[0]);
    }

    private void createTasks(){
        String[] zwischenspeicher;
        String rechner = "";
        int gconclusion = 0;
        Random x = new Random();
        int a = 0;
        int b = 0;
        int conclusion = 0;
        ArrayList<Integer> teiler;
        for(int i = 0; i < 10; i++) {
            switch (calculationType) {
                case "+" -> {
                    a = x.nextInt(numberRange + 1);
                    b = x.nextInt((numberRange - a) + 1);
                    conclusion = a + b;
                }
                case "-" -> {
                    a = x.nextInt(numberRange + 1);
                    b = x.nextInt(a + 1);
                    conclusion = a - b;
                }
                case "*" -> {
                    a = x.nextInt(((int) Math.sqrt(numberRange)) + 1);
                    b = x.nextInt(((int) Math.sqrt(numberRange)) + 1);
                    conclusion = a * b;
                }
                case "/" -> {
                    a = x.nextInt(numberRange + 1);
                    teiler = teiler(a);
                    b = teiler.get(x.nextInt(teiler.size()));
                    conclusion = a / b;
                }
                case "gemischt" -> {
                    int rechnen = x.nextInt(4);
                    switch (rechnen) {
                        case 0 -> {
                            a = x.nextInt(numberRange + 1);
                            b = x.nextInt((numberRange - a) + 1);
                            gconclusion = a + b;
                            rechner = "+";
                        }
                        case 1 -> {
                            a = x.nextInt(numberRange + 1);
                            b = x.nextInt(a + 1);
                            gconclusion = a - b;
                            rechner = "-";
                        }
                        case 2 -> {
                            a = x.nextInt(((int) Math.sqrt(numberRange)) + 1);
                            b = x.nextInt(((int) Math.sqrt(numberRange)) + 1);
                            gconclusion = a * b;
                            rechner = "*";
                        }
                        case 3 -> {
                            a = x.nextInt(numberRange + 1);
                            teiler = teiler(a);
                            b = teiler.get(x.nextInt(teiler.size()));
                            gconclusion = (int) a / b;
                            rechner = "/";
                        }
                    }
                }
                default -> {
                    a = 0;
                    b = 0;
                    conclusion = 0;
                }
            }
            if(calculationType.equals("gemischt")){
                zwischenspeicher = new String[]{
                        a + " " + rechner + " " + b + " = ",
                        Integer.toString(gconclusion)
                };
            }else {
                zwischenspeicher = new String[]{
                        a + " " + calculationType + " " + b + " = ",
                        Integer.toString(conclusion)
                };
            }
            tasks.add(zwischenspeicher);
        }
    }

    private static ArrayList<Integer> teiler(int n){
        ArrayList<Integer> teiler = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(n % i == 0){
                teiler.add(i);
            }
        }
        return teiler;
    }

    private class MenuBarHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == plus){
                calculationType = "+";
            }else if(event.getSource() == minus){
                calculationType = "-";
            }else if(event.getSource() == mal){
                calculationType = "*";
            }else if(event.getSource() == geteilt){
                calculationType = "/";
            }else if(event.getSource() == gemischt){
                calculationType = "gemischt";
            }else if(event.getSource() == einhundert){
                if(calculationType.isEmpty()){
                    JOptionPane.showConfirmDialog(null, "Du hast keine Rechenart gewählt!",
                            "Rechenart vergessen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }else{
                    numberRange = 100;
                    createTaskPage();
                }
            }else if(event.getSource() == zweihundert){
                if(calculationType.isEmpty()){
                    JOptionPane.showConfirmDialog(null, "Du hast keine Rechenart gewählt!",
                            "Rechenart vergessen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }else{
                    numberRange = 200;
                    createTaskPage();
                }
            }else if(event.getSource() == fuenfhundert){
                if(calculationType.isEmpty()){
                    JOptionPane.showConfirmDialog(null, "Du hast keine Rechenart gewählt!",
                            "Rechenart vergessen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }else{
                    numberRange = 500;
                    createTaskPage();
                }
            }else if(event.getSource() == tausend){
                if(calculationType.isEmpty()){
                    JOptionPane.showConfirmDialog(null, "Du hast keine Rechenart gewählt!",
                            "Rechenart vergessen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }else{
                    numberRange = 1000;
                    createTaskPage();
                }
            }else if(event.getSource() == zehntausend) {
                if (calculationType.isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "Du hast keine Rechenart gewählt!",
                            "Rechenart vergessen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                } else {
                    numberRange = 10000;
                    createTaskPage();
                }
            }
        }
    }

    private class KeyHandler implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(aufruf <= 9) {
                    if (input.getText().equals(tasks.get(aufruf - 1)[1])) {
                        aufruf++;
                        input.setText("");
                        createTaskPage();
                    } else {
                        JOptionPane.showConfirmDialog(null, "Falsche Antwort", "",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        input.setText("");
                    }
                }else{
                    // System.out.println("Du hast " + aufruf + "Aufgaben gelöst");
                    end = System.currentTimeMillis();
                    JOptionPane.showConfirmDialog(null, "Du hast " + (int) (end - start) / 1000 + " Sekunden gebrauchht.",
                        "Alle Aufgaben gelöst", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    remove(taskPage);
                    validate();
                    tasks.clear();
                    aufruf = 1;
                    createTitlePage();
                }
            }
            /*if(e.getKeyCode() == KeyEvent.VK_NUMPAD1){
                input.setText(input.getText() + "1");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD2){
                input.setText(input.getText() + "2");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                input.setText(input.getText() + "3");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                input.setText(input.getText() + "4");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                input.setText(input.getText() + '5');
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                input.setText(input.getText() + "6");
            }if(e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                input.setText(input.getText() + "7");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                input.setText(input.getText() + "8");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                input.setText(input.getText() + "9");
            }
            if(e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                input.setText(input.getText() + "0");
            }*/
            if(e.getKeyCode() == KeyEvent.VK_1){
                input.setText(input.getText() + "1");
            }
            if(e.getKeyCode() == KeyEvent.VK_2){
                input.setText(input.getText() + "2");
            }
            if(e.getKeyCode() == KeyEvent.VK_3) {
                input.setText(input.getText() + "3");
            }
            if(e.getKeyCode() == KeyEvent.VK_4) {
                input.setText(input.getText() + "4");
            }
            if(e.getKeyCode() == KeyEvent.VK_5) {
                input.setText(input.getText() + '5');
            }
            if(e.getKeyCode() == KeyEvent.VK_6) {
                input.setText(input.getText() + "6");
            }
            if(e.getKeyCode() == KeyEvent.VK_7) {
                input.setText(input.getText() + "7");
            }
            if(e.getKeyCode() == KeyEvent.VK_8) {
                input.setText(input.getText() + "8");
            }
            if(e.getKeyCode() == KeyEvent.VK_9) {
                input.setText(input.getText() + "9");
            }
            if(e.getKeyCode() == KeyEvent.VK_0) {
                input.setText(input.getText() + "0");
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {

        }
    }
}
