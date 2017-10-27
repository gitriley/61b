public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	};

	public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;

	}

    public double calcDistance(Planet otherPlanet) {
        double xDistance;
        double yDistance;
        double distance;

        xDistance = xxPos - otherPlanet.xxPos;
        yDistance = yyPos - otherPlanet.yyPos;

        distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }

    public double calcForceExertedBy(Planet otherPlanet) {
        // G (6.67 * 10-11 N-m2 / kg2)
        // G = 6.67 x 10^-11 = Netwon's gravitational constant
        // F = G * m1 * m2 / r2
	    // F = G * (2.0 * 1030 Kg) * (6.0 * 1026 Kg) / (1.5 * 1012 m)2 = 3.6 * 1022 N

        double G = 6.67 * Math.pow(10, -11);
        double distance = calcDistance(otherPlanet);

        double force = (G * mass * otherPlanet.mass) / (distance * distance);

        return force;
    }

    public double calcForceExertedByX(Planet otherPlanet) {
        // Fx = F * dx / r
        double force = calcForceExertedBy(otherPlanet);
        double xDistance = otherPlanet.xxPos - xxPos;
        double distance = calcDistance((otherPlanet));

        // calculates the xForce
        double xForce = force * xDistance / distance;

        return xForce;
    }

    public double calcForceExertedByY(Planet otherPlanet) {
        // Fy = F * dy / r

        double force = calcForceExertedBy(otherPlanet);
        double yDistance = otherPlanet.yyPos - yyPos;
        double distance = calcDistance((otherPlanet));

        // calculates the xForce
        double yForce = force * yDistance / distance;

        return yForce;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double[] distances = new double[allPlanets.length];
        double netXForce = 0.0;
        for (int i=0; i < allPlanets.length - 1; i++) {

            if (this.equals(allPlanets[i])) {
                continue;
            }

            netXForce = netXForce + calcForceExertedByX(allPlanets[i]);
        }

        return netXForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {

        double netYForce = 0.0;
	//    for (int i=0; i < allPlanets.length - 1; i++) {
            for (Planet p : allPlanets) {

	        if (this.equals(p)) {
                continue;
	        }

            netYForce = netYForce + calcForceExertedByY(p);
        }

        return netYForce;
    }

    public void update(double dt, double fX, double fY) {
        // anet,x = Fnet,x / m = -5 N / 1 Kg = -5 m/s2
        // anet,y = Fnet,y / m = -2 N / 1 Kg = -2 m/s2

        // calculate net acceleration
        double netXAcceleration = fX/mass;
        double netYAcceleration = fY/mass;


        // update velocities
        xxVel = xxVel + dt * netXAcceleration;
        yyVel = yyVel + dt * netYAcceleration;


        // update positions
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw () {
        String filename = "./images/" + this.imgFileName;
        StdDraw.picture(xxPos, yyPos, filename);
    }
}
