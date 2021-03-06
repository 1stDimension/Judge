package communication;

import mainlogic.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class MessengerTest {

    private Player playerOne = new Player("123456","One", "Jan", "Kowalski", "java -jar .\\ProxyPlayer.jar");
    private Player playerTwo = new Player("987654","Two", "John", "Bukowski", "java -jar .\\ProxyPlayer1.jar");
    private Messenger messenger = new Messenger(playerOne, playerTwo);

    @Before
    public void setUp() throws Exception {
        messenger.openCommunication(playerOne);
        messenger.openCommunication(playerTwo);
    }

    @After
    public void tearDown() {
        messenger.endCommunication();
    }

    //
//    TODO Find a way to way to test opened conection
    @Test
    public void openCommunication() {
//        messenger.openCommunication();
////        Process process = Runtime.getRuntime().exec("tasklist.exe");
////        Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
////        while (scanner.hasNext()) {
////            System.out.println(scanner.nextLine());
////        }
////        scanner.close();
//        messenger.endCommunication();
    }

    @Test
    public void sendPlaygroundSizePlayer1() {
        try {
            int playGroundSize = 10;
            messenger.send(String.valueOf(playGroundSize), playerOne);
        } catch (Exception e) {
            fail();
        }
    }
}