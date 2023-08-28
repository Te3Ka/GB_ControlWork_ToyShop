import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyShop {
    private List<Toy> toys;

    public ToyShop() {
        toys = new ArrayList<>();
    }

    /**
     * ��������� ����� ������� � ���������� ���������� � ����� ������ ��� ���������
     * @param id - ���������� �����
     * @param name - �������� �������
     * @param quantity - ����������
     * @param percent - ���� ��������� ��� ���������
     */
    public void addToy(int id, String name, int quantity, double percent) {
        Toy toy = new Toy(id, name, quantity, percent);
        toys.add(toy);
    }

    /**
     * ��������� ����� ��������� �������
     * @param id - ���������� ����� �������
     * @param newPercent - ����� �������� ����� ���������
     */
    public void updateToyPercent(int id, double newPercent) {
        for(Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setPercent(newPercent);
                return;
            }
            System.out.println("������� � ��������� id �� �������");
        }
    }

    /**
     * ���������� ��������� �������
     */
    public void playGame() {
        Toy prize = selectPrize();
        if (prize == null) {
            System.out.println("��� ��������� ������� ��� ���������.");
            return;
        }
        prize.setPercent(
                ((prize.getQuantity() / prize.getPercent()) * 100) - ((prize.getQuantity() - 1) / prize.getPercent() * 100)
        );
        prize.setDecreaseQuantity();
        if (prize.getQuantity() <= 0)
            toys.remove(prize);
        savePrizeToFile(prize);
    }

    /**
     * ����� ��� ��������� �������.
     * ��������� ����� "�������" ��������� ������� (����� ���� ������ 100)
     * ����� ���������� ��������� ����� �� 0 �� ����� ����� ���������.
     * ����� �����������, ����� ������� ������ ��� �����.
     * @return
     */
    private Toy selectPrize() {
        double totalPercent = 0;
        for (Toy toy : toys) {
            totalPercent += toy.getPercent();
        }
        if (totalPercent <= 0) {
            return null;
        }

        double random = new Random().nextDouble() * totalPercent;
        double summaryPercent = 0;
        for (Toy toy : toys) {
            summaryPercent += toy.getPercent();
            if (random <= summaryPercent) {
                return toy;
            }
        }
        return null;
    }


    public void savePrizeToFile(Toy saveToy) {
        try (FileWriter writer = new FileWriter("prizes.txt", true)) {
            writer.write(saveToy.getId() + " - " + saveToy.getName() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyShop shop = new ToyShop();
        shop.addToy(1, "�����", 2, 50);
        shop.addToy(2, "���", 3, 80);
        shop.addToy(3, "�����������", 3, 40);
        shop.addToy(4, "�������", 3, 90);
        shop.addToy(5, "�����", 2, 2);
        shop.addToy(6, "������", 2, 8);
        shop.addToy(7, "��������", 4, 30);

        while (!shop.toys.isEmpty()) {
            shop.playGame();
        }
        try (FileWriter writer = new FileWriter("prizes.txt", true)) {
            writer.write("=".repeat(42));
            System.out.println("�������� ���������!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
