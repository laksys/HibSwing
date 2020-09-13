package com.king;

import com.king.model.Student;
import com.king.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class App extends JFrame {
    private Session session;
    private JTextField tfRollno, tfName, tfMark;

    public App(){
        super("CRUD using Hibernate");

        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("RollNo:"));
        northPanel.add(tfRollno = new JTextField(10));
        northPanel.add(new JLabel("Name:"));
        northPanel.add(tfName = new JTextField(20));
        northPanel.add(new JLabel("Mark:"));
        northPanel.add(tfMark = new JTextField(5));
        JButton btn;
        northPanel.add(btn = new JButton("Add"));
        add("North", northPanel);

        session = HibernateUtil.getSessionFactory().openSession();

        List list = session.createQuery("from Student").getResultList();
        DefaultTableModel tableModel = new DefaultTableModel();
        



        setSize(640, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        btn.addActionListener((actionEvent -> {
            session.beginTransaction();

            Student student = new Student();

            student.setRollno(Integer.parseInt(tfRollno.getText()));
            student.setName(tfName.getText());
            student.setMark(Integer.parseInt(tfMark.getText()));

            session.save(student);
            session.getTransaction().commit();
            System.out.println("Record saved successfully!");
        }));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                session.close();
                HibernateUtil.shutdown();
                e.getWindow().dispose();
            }
        });
    }
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(()->new App());
    }
}