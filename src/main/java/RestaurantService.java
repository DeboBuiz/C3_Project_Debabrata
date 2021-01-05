import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        boolean restaurantFound = false;
        Restaurant searchRestaurant = null;
        for (Restaurant restaurant : restaurants){
            if (restaurant.getName().equals(restaurantName)){
                restaurantFound = true;
                searchRestaurant = restaurant;
            }
        }

        //if the restaurant is not found, throw exception.
        if (restaurantFound) {
            return searchRestaurant;
        }else{
            throw new restaurantNotFoundException(restaurantName);
        }
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getTotalPriceOfSelectedItems(List<String> items, String restaurantName) throws restaurantNotFoundException, itemNotFoundException{
        int totalPrice = 0;
        Restaurant restaurant = findRestaurantByName(restaurantName);

        for(String name: items){
            Item item = restaurant.findItemByName(name);
            totalPrice = totalPrice + item.getPrice();
        }

        return totalPrice;
    }
}
