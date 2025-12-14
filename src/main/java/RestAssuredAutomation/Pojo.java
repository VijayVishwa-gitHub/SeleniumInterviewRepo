package RestAssuredAutomation;

import java.util.List;

public class Pojo {


/*    {
        "orderId": "ORD123",
            "customer": {
        "id": "C101",
                "name": "Amit"
    },
        "items": [
        {
            "itemId": "I1",
                "qty": 2,
                "price": 500
        },
        {
            "itemId": "I2",
                "qty": 1,
                "price": 1200
        }
  ]}

*/

    private String orderId;
    private Pojo2 customer;
    private List<Pojo3> items;


    public void setOrderID(String orderID){
        this.orderId = orderID;
    }

    public String getOrderID(){
        return orderId;
    }

    public void setCustomer(Pojo2 customer){
        this.customer = customer;
    }

    public Pojo2 getCustomerDetails(){
        return customer;
    }

    public void setItems(List<Pojo3> items){
        this.items = items;
    }

    public List<Pojo3> getItems(){
        return items;
    }


    /******************************************/
    private String message;
    private int userId;
    private String title;


    public void putMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
    public void putID(int Id){
        this.userId = Id;
    }

    public int getId(){
        return userId;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



}
