package finalProject;

public class Test {
	
	public static void main(String[]args) {
		
		HumanPlayer obj1 = new HumanPlayer();
		SentientAI obj2 = new SentientAI();
	//	HumanPlayer obj2 = new HumanPlayer();
		
		//NOTE: P1 IS RED AND P2 IS BLUE
		//NOTE: COLUMNS TO LEFT TO RIGHT STARTING AT 0
		
		ConnectFourBoard obj3 = new ConnectFourBoard(7,6);
		
		obj3.play(obj1, obj2, true, false);
	}

}
