package pa1;

import pa1.ministers.Economist;
import pa1.ministers.Minister;
import pa1.ministers.Scientist;
import pa1.ministers.WarGeneral;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds the necessary components for running the game
 */
public class GameMap {
    private List<Player> players;
    private Map<Integer, Cell> cityLocations;
    private Map<Integer, List<Integer>> connectedCities;
    private int width, height;

    /**
     * Load the map from a text file
     * The outline of the map format is given in the assignment description
     * <p>
     * You should instantiate cityLocations and connectedCities here
     *
     * @param filename
     * @throws IOException
     */
    public void loadMap(String filename) throws IOException {
        // TODO: store the map information inside cityLocations and connectedCities
        File file = new File(filename);
        Scanner reader = new Scanner(file);
        reader.useDelimiter("\\s|,");
        width = reader.nextInt();
        height = reader.nextInt();
        int numCities = reader.nextInt();

        for (int i = 0; i < numCities; i++) {
            int cityID = reader.nextInt();
            int x = reader.nextInt();
            int y = reader.nextInt();
            Cell c = new Cell(x, y);
            cityLocations.put(cityID, c);
        }

        reader.nextLine();
        reader.nextLine();

        for (int i = 0; i < numCities; i++) {
            String oneLine = reader.nextLine();
            Scanner stringReader = new Scanner(oneLine);
            stringReader.useDelimiter("\\s|,");

            List<Integer> tempList = new ArrayList<>();
            // The capacity of ArrayList grows automatically

            int cityID = stringReader.nextInt();
            if (stringReader.hasNextInt()) {
                tempList.add(stringReader.nextInt());
            }

            connectedCities.put(cityID, tempList);

            stringReader.close();
        }

        reader.close();
    }

    /**
     * Loads player data from text file
     * The outline of the player file format is given in the assignment description
     * <p>
     * You should instantiate the member variable players here
     *
     * @param filename
     * @throws IOException
     */
    public void loadPlayers(String filename) throws IOException {
        // TODO
        // 1
        File file = new File(filename);
        try (Scanner reader = new Scanner(file)) {
            // 2
            int numPlayers = reader.nextInt();
            players = new ArrayList<>(numPlayers);
            reader.nextLine();
            for (int i = 0; i < numPlayers; i++) {
                // 3.1
                String name = reader.next();
                int gold = reader.nextInt();
                int sciencePoint = reader.nextInt();
                int productionPoint = reader.nextInt();
                int numCities = reader.nextInt();
                int numMinisters = reader.nextInt();

                // 3.2
                Player player = new Player(name, gold, sciencePoint, productionPoint);

                // 3.3
                for (int j = 0; j < numCities; j++) {
                    int cityID = reader.nextInt();
                    String cityName = reader.next();
                    int population = reader.nextInt();
                    int troops = reader.nextInt();
                    int cropYields = reader.nextInt();
                    City city = new City(cityID, cityName, population, troops, cropYields);
                    player.getCities().add(city);
                }

                // 3.4
                for (int j = 0; j < numMinisters; j++) {
                    String type = reader.next();
                    int intelligence = reader.nextInt();
                    int experience = reader.nextInt();
                    int leadership = reader.nextInt();
                    switch (type) {
                        case "Scientist":
                            player.getMinisters().add(new Scientist(intelligence, experience, leadership));
                            break;
                        case "Economist":
                            player.getMinisters().add(new Economist(intelligence, experience, leadership));
                            break;
                        case "WarGeneral":
                            player.getMinisters().add(new WarGeneral(intelligence, experience, leadership));
                            break;
                    }
                }

                // 3.5
                players.add(player);
                reader.nextLine();
            }
        }
    }

    /**
     * @return list of all cities from every player
     */
    public List<City> getAllCities() {
        // TODO
        List<City> tempList = new ArrayList<>();
        // The capacity of ArrayList grows automatically
        for (Player player : players) {
            tempList.addAll(player.getCities());
        }
        return tempList;
    }

    /**
     * @param city
     * @return Cell object representing the city's location on the game map
     */
    public Cell getCityLocation(City city) {
        // TODO
        return cityLocations.get(city.getId());
    }

    public City getCityById(int id) {
        return getAllCities().stream()
                .filter(city -> city.getId() == id)
                .findAny()
                .orElse(null);
    }

    public Player getCityOwner(City city) {
        return getPlayers().stream()
                .filter(p -> p.hasCity(city))
                .findFirst()
                .orElse(new Player("", 0, 0, 0));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<City> getNeighboringCities(City city) {
        List<Integer> neighborIds = connectedCities.get(city.getId());
        return neighborIds.stream()
                .map(this::getCityById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void drawLine(char map[][], Cell a, Cell b) {
        if (a.getX() == b.getX()) {
            int minY = Math.min(a.getY(), b.getY());
            int maxY = Math.max(a.getY(), b.getY());

            for (int i = minY; i < maxY; i++) map[i][a.getX()] = '|';
        } else if (a.getY() == b.getY()) {
            int minX = Math.min(a.getX(), b.getX());
            int maxX = Math.max(a.getX(), b.getX());

            Arrays.fill(map[a.getY()], minX, maxX, '-');
        }
    }

    @Override
    public String toString() {
        char[][] map = new char[height][width];
        char[] vertSeparator = new char[width + 2];
        Arrays.fill(vertSeparator, '#');
        for (int i = 0; i < height; i++) Arrays.fill(map[i], ' ');

        for (int cityIdA : connectedCities.keySet()) {
            for (int cityIdB : connectedCities.get(cityIdA)) {
                Cell a = cityLocations.get(cityIdA);
                Cell b = cityLocations.get(cityIdB);
                drawLine(map, a, b);
            }
        }

        for (City city : getAllCities()) {
            Cell location = getCityLocation(city);
            int x = location.getX();
            int y = location.getY();

            char[] line = map[y];
            String cityText = city.getMapRepresentation(getCityOwner(city).getName());
            System.arraycopy(cityText.toCharArray(), 0, line, x - 3, cityText.length());
        }

        StringBuilder sb = new StringBuilder().append(vertSeparator).append('\n');
        for (char[] line : map) sb.append('#').append(line).append('#').append('\n');
        return sb.append(vertSeparator).toString();
    }
}