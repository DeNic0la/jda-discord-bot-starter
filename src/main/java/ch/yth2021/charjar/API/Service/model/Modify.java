package ch.yth2021.charjar.API.Service.model;

public class Modify {
    public PointAction action;
    public int number;

    /**
     * generates the Modify object according to the number (make number negative for substract)
     *
     * @param number number to add or subtract
     */
    public Modify(int number) {
        if (number > 0) {
            this.number = number;
            this.action = PointAction.ADD;
        } else {
            this.number = -number;
            this.action = PointAction.SUBTRACT;
        }
    }

    @Override
    public String toString() {
        return "{action:" + action.name() + " ,number:" + number + "}";
    }
}
