public class BuggyProgram {
    public static void main (String[] args) {

        int pizzaSlices = 10;
        int people;

        // How many slices of pizza should each person get?
        int slicesPerPerson = pizzaSlices / people;
        System.out.println("Each person should have " + slicesPerPerson + " slices of pizza.");

        // Are there more slices of pizza than people?
        boolean morePizza = pizzaSlices < people;
        System.out.println("Are there more slices of pizza than people at the party?");
        System.out.println(morePizza);
    }
}
