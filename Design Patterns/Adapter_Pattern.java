// Main class demonstrating the Adapter Pattern
public class Adapter_Pattern {

    public static void main(String[] args) {

        // Creating a modern media player (MP3 player) that supports all modern methods
        Modern_Media_Player M_Player = new Mp3_Player();

        // Playing an audio file using the modern player
        M_Player.Play("Noor Jahan Heart touching Ghazal");

        // The legacy player cannot be used directly because it doesn't implement the modern interface
        // Legacy_Media_Player L_player = new Legacy_Media_Player();
        // L_player.Start_Music(null); // This would not fit into our modern system design

        // Using the adapter to make the legacy player compatible with the modern interface
        Modern_Media_Player Player1 = new Media_Adapter(new Legacy_Media_Player());
        
        // Now we can use the old Legacy player through the modern interface
        Player1.Play("Rahat Fat Ali Khan's MBZ");
    }
}

// Modern interface that defines the expected functionalities
interface Modern_Media_Player {

    void Play(String File);   // Play a given audio file
    void Pause();             // Pause the current track
    void Play2x();            // Play the audio at double speed
}

// Concrete class implementing the modern interface
class Mp3_Player implements Modern_Media_Player {

    // Play the given file normally
    public void Play(String File) {
        System.out.println("The audio " + File + " is running now.");
    }

    // Pause the audio playback
    public void Pause() {
        System.out.println("The audio is paused.");
    }

    // Play the audio at 2x speed
    public void Play2x() {
        System.out.println("The audio is being played with 2x speed.");
    }
}

// Old/legacy media player that has a different interface
class Legacy_Media_Player {

    // Old method to start playing a track
    void Start_Music(String file) {
        System.out.println("The " + file + " is playing now.");
    }

    // Old method to stop playing the track
    void Stop_Music() {
        System.out.println("The track is stopped now.");
    }
}

// Adapter class that connects the modern interface with the legacy class
class Media_Adapter implements Modern_Media_Player {

    // Composition: Adapter has a reference to a Legacy_Media_Player object
    private Legacy_Media_Player O_Player;

    // Constructor that takes a legacy player as input
    public Media_Adapter(Legacy_Media_Player O_Player) {
        this.O_Player = O_Player;
    }

    // Adapting the Play method to call the legacy Start_Music method
    public void Play(String File) {
        O_Player.Start_Music(File);
    }

    // Adapting the Pause method to call the legacy Stop_Music method
    public void Pause() {
        O_Player.Stop_Music();
    }

    // Legacy player doesn't support 2x playback, so we handle it gracefully
    public void Play2x() {
        System.out.println("This feature is not supported by the Legacy_Media_Player.");
    }
}
