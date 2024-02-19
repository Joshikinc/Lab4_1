import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class StringAnimation extends JPanel implements ActionListener {
    private static final int WIDTH = 800; // Ширина окна
    private static final int HEIGHT = 600; // Высота окна
    private static final int STRING_SPEED = 2; // Скорость движения строки
    private String[] strings = {"Picked", "up", "_JAVA_OPTIONS:", "-Xmx512M", "Ошибочка"};
    private int[] stringXPositions; // X-позиции для каждой строки
    private int[] stringYPositions; // Y-позиции для каждой строки
    private int[] stringVelocities; // Скорости движения для каждой строки
    public StringAnimation() {
        // Инициализация позиций и скоростей
        stringXPositions = new int[strings.length];
        stringYPositions = new int[strings.length];
        stringVelocities = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            // Начальные позиции и скорости выбираются случайным образом
            stringXPositions[i] = (int) (Math.random() * (WIDTH - 100));
            stringYPositions[i] = (int) (Math.random() * (HEIGHT - 30));
            stringVelocities[i] = (Math.random() > 0.5) ? STRING_SPEED : -STRING_SPEED;
        }

        Timer timer = new Timer(10, this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        for (int i = 0; i < strings.length; i++) {
            g.drawString(strings[i], stringXPositions[i], stringYPositions[i]);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < strings.length; i++) {
            // Обновление позиций строк
            stringXPositions[i] += stringVelocities[i];

            // Проверка достижения границ окна
            if (stringXPositions[i] < 0 || stringXPositions[i] > WIDTH - 100) {
                // Изменение направления движения строки
                stringVelocities[i] = -stringVelocities[i];
            }
        }

        repaint();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("String Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        StringAnimation animation = new StringAnimation();
        frame.add(animation);

        frame.setVisible(true);
    }
}