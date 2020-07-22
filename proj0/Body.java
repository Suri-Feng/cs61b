import java.lang.Math;

public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	final static double gConstant = 6.67e-11;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = "images/"+img;
	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;

	}

	public double calcDistance(Body b){
		double distance;
		distance = Math.sqrt(Math.pow(b.xxPos- this.xxPos, 2)+Math.pow(b.yyPos - this.yyPos, 2));
		return distance;
	}

	public double calcForceExertedBy(Body b){
		double distance = this.calcDistance(b);
		double force = gConstant*this.mass*b.mass/Math.pow(distance, 2);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double distanceX = b.xxPos - this.xxPos;
		double forceX = this.calcForceExertedBy(b)*distanceX/this.calcDistance(b);
		return forceX;
	}

	public double calcForceExertedByY(Body b){
		double distanceY = b.yyPos - this.yyPos;
		double forceY = this.calcForceExertedBy(b)*distanceY/this.calcDistance(b);
		return forceY;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double netForceX = 0;
		for(int i = 0; i<allBodys.length; i++){
			if (this.equals(allBodys[i])){
				continue;
			}
			netForceX += this.calcForceExertedByX(allBodys[i]);
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double netForceY = 0;
		for(int i = 0; i<allBodys.length; i++){
			if (this.equals(allBodys[i])){
				continue;
			}
			netForceY += this.calcForceExertedByY(allBodys[i]);
		}
		return netForceY;
	}

	public void update(double time, double forceX, double forceY){
		double accelerationX = forceX/this.mass;
		double accelerationY = forceY/this.mass;
		this.xxVel = this.xxVel + time*accelerationX;
		this.yyVel = this.yyVel + time*accelerationY;
		this.xxPos = this.xxPos + time*this.xxVel;
		this.yyPos = this.yyPos + time*this.yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, imgFileName);
		StdDraw.show();
	}
}
