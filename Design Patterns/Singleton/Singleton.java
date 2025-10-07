public class Singleton {

    public static void main(String[] args){

        // First call to Get_instance: creates the single instance using the provided values (12, "Dark").
        Setting s1= Setting.Get_instance(12, "Dark");

        // Second call to Get_instance: this call returns the EXISTING instance created above. 
        // The new values (14, "Light") are IGNORED because the instance is not null.
        Setting s2= Setting.Get_instance(14, "Light");

        // Display shows the state set during the FIRST call (Font_Size=12, theme="Dark").
        Setting.display();


    }
    
}

// The Setting class enforces the Singleton design pattern.
class Setting{

    // 1. Private fields to hold the configuration state (the data).
    private int Font_Size;
    private String theme;

    // 2. Private Constructor: Prevents direct instantiation from outside the class (e.g., 'new Setting()').
    private Setting(int font, String theme){

        this.Font_Size=font;
        this.theme=theme;
    }

    // 3. Static private field: Holds the single instance of the Setting class.
    private static Setting Unique_Instance;

    // 4. Public static method: The controlled access point to get the single instance.
    public static Setting Get_instance(int font, String theme){

        // Lazy initialization check: if no instance exists yet...
        if(Unique_Instance==null){

            // ...create the instance using the private constructor.
            Unique_Instance= new Setting(font, theme);
        }

        // Return the existing (or newly created) single instance.
        return Unique_Instance;


    }

    // Method to display the state of the single instance.
    public static void display(){

        System.out.println("The theme is "+Unique_Instance.theme);
        System.out.println("The font size is "+Unique_Instance.Font_Size);
    }


}
