import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();

        //returns true if the currentTime is between opening and closing time.
        return !currentTime.isBefore(openingTime) && !currentTime.isAfter(closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        //menu items are added using addToMenu option. So this method will simply return the available menu.
        return menu;
    }

    public Item findItemByName(String itemName) throws itemNotFoundException{
        Item searchItem = null;
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                searchItem = item;
        }

        if (searchItem != null){
            return searchItem;
        }else{
            throw new itemNotFoundException(itemName);
        }

    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
