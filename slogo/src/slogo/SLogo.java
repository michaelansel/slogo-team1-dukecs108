package slogo;

import slogo.controller.Controller;
/**
 * Main method, starts our program by creating the new controller
 * @author dave
 *
 */
public class SLogo {
	public static Controller myController;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myController = new Controller();
		
	}

}
