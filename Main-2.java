////////////////////////////////////////////////////////////////////////////////
// File:            Main.java
// Course:          CSC 232 A, Spring 2021
// Authors:         Hamza Sultan Khan Niazi, Brian Howard
//
// Acknowledgments: None
//
// Online sources:  None
////////////////////////////////////////////////////////////////////////////////

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import csc232.Animation;
import csc232.AnimationComponent;
import csc232.Image;

public class Main
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Animation");

      // Construct a simple animation: a large circle, followed by a
      // rectangle that grows from the left to fill the window, followed
      // by a circle that grows from the center.
      // WARNING: there are magic numbers here, and I don't care.
      Animation animation = Animation.fixed(Image.circle())
          .before(new Animation(time -> Image.createCoolGuy().translate(0.25,0.25).scale(0.2,0.2).translate(1*time, 0.5*2.5*time*time).over(Image.square()))
    		  .before(new Animation(time -> Image.createCoolGuy().scale(0.2,0.2).translate(0.4, 0.95).translate(0, 0.5*-1*time).over(Image.square()))
    		  .before(new Animation(time -> Image.createCoolGuy().scale(0.2,0.2).translate(0.4, -0.05).translate(0, 0.5*1*time).over(Image.square())))
    		  .before(new Animation(time -> Image.createCoolGuy().scale(0.2,0.2).translate(-0.05, 0.4).translate(0.5*1*time, 0).over(Image.square())))
    		  .before(new Animation(time -> Image.createCoolGuy().scale(0.2,0.2).translate(0.95, 0.4).translate(0.5*-1*time, 0).over(Image.square()))))
    		  .before(Animation.fixed(Image.createCoolGuy().over(Image.square()))));

      final AnimationComponent view = new AnimationComponent(animation);
      view.setPreferredSize(new Dimension(350, 350));

      // Build a simple user interface around the AnimationComponent
      frame.setLayout(new BorderLayout());
      frame.add(view, BorderLayout.CENTER);

      Box buttons = Box.createHorizontalBox();

      JButton startButton = new JButton("Start");
      startButton.addActionListener(event -> view.start());
      buttons.add(startButton);

      JButton stopResumeButton = new JButton("Resume");
      stopResumeButton.addActionListener(event -> {
         if (view.isRunning()) {
            view.stop();
         }
         else {
            view.resume();
         }
      });
      stopResumeButton.setEnabled(false); // initially disabled
      buttons.add(stopResumeButton);

      JButton slowerButton = new JButton("Slower");
      slowerButton.addActionListener(
               event -> view.setDuration(view.getDuration() * 6 / 5));
      buttons.add(slowerButton);

      JButton fasterButton = new JButton("Faster");
      fasterButton.addActionListener(
               event -> view.setDuration(view.getDuration() * 5 / 6));
      buttons.add(fasterButton);

      JCheckBox loopButton = new JCheckBox("Loop");
      loopButton.addActionListener(
               event -> view.setLoop(loopButton.isSelected()));
      buttons.add(loopButton);

      frame.add(buttons, BorderLayout.SOUTH);

      view.addRunStateListener(event -> {
         if (view.isRunning()) {
            startButton.setEnabled(false);
            stopResumeButton.setText("Stop");
            stopResumeButton.setEnabled(true);
         }
         else {
            startButton.setEnabled(true);
            stopResumeButton.setText("Resume");
            stopResumeButton.setEnabled(view.isPaused());
         }
      });

      frame.pack();
      frame.setVisible(true);
   }
}
