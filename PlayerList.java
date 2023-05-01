// CMPINF 0401 Fall 2022
// Shell of the PlayerList class.
// This class represents a collection of players -- a very simple database.  The
// collection can be represented in various ways.  However, for this assignment
// you are required to use an array of GamePlayer.

// Note the imports below.  java.util.* is necessary for a Scanner and java.io.* is
// necessary for various File classes.
import java.util.*;
import java.io.*;

public class PlayerList
{
	private GamePlayer [] players;  // array of GamePlayer
	private int size;				// logical size
	private String file;			// name of file associated with this PlayerList
	private int arrLen = 0;
	int newSi;
	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new GamePlayer object for each line
	// and putting them into the array.  Your initial size for the array MUST be 3 and
	// if you fill it should resize by doubling the array size.  You will need to write
	// a resize() method to do the resizing.
	
	// Note that this method throws IOException. Because of this, any method that CALLS
	// this method must also either catch IOException or throw IOException.  Note that 
	// the main() in PlayerListTest.java throws IOException.  Keep this in mind for your
	// main program (Assig3.java).  Note that your saveList() method will also need
	// throws IOException in its header, since it is also accessing a file.
	public PlayerList(String fName) throws IOException
	{
		players = new GamePlayer[3];
		File fiole = new File(fName);
		file = fName;
		Scanner playScan = new Scanner(fiole);
		playScan.useDelimiter("\\D");
		size = 0;
		
		arrLen = players.length;
		while(playScan.hasNextLine()) {
			
			String slice = new String(playScan.nextLine());
			String[] slices = slice.split(",");
			String name = new String(slices[0]);
			String passw = new String(slices[1]);
			int round = Integer.parseInt(slices[2]);
			int succ = Integer.parseInt(slices[3]); 
			int opt = Integer.parseInt(slices[4]);
			int troy = Integer.parseInt(slices[5]);
			
			
			
			
			players[size] = new GamePlayer(name,passw,round,succ,opt,troy);
			size ++;
			
			resize();
			
		}
		
		
		
	}
	
	public boolean addPlayer(GamePlayer bingus) {
		boolean bababooey = false;
		String pName = bingus.getName();
		resize();
		if(!containsName(pName)) {
			players[size] = bingus;
			size++;
			bababooey = true;
		}
		
		
		return bababooey;
		
	}
	
	
	public boolean containsName(String test) {
		boolean cont = false;
		
		for(int i = 0; i < size; i++) {
			if(players[i].getName().equals(test)) {
				cont = true;
			}
		}
		
		return cont;
	}
	
	public GamePlayer authenticate(GamePlayer auth) {
		GamePlayer bazooka = new GamePlayer(auth.getName());
		for(int i = 0; i < size; i++) {
			if(players[i].equals(auth)) {
				bazooka = players[i];
			}
			else {
				bazooka = null;
			}
			
		}
		return bazooka;
	}
	
	
	public int size() {
		return size;
	}
	
	public int capacity() {
		return arrLen;
	}
	
	public String toString() {
		StringBuilder printer = new StringBuilder();
		
		printer.append("Total players: " + size + "\n");
		
		for(int i = 0; i < size; i++) {
			printer.append(players[i].toString() + "\n");
		}
		
		return printer.toString();
	}
	
	
	public void resize() {
		if(size >= arrLen) {
				newSi = size * 2;
				GamePlayer[] replace = new GamePlayer[newSi];
				
				for(int i = 0; i < size; i++) {
					replace[i] = players[i];
				}
				
				players = replace;
			}
	}
	
	public void saveList() throws IOException {
		
		File funko = new File(file);
		
		PrintWriter w = new PrintWriter(funko);
		for(int i = 0; i < size; i++) {
			String text = new String(players[i].toStringFile());
			w.println(text);
		}
		w.close();

		
		
		
	}

	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the modified
	// GamePlayer class before the PlayerList class will work, since your array is an
	// array of GamePlayer objects.
	
	// You may also want to add some methods that are not tested in PlayerListTest.java.
	// Think about what you need to do to a PlayerList in your Assig3 program and write 
	// the methods to achieve those tasks.  However, make sure you are always thinking
	// about encapsulation and data abstraction.
}