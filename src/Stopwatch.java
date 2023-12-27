import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener {
    private final JFrame frame;
    private final JButton startButton;
    private final JButton resetButton;
    private final JLabel timeLabel;
    private static int elapsedTime;
    private static int seconds;
    private static int minutes;
    private static int hours;
    private boolean started;
    private String seconds_String;
    private String minutes_String;
    private String hours_String;
    private Timer timer ;

    public Stopwatch() {
        frame = new JFrame();
        startButton = new JButton("START");
        resetButton = new JButton("RESET");
        timeLabel = new JLabel();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        started = false;
        seconds_String = String.format("%02d", seconds);
        minutes_String = String.format("%02d", minutes);
        hours_String = String.format("%02d", hours);
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime+=1000;
                hours=(elapsedTime/3600000);
                minutes=(elapsedTime/60000) % 60;
                seconds = (elapsedTime/1000) % 60;
                seconds_String = String.format("%02d", seconds);
                minutes_String = String.format("%02d", minutes);
                hours_String = String.format("%02d", hours);
                timeLabel.setText(hours_String+":"+minutes_String+":"+seconds_String);
            }
        });
        timeLabel.setText(hours_String+ ":"+minutes_String+":"+seconds_String);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);



        // Adding frame
        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
          if(!started){
              started=true;
              startButton.setText("STOP");
              start();
          }else{
              started=false;
              startButton.setText("START");
              stop();
          }
        }
        if(e.getSource()==resetButton){
            started=false;
            startButton.setText("START");
            reset();
        }

    }
    public void start(){
        timer.start();

    }
    public void stop(){
        timer.stop();

    }
    public void reset(){
        timer.stop();
        elapsedTime=0;
        seconds=0;
        minutes=0;
        hours=0;
        seconds_String = String.format("%02d", seconds);
        minutes_String = String.format("%02d", minutes);
        hours_String = String.format("%02d", hours);
        timeLabel.setText(hours_String+":"+minutes_String+":"+seconds_String);
    }
}
