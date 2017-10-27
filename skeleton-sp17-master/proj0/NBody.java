public class NBody {
    public static double readRadius(String fileName) {

        double radius = 0;
        double i = 0;
        In stream = new In(fileName);

        // radius is on line 2 of the text file, so run until second line
        while(!stream.isEmpty() && i < 2) {
            radius = stream.readDouble();
            System.out.print(radius);
            i++;
        }

        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In stream = new In(fileName);
        int planetCount = stream.readInt();
        double radius = stream.readDouble();

        Planet[] planets = new Planet[planetCount];
        int i = 0;

        while(i < planetCount) {
            double xPos = stream.readDouble();
            double yPos = stream.readDouble();
            double xVel = stream.readDouble();
            double yVel = stream.readDouble();
            double mass = stream.readDouble();
            String imgFileName = stream.readString();
            System.out.print(xPos);
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);

            i++;
        }
        return planets;
    }

    public static void main(String[] args) {
        /*
        Create a main method in the NBody class. Write code so that your NBody class performs the following steps:

        Store the 0th and 1st command line arguments as doubles named T and dt.
        Store the 2nd command line argument as a String named filename.
        Read in the planets and the universe radius from the file described by filename using your methods from earlier in this assignment.
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Initial Drawing Section
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");




        for (Planet p : planets) {
            p.draw();
        }

        // animation
        double time = 0;
        while (time != T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int j = 0; j < planets.length; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }

            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }


            // draw background and planets
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show(10);
            time += dt;
        }
    }
}
