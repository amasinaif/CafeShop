package cafeshop;


public class Reservation {

    private int id;
    private static int staticID;

    private String date;
    private String startTime;
    private String endTime;

    private Customer customer;
    private Table table;

    public Reservation(int id, String date, String startTime, String endTime, Customer customer, Table table) {
        this.setId(id);
        this.setDate(date);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setCustomer(customer);
        this.setTable(table);
        staticID++;
    }

    public Reservation(String date, String startTime, String endTime, Customer customer, Table table) {
        this.setId(++staticID);
        this.setDate(date);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setCustomer(customer);
        this.setTable(table);
    }

    public Reservation() {
        this.setId(++staticID);
        this.setDate("");
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setCustomer(null);
        this.setTable(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    // get data object as string
    @Override
    public String toString() {
        return "id= " + id + "\ndate= " + date + "\nfrom= " + startTime + "\nto= " + endTime + "\ncustomer id= " + customer.getID() + "\ntable id= " + table.getID();
    }

}
