package Webservice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

public class TCNotificationPanel extends JWindow{
	private static final long serialVersionUID = 1L;
	public TCNotificationPanel() throws InterruptedException {
		String s="vijay";
		setVisible(true);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		
		//Setting the size of GeryPanel
		setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,33);
		

		//Setting the GeryPanel locationj in desktop
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(0,screenSize.height - taskBarSize - getHeight());
		//invoking the timer to scroll the text in GreyPanel
		ScrollText scroll = new ScrollText(s, 185,this.getContentPane());
		scroll.start();
		Thread.sleep(25000);
		scroll.stop();
		this.dispose();
	}
	public static void main(String[] args) throws InterruptedException {		
		new TCNotificationPanel();
	    }
	}

class ScrollText implements ActionListener {
	private static final int RATE = 10;
    private final Timer timer = new Timer(1000 / RATE, this);
    private final JLabel label = new JLabel();
    private final String s;
    private final int n;
    private int index;

    public ScrollText(String s, int n,Container c) {
        if (s == null || n < 1) {
            throw new IllegalArgumentException("Null string or n < 1");
        }
       
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        this.s = sb + s + sb;
        this.n = n;
        label.setFont(new Font("Serif", Font.ITALIC, 30));
        label.setForeground(Color.CYAN);
        label.setText(sb.toString());
        c.add(label);
       
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        label.setText(s.substring(index, index + n));
    }
}
