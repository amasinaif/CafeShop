package cafeshop;


public class Table {

    private int ID;
    private static int staticID;

    private int numberOfSeats;

    public Table() {
        this.setID(++staticID);
        this.setNumberOfSeats(0);
    }

    public Table(int ID, int numberOfSeats) {
        this.setID(ID);
        this.setNumberOfSeats(numberOfSeats);
        ++staticID;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    @Override
    public String toString() {
        return "ID= " + ID + "\nNumber of seats= " + numberOfSeats ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Table other = (Table) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.numberOfSeats != other.numberOfSeats) {
            return false;
        }
        return true;
    }
    

}
