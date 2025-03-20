import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AudioPlayer {
    private AdvancedPlayer audioPlayer;
    private FileInputStream audioStream;
    private boolean isPaused;
    private int pausePosition;
    private long playbackStartTime;
    private String currentAudioPath;
    private Thread playbackThread;

    public void play(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);

            // Verificações antes de abrir o arquivo
            System.out.println("Abrindo arquivo: " + audioFilePath);
            System.out.println("Caminho absoluto: " + audioFile.getAbsolutePath());
            System.out.println("Arquivo existe? " + audioFile.exists());

            if (!audioFile.exists()) {
                System.err.println("Erro: Arquivo não encontrado -> " + audioFilePath);
                return;
            }

            if (audioPlayer != null) {
                audioPlayer.close(); // Fecha o player anterior se houver um ativo
            }

            audioStream = new FileInputStream(audioFile);
            audioPlayer = new AdvancedPlayer(audioStream);
            currentAudioPath = audioFilePath;

            audioPlayer.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    System.out.println("Reprodução concluída.");
                    pausePosition = 0;
                    isPaused = false;
                    cleanup();
                }
            });

            playbackThread = new Thread(() -> {
                try {
                    playbackStartTime = System.currentTimeMillis() - pausePosition;
                    audioPlayer.play(pausePosition, Integer.MAX_VALUE);
                } catch (JavaLayerException e) {
                    System.err.println("Erro ao reproduzir o arquivo MP3: " + e.getMessage());
                }
            });

            playbackThread.start();

            isPaused = false;
            System.out.println("Reproduzindo: " + audioFilePath);
        } catch (IOException | JavaLayerException e) {
            System.err.println("Erro ao carregar o arquivo MP3: " + e.getMessage());
        }
    }

    public void pause() {
        if (audioPlayer != null && !isPaused) {
            pausePosition = (int) (System.currentTimeMillis() - playbackStartTime); // Salva o tempo atual
            isPaused = true;
            System.out.println("Música pausada em: " + pausePosition + " ms");

            // Fecha o player e interrompe a thread
            audioPlayer.close();
            audioPlayer = null;

            if (playbackThread != null) {
                playbackThread.interrupt();
                playbackThread = null;
            }
        }
    }

    public void resume() {
        if (isPaused) {
            if (currentAudioPath == null) {
                System.err.println("Erro: Nenhum arquivo para retomar!");
                return;
            }

            File audioFile = new File(currentAudioPath);
            if (!audioFile.exists()) {
                System.err.println("Erro: Arquivo não encontrado -> " + currentAudioPath);
                return;
            }

            System.out.println("Retomando a música de: " + pausePosition + " ms");
            play(currentAudioPath); // Reinicia a reprodução do ponto salvo
        }
    }

    public void stop() {
        if (audioPlayer != null) {
            audioPlayer.close();
            isPaused = false;
            pausePosition = 0;
            System.out.println("Música parada.");
        }
    }

    private void cleanup() {
        if (audioPlayer != null) {
            audioPlayer.close();
        }
        if (audioStream != null) {
            try {
                audioStream.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o stream do arquivo: " + e.getMessage());
            }
        }
    }
}