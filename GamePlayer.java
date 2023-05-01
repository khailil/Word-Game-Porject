import java.util.*;
import java.io.*;

public class GamePlayer {
	
	//All relevant values declared outside of methods so they can be changed and updated as needed.
	private int rounds;
	private int succs;
	private int fails;
	private int tries;
	private double ratio = 1.0;
	private String name = new String();
	private String password = new String();
	private int optimal;
	
	
	//Makes a new GamePlayer object with relevant values initialized to the values entered when the constructor is called.
	public GamePlayer (String nam, String pass, int ron, int suc, int ort, int tar) {
		name = nam;
		password = pass;
		rounds = ron;
		succs = suc;
		optimal = ort;
		tries = tar;
		fails = 0;
		
	}
	//Makes a new GamePlayer object with all values except name initialized to 0.
	public GamePlayer (String nom, String pos) {
		name = nom;
		password = pos;
		rounds = 0;
		succs = 0;
		optimal = 0;
		tries = 0;
		fails = 0;
		
	}
	
	public GamePlayer (String nom) {
		name = nom;
		rounds = 0;
		succs = 0;
		optimal = 0;
		tries = 0;
		fails = 0;
		
	}
	
	
	//Calculates the new ratio based on the tries used and optimal moves and then increments the round and successes.
	//Also updates fails as a failsafe to make sure the count is always accurate.
	public void success(int tri, int opt) {
		tries += tri;
		optimal += opt;
		ratio = tries/optimal;
		
		succs++;
		rounds++;
		fails = rounds - succs;
	}
	
	
	public void failure() {
		rounds++;
		fails++;
	}
	
	public void setPass(String passTry) {
		password = passTry;
	}
	
	public boolean equals(GamePlayer test) {
		boolean awooga = false;
		if(password.equals(test.getPass()) && name.equals(test.getName())) {
			awooga = true;
		}
		return awooga;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPass() {
		return password;
	}
	
	//I was having trouble with newline stuff here for some reason so it looks really odd, but just assembles the info list as a StringBuilder.
	@Override 
	public String toString() {
		if(optimal > 0) {
			double trop = tries;
			double oop = optimal;
		
			ratio = trop/oop;
		}
		fails = rounds - succs;
		StringBuilder ungo = new StringBuilder();
		ungo.append(	"	Name: " + name).append(System.getProperty("line.separator"));
		ungo.append(	"	Rounds: " + rounds).append(System.getProperty("line.separator"));
		ungo.append(	"	Successes: " + succs).append(System.getProperty("line.separator"));
		ungo.append(	"	Failures: " + fails).append(System.getProperty("line.separator"));
		ungo.append(	"	Ratio (Successes Only): " + ratio).append(System.getProperty("line.separator"));
		return ungo.toString();
		
	}
	
	
	//Uses a StringBuilder to return data to be written into a file in a way that it can be read back into the program by the constructor.
	public String toStringFile() {
		StringBuilder unge = new StringBuilder();
		unge.append(name + "," +password + "," + rounds + "," + succs + "," + optimal + "," + tries);
		return unge.toString();
	}
}