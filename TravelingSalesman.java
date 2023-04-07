import java.util.*;

public class TravelingSalesman {

    private int[][] distances;
    private int[] tour;

    public TravelingSalesman(int[][] distances) {
        this.distances = distances;
        this.tour = new int[distances.length];
    }

    public int[] solve() {
        // Initialize tour with the first city
        tour[0] = 0;
        for (int i = 1; i < tour.length; i++) {
            // Find the nearest unvisited city to the current city
            int nearestCity = findNearestUnvisitedCity(tour[i - 1]);
            tour[i] = nearestCity;
        }
        return tour;
    }

    private int findNearestUnvisitedCity(int city) {
        int nearestCity = -1;
        int shortestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < distances.length; i++) {
            if (i == city || isCityVisited(i)) {
                continue;
            }
            int distance = distances[city][i];
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestCity = i;
            }
        }
        return nearestCity;
    }

    private boolean isCityVisited(int city) {
        for (int i = 0; i < tour.length; i++) {
            if (tour[i] == city) {
                return true;
            }
        }
        return false;
    }

    public int getTourDistance() {
        int distance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            distance += distances[tour[i]][tour[i + 1]];
        }
        distance += distances[tour[tour.length - 1]][tour[0]];
        return distance;
    }

    public static void main(String[] args) {
        int[][] distances = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        TravelingSalesman tsp = new TravelingSalesman(distances);
        int[] tour = tsp.solve();
        System.out.println("Tour: " + Arrays.toString(tour));
        System.out.println("Tour distance: " + tsp.getTourDistance());
    }
}
