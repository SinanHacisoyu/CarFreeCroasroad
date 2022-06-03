import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.*;

//Sinan Hacisoyu

public class CarFreeCroasroad extends JPanel {
    private ArrayList<Car> cars;
    private Thread CarThread;
    private Semaphore semaphore;

    public CarFreeCroasroad() {
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 800);
        panel.setVisible(true);

        cars = new ArrayList<>();
        semaphore = new Semaphore(1);

        CarThread = new Thread(new Runnable() {
            @Override
            public void run() { // Adding and moving
                try {
                    while (true) {
                        Thread.sleep(1300);//adding new car in each 1.3 seconds
                        Car car = new Car();
                        cars.add(car);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    while (true) {
                                        Thread.sleep(200);
                                        moveCar(car);
                                        repaint();
                                    }
                                } catch (InterruptedException e) {
                                    System.out.println("Error: " + e.getLocalizedMessage() + " " + e.getMessage() + " " + e.getCause());
                                }
                            }
                        }).start();
                        repaint();
                    }
                } catch (IllegalThreadStateException | InterruptedException e) {
                    System.out.println("Error: " + e.getLocalizedMessage() + " " + e.getMessage() + " " + e.getCause());
                }
            }
        });
        //starting thread
        CarThread.start();
    }

    public void moveCar(Car car) {
        try {
            if (car.getDirection() == 0) { // if the car cames from the top
                int newY = car.getY() + car.getSpeed(); // to make a move the car from top to bottom
                //when the car going out of the yellow intersection point, semafore became release and the movement keeps going
                //hasSemaphoer: to understand if that car has acauired and not released it yet
                if (car.isHasSemaphore()) {
                    car.setY(newY); 
                    if (car.getY() >= 405) {
                        car.setHasSemaphore(false);
                        car.setFinished(true); // done with the semaphore (aquire and release)
                        semaphore.release();
                }
                } else if (newY >= 305 && !car.isFinished()) { //If the car has not finished the cycle yet
                    if (semaphore.availablePermits() > 0) { // if semaphore counter is not 0, acquire the semaphore
                        semaphore.acquire();
                        car.setHasSemaphore(true);
                    } else { //move
                        newY = car.getY();
                    }
                 // if car is not in the yellow intersection
                } else { // move
                    car.setY(newY);
                }
                
            } else if (car.getDirection() == 1) { // if the car cames from the right and same as first if loop
                int newX = car.getX() - car.getSpeed();
                if (car.isHasSemaphore()) {
                    car.setX(newX);
                    if (car.getX() <= 325) {
                        car.setHasSemaphore(false);
                        car.setFinished(true);
                        semaphore.release();

                    }
                } else if (newX <= 425 && !car.isFinished()) {
                    if (semaphore.availablePermits() > 0) {
                        semaphore.acquire();
                        car.setHasSemaphore(true);

                    } else {
                        newX = car.getX();
                    }
                    car.setX(newX);
                } else {
                    car.setX(newX);
                }
                
            } else if (car.getDirection() == 2) { // if the car cames from the bottom and same as first if loop
                int newY = car.getY() - car.getSpeed();
                if (car.isHasSemaphore()) {
                    car.setY(newY);
                    if (car.getY() <= 325) {
                        car.setHasSemaphore(false);
                        car.setFinished(true);
                        semaphore.release();
                    }
                } else if (newY <= 425 && !car.isFinished()) {
                    if (semaphore.availablePermits() > 0) {
                        semaphore.acquire();
                        car.setHasSemaphore(true);
  
                    } else {
                        newY = car.getY();
                    }
                } else {
                    car.setY(newY);
                }
                
            } else { // if the car cames from the left and same as first if loop
                int newX = car.getX() + car.getSpeed();
                if (car.isHasSemaphore()) {
                    car.setX(newX);
                    if (car.getX() >= 405) {
                        car.setHasSemaphore(false);
                        car.setFinished(true);
                        semaphore.release();

                    }
                } else if (newX >= 305 && !car.isFinished()) {
                    if (semaphore.availablePermits() > 0) {
                        semaphore.acquire();
                        car.setHasSemaphore(true);

                    } else {
                        newX = car.getX();
                    }
                    car.setX(newX);
                } else {
                    car.setX(newX);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 325, 800, 100);
        g.setColor(Color.GRAY);
        g.fillRect(325, 0, 100, 800);
        g.setColor(Color.YELLOW);
        g.fillRect(325, 325, 100, 100);

        for (int i = 0; i < cars.size(); i++) {
            int currentDirection = cars.get(i).getDirection(); //direction 0-->Top 1-->Right 2-->Bottom 3-->Left
            if (currentDirection == 0) { // if car comes from top, color is green
                g.setColor(Color.green);
            } else if (currentDirection == 1) { // if car comes from right, color is green
                g.setColor(Color.red);
            } else if (currentDirection == 2) { // if car comes from bottom, color is green
                g.setColor(Color.blue);
            } else { // if car comes from left, color is green
                g.setColor(Color.pink);
            }
            g.fillRect(cars.get(i).getX(), cars.get(i).getY(), 20, 20);
        }
    }
}
