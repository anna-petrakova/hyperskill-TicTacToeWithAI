package tictactoe;

public class StrategyUser implements PlayerStrategy {
    private Field field;
    private String coordinates_str;
    private int x;
    private int y;
    private char player;

    public StrategyUser(Field field, char player) {
        this.field = field;
        this.player = player;
    }

    public void setCoordinates(String coordinates_str) {
        this.coordinates_str = coordinates_str;
    }

    @Override
    public boolean computeMove() {
        if (!isValidMove(coordinates_str)) {
            return false;
        }
        String[] coordinates = coordinates_str.split(" ");
        int x_user = Integer.parseInt(coordinates[0]);
        int y_user = Integer.parseInt(coordinates[1]);

        this.x = field.getXIdxInInternalRepresentation(x_user, y_user);
        this.y = field.getYIdxInInternalRepresentation(x_user, y_user);

        return true;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getPlayerSymbol() {
        return player;
    }

    @Override
    public String getMoveAnnouncement() {
        return "";
    }

    private boolean isValidMove(String coordinates_str) {
        String[] coordinates = coordinates_str.split(" ");
        if (coordinates.length != 2) {
            System.out.println("You should enter numbers!");
            return false;
        }
        String x_str = coordinates[0];
        String y_str = coordinates[1];
        int x_user,y_user;
        try {
            x_user = Integer.valueOf(x_str);
            y_user = Integer.valueOf(y_str);
        } catch (NumberFormatException ex) {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (x_user <= 0 || y_user <= 0 || x_user > 3 || y_user > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        int x = field.getXIdxInInternalRepresentation(x_user, y_user);
        int y = field.getYIdxInInternalRepresentation(x_user, y_user);

        if (field.get(x, y) != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }
}
