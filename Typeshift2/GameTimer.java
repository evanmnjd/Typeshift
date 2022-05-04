import java.util.TimerTask;

public class GameTimer extends TimerTask {
	
	public static int countdown = 20;
	
	public void run() {
		countdown--;
		Typeshift.UpdateTimer();
		if ((countdown <= 0) || (Typeshift.end == true)) {
			cancel();
			Typeshift.dead = true;
		}
	}
	
	public static TimerTask CreateTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				countdown--;
				Typeshift.UpdateTimer();
				if ((countdown <= 0) || (Typeshift.end == true)) {
					cancel();
					Typeshift.dead = true;
				}
			}
		};
	}
	
	public static void main(String[] args) {
		
	}
}