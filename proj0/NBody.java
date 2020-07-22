public class NBody{
	public static double readRadius(String path){
		In in = new In(path);
		int number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String path){
		In in = new In(path);
		int number = in.readInt();
		double radius = in.readDouble();
		Body[] allBodys = new Body[number];
		for (int i = 0; i < number; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			allBodys[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allBodys;
	}

	public static void main(String[] args){
		System.out.println("Test.");
		double bigT = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] allBodys = readBodies(filename);

		String imageToDraw = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(2000);

		for (Body b : allBodys){
			b.draw();
		}		

		double[] xForces = new double[allBodys.length];
		double[] yForces = new double[allBodys.length];

		for (double time = 0; time <= bigT; time += dt){
			for (int i = 0; i < allBodys.length; i ++){
				xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
				yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);
			}
			for (int i = 0; i < allBodys.length; i ++){
				allBodys[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			for (Body b : allBodys){
				b.draw();
			}		
			StdDraw.show();

			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", allBodys.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allBodys.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allBodys[i].xxPos, allBodys[i].yyPos, allBodys[i].xxVel,
                  allBodys[i].yyVel, allBodys[i].mass, allBodys[i].imgFileName);   
}
	}		

}