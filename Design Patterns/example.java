public class example {

    public static void main(String[] args) {

        User u1= new User();
        u1.Remainder();
        
    }
    
}

class Siri {

    User u1;

    public Siri(User u1){

        this.u1=u1;
    }


    void NotifyUser(){

        System.out.println("The remainder is due...");
        u1.ReceiveNotification("Good morning! It’s time to get up — your SE322 class starts at 11:30");
    }
}

class User {

    private Siri s1= new Siri(this);




    void ReceiveNotification(String message){

        System.out.println("Notification is received "+message);
    }


    void Remainder(){

        s1.NotifyUser();
    }
}
