package slogo.model.arena.turtle;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import slogo.model.arena.turtle.qualities.behavior.BehaviorDecorator;
import slogo.model.arena.turtle.qualities.behavior.DefaultBehavior;
import slogo.model.arena.turtle.qualities.behavior.IBehavior;
import slogo.model.arena.turtle.qualities.mode.DrawModeDecorator;
import slogo.model.arena.turtle.qualities.mode.IMode;
import slogo.model.arena.turtle.qualities.positioning.IPosition;
import slogo.model.arena.turtle.qualities.positioning.Position;
import slogo.util.line.Line;
import slogo.util.pen.Pen;
import slogo.util.trace.Trace;

/**
 * The "turtle" which serves as the model for responding to the user's commands
 * Overloaded with a lot of useful informations
 * 
 * @author Julian Genkins
 * 
 */
public class Turtle implements IArtist, IMorphable {
	private static final File DEFAULT_IMAGE = new File("src/default.png"); // TODO:
																			// Write
																			// in
																			// default
																			// filepath
	private String myName;
	private IBehavior myBehavior;
	private IMode myMode;
	private File myImage;
	private IPosition myPosition;
	private Trace myTrace;
	private List<Line> myLines;
	private boolean amVisible;

	// Constructors
	public Turtle(String name) {
		this(name, new Position());
	}

	public Turtle(String name, File image) {

		this(name, new Position(), new Trace(), image);
	}

	public Turtle(String name, IPosition position) {
		this(name, position, new Trace());
	}

	public Turtle(String name, IPosition position, Trace trace) {
		this(name, position, trace, DEFAULT_IMAGE);
	}

	public Turtle(String name, IPosition position, Trace trace, File image) {
		this(name, position, trace, image, new DefaultBehavior());
	}

	public Turtle(String name, IPosition position, Trace trace, File image,
			IBehavior behavior) {
		rename(name);
		setPosition(position);
		setTrace(trace);
		setImage(image);
		myTrace.setPen(new Pen());
		myBehavior = behavior;
		myLines = new ArrayList<Line>();
		amVisible = true;
	}

	// end Constructors

	// Artist

	public void rename(String name) {
		myName = name;

	}

	public String getName() {
		return myName;
	}

	@Override
	public void addAllLines(List<Line> newLines) {
		myLines.addAll(newLines);

	}

	@Override
	public void addLine(Line newLine) {
		myLines.add(newLine);
	}

	@Override
	public List<Line> getLines() {
		return myLines;
	}

	@Override
	public void clearLines() {
		myLines.clear();

	}

	@Override
	public void revomeLine(Integer index) {
		myLines.remove(index);

	}

	@Override
	public void removeLines(List<Line> lines) {
		myLines.removeAll(lines);

	}

	@Override
	public Trace getTrace() {
		return myTrace;
	}

	@Override
	public void setTrace(Trace newTrace) {
		myTrace = newTrace;
	}

	@Override
	public IBehavior getBehavior() {
		return myBehavior;
	}

	@Override
	public File getImage() {
		return myImage;
	}

	public String getImagePath() {
		return myImage.getPath();
	}

	@Override
	public IPosition getPosition() {
		return myPosition;
	}

	// Morphable

	@Override
	public int move(double distance) {
		myLines.add(myBehavior.applyBehavior(new Line(myTrace, myPosition,
				distance)));
		myPosition.setLocation(myLines.get(myLines.size() - 1).getP2());
		myTrace.getPen().putDown();
		return (int) myLines.get(myLines.size() - 1).length();
	}

	@Override
	public int moveTo(Point target) {
		myTrace.getPen().putUp();
		myPosition.changeAngle(target);
		return move(target.distance(myPosition.getLocation()));

	}

	@Override
	public int rotate(double dAngle) {
		myPosition.changeAngle(dAngle);

		return (int) Math.abs(dAngle);
	}

	public int setHeading(double heading) {

		return this.rotate(heading - myPosition.getAngle());
	}

	@Override
	public void addBehavior(BehaviorDecorator behavior) {
		behavior.setSubBehavior(myBehavior);
		myBehavior = behavior;
	}

	@Override
	public void setImage(File image) {
		myImage = image;
	}

	@Override
	public void setPosition(IPosition position) {
		myPosition = position;
	}

	public boolean isVisible() {
		return amVisible;
	}

	public int resetTurtle() {

		this.moveTo(new Point());
		this.myPosition.setAngle(IPosition.DEFAULT_ANGLE);
		int d = (int) myLines.get(myLines.size() - 1).length();
		myLines.clear();
		return d;
	}

	@Override
	public IMode getMode() {

		return myMode;
	}

	@Override
	public void addMode(DrawModeDecorator mode) {
		mode.setSubMode(myMode);
		myMode = mode;

	}

	@Override
	public Iterable<Line> linesToDraw(int start) {
		return myMode.applyMode(myLines.subList(start, myLines.size() - 1));
	}

}
