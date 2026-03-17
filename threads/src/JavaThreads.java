import java.util.Scanner;

public class JavaThreads {

  void main() throws Exception {
    var time = new GlobalTime();

    var tracks = new Track[]{
      new Track("backing", "backing.wav",  time),
      new Track("baixo",   "bass.wav",     time),
      new Track("bateria", "drums.wav",    time),
      new Track("teclado", "synths.wav",   time),
      new Track("vocal",   "vocal.wav",    time)
    };

    for (var track : tracks) track.start();

    var scanner = new Scanner(System.in);
    while (true) {
      printTracks(tracks);
      int input = scanner.nextInt();

      if (input == 0) {
        for (var track : tracks) track.stop();
        Thread.sleep(500);
        break;
      }

      if (input >= 1 && input <= 5) {
        var track = tracks[input - 1];
        if (track.isActive()) track.pause();
        else track.resume();
      }
    }

    System.exit(0);
  }

  public static void printTracks(Track[] tracks) {
    System.out.print("\033[H");
    System.out.println("MIXER DE FAIXAS");
    System.out.println("──────────────────");
    for (int i = 0; i < tracks.length; i++) {
      String status = tracks[i].isActive() ? "▶ tocando" : "⏸ pausado";
      System.out.println((i + 1) + ". " + tracks[i].getName() + " → " + status);
    }
    System.out.println("──────────────────");
    System.out.print("Digite 1-5 para pausar/resumir, 0 para sair: ");
  }

}

