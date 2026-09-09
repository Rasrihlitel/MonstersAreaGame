import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Проверка высоты

        int height;
        while(true){
            System.out.print("Введите высота поля: ");
            height = scan.nextInt();
            if(height < 1){
                System.out.println("Высота не может быть меньше 1. Попробуйте снова.");
                continue;
            }
            else break;
        }

        //Проверка ширины

        int width;
        while(true){
            System.out.print("Введите ширину поля: ");
            width = scan.nextInt();
            if(width < 1){
                System.out.println("Ширина не может быть меньше 1. Попробуйте снова.");
                continue;
            }
            else break;
        }

        //Проверка монстров

        int countMonsters;
        while(true){
            System.out.print("Введите количество монстров: ");
            countMonsters = scan.nextInt();
            if(countMonsters < 2){
                System.out.println("Количество монстров не может быть меньше 2. Попробуйте снова.");
                continue;
            }
            else break;
        }

        //Проверка координат

        int[] xCoord = new int[countMonsters];
        int[] yCoord = new int[countMonsters];
        for(int i = 0; i < countMonsters; i++){
            while(true){
                System.out.print("Введите координаты X " + (i + 1) + " монстра: ");
                xCoord[i] = scan.nextInt();
                if(xCoord[i] < 0 || xCoord[i] >= width){
                    System.out.println("Координаты X должны быть в диапазоне поля. Попробуйте снова.");
                    continue;
                }
                else break;
            }
            while(true){
                System.out.print("Введите координаты Y " + (i + 1) + " монстра: ");
                yCoord[i] = scan.nextInt();
                if(yCoord[i] < 0 || yCoord[i] >= height){
                    System.out.println("Координаты Y должны быть в диапазоне поля. Попробуйте снова.");
                    continue;
                }
                else break;
            }
            boolean isPast = false;
            for(int j = 0; j < i; j++){
                if(xCoord[i] == xCoord[j] && yCoord[i] == yCoord[j]){
                    System.out.println("Координаты " + (i + 1) + " и " + (j + 1) + " монстра совпадают. Введите другие координаты.");
                    isPast = true;
                    break;
                }
            }
            if(isPast == true){
                i--;
                continue;
            }
        }

        //Само решение

        //Изначальная площадь
        int firstMinX = xCoord[0];
        int firstMinY = yCoord[0];
        int firstMaxX = xCoord[0];
        int firstMaxY = yCoord[0];
        for(int i = 1; i < countMonsters; i++){
            if(firstMinX > xCoord[i]) firstMinX = xCoord[i];
            if(firstMaxX < xCoord[i]) firstMaxX = xCoord[i];
            if(firstMinY > yCoord[i]) firstMinY = yCoord[i];
            if(firstMaxY < yCoord[i]) firstMaxY = yCoord[i];
        }
        int firstArea = (firstMaxX - firstMinX + 1) * (firstMaxY - firstMinY + 1);

        //Сдвиг всех монстров

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        for(int i = 0; i < countMonsters; i++){
            int tempX = xCoord[i];
            int tempY = yCoord[i];
            for(int d = 0; d < 4; d++){
                int newXCoord = xCoord[i] + dx[d];
                int newYCoord = yCoord[i] + dy[d];
                boolean isIt = true;
                for(int j = 0; j < countMonsters; j++) {
                    if (newXCoord == xCoord[j] && newYCoord == yCoord[j]) {
                        isIt = false;
                        break;
                    }
                }
                if(newXCoord < 0 || newXCoord > width || newYCoord < 0 || newYCoord > height) isIt = false;
                if(isIt == false) continue;
                else {
                    xCoord[i] = newXCoord;
                    yCoord[i] = newYCoord;
                    int finalMaxX = xCoord[0];
                    int finalMaxY = yCoord[0];
                    int finalMinX = xCoord[0];
                    int finalMinY = yCoord[0];
                    for(int j = 0; j < countMonsters; j++){
                        if(finalMinX > xCoord[j]) finalMinX = xCoord[j];
                        if(finalMaxX < xCoord[j]) finalMaxX = xCoord[j];
                        if(finalMinY > yCoord[j]) finalMinY = yCoord[j];
                        if(finalMaxY < yCoord[j]) finalMaxY = yCoord[j];
                    }
                    int finalArea = (finalMaxX - finalMinX + 1) * (finalMaxY - finalMinY + 1);
                    if(finalArea < firstArea) firstArea = finalArea;
                    xCoord[i] = tempX;
                    yCoord[i] = tempY;
                }
            }
        }
        System.out.println("Минимально возможная площадь: " + firstArea);

    }
}