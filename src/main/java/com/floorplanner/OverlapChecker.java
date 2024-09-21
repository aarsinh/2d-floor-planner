package src.main.java.com.floorplanner;

class OverlapChecker{

    public static boolean checkOverlap(Room room1, Room room2){
        if (room1.x + room1.width < room2.x || room2.x + room2.width < room1.x){ 
            return false;
        }
        if (room1.y + room1.height < room2.y || room2.y + room2.height < room1.y){
            return false;
        }
        return true;
    }

}