package com.problems.crackcode.kata.threading.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongRunningTasks {
    static Future<?> runningTask = null;

    public static void main(String[] args) {

        // Create an ExecutorService
        ExecutorService backgroundExec = Executors.newCachedThreadPool();


        // Create a new JFrame
        JFrame frame = new JFrame("Long Running Task Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);


        // Create a new JButton
        JButton startLongRunningTaskBttn = new JButton("Start Long Running Task");


        System.out.println("This is running in : " + Thread.currentThread().getName());
        startLongRunningTaskBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLongRunningTaskBttn.setEnabled(false);
                startLongRunningTaskBttn.setText("busy...");
                if (runningTask == null) {
                    runningTask = backgroundExec.submit(new Runnable() {
                        @Override
                        public void run() {

                            while (true) {
                                if (Thread.interrupted()) {
                                    break;
                                }
                                try {
                                    doSomeLongComputations();
                                } finally {
                                    GUIExecutor.getInstance().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            startLongRunningTaskBttn.setEnabled(true);
                                            startLongRunningTaskBttn.setText("Done !!!");
                                        }
                                    });
                                }
                            }
                        }
                    });
                } else {
                    startLongRunningTaskBttn.setText("Already working on something !");
                    startLongRunningTaskBttn.setEnabled(true);
                    startLongRunningTaskBttn.setText("Start Long Running Task");
                }
            }
        });


        JButton cancelButton = new JButton("Cancel Taskz");

        cancelButton.addActionListener(new

                                               ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       System.out.println(Thread.currentThread().getName());
                                                       if (runningTask != null && !runningTask.isCancelled()) {
                                                           System.out.println("Cancelling running task");
                                                           cancelButton.setEnabled(false);
                                                           cancelButton.setText("Cancelling !");
                                                           runningTask.cancel(true);
                                                           cancelButton.setText("Cancelled !");
                                                       } else {
                                                           System.out.println("Nothing to cancel");
                                                           cancelButton.setText("No task was running");
                                                           cancelButton.setText("Cancel Taskz");
                                                       }
                                                       cancelButton.setEnabled(true);
                                                   }
                                               });

        // Add the startLongRunningTaskBttn to the JFrame
        frame.getContentPane().

                add(startLongRunningTaskBttn, BorderLayout.NORTH);
        frame.getContentPane().

                add(cancelButton, BorderLayout.SOUTH);

        // Make the JFrame visible
        frame.setVisible(true);

        frame.addWindowListener(new

                                        WindowAdapter() {
                                            public void windowClosing(WindowEvent e) {
                                                backgroundExec.shutdown();
                                                System.out.println("Executor Shutdown-ed");
                                            }
                                        });
    }

    private static void doSomeLongComputations() {
        for (int i = 0; i < 10000; i++) {
            if (i / 7 == 0) {
                System.out.println("This is running in : " + Thread.currentThread().getName());
            }
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
