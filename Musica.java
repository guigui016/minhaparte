import java.io.File;

public class Musica {
    private String titulo;
    private String artista;
    private String album;
    private String genero;
    private int anoLancamento;
    private double duracao; // Duração em segundos
    private String caminhoArquivo;
    private AudioPlayer player;
    private String caminhoArquivo1 = "C:\\Users\\guiol\\Temp\\Vampiro.mp3";
    
    // Construtor
    public Musica(String titulo, String artista, String album, String genero, int anoLancamento, double duracao, String caminhoArquivo) {
        setTitulo(titulo);
        setArtista(artista);
        setAlbum(album);
        setGenero(genero);
        setAnoLancamento(anoLancamento);
        setDuracao(duracao);
        setCaminhoArquivo(caminhoArquivo);
        this.player = new AudioPlayer();
    }

    // Método para reproduzir a música
    public void reproduzir() {
        if (caminhoArquivo == null || caminhoArquivo.isEmpty()) {
            System.out.println("Caminho do arquivo de áudio não especificado.");
            return;
        }

        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de áudio não encontrado: " + caminhoArquivo);
            return;
        }

        try {
            if (caminhoArquivo.endsWith(".mp3") || caminhoArquivo.endsWith(".wav")) {
                player.play(caminhoArquivo);
            } else {
                System.out.println("Formato de arquivo não suportado: " + caminhoArquivo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao reproduzir a música: " + e.getMessage());
        }
    }

    // Método para pausar a música
    public void pausar() {
        if (player != null) {
            player.pause();
        } else {
            System.out.println("Player não inicializado.");
        }
    }

    // Método para retomar a música
    public void retomar() {
        if (player != null) {
            player.resume();
        } else {
            System.out.println("Player não inicializado.");
        }
    }

    // Método para parar a música
    public void parar() {
        if (player != null) {
            player.stop();
        } else {
            System.out.println("Player não inicializado.");
        }
    }

    // Getters e Setters com validações
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio.");
        }
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.isEmpty()) {
            throw new IllegalArgumentException("Artista não pode ser nulo ou vazio.");
        }
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album; // Album pode ser nulo (não obrigatório)
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero; // Gênero pode ser nulo (não obrigatório)
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        if (anoLancamento < 0) {
            throw new IllegalArgumentException("Ano de lançamento não pode ser negativo.");
        }
        this.anoLancamento = anoLancamento;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        if (duracao < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa.");
        }
        this.duracao = duracao;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        if (caminhoArquivo == null || caminhoArquivo.isEmpty()) {
            throw new IllegalArgumentException("Caminho do arquivo não pode ser nulo ou vazio.");
        }
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
               "Artista: " + artista + "\n" +
               "Álbum: " + album + "\n" +
               "Gênero: " + genero + "\n" +
               "Ano de Lançamento: " + anoLancamento + "\n" +
               "Duração: " + duracao + " segundos\n" +
               "Caminho do Arquivo: " + caminhoArquivo;
    }

    // Classe interna AudioPlayer com suporte para pausa e retomada
    private static class AudioPlayer {
        private boolean isPlaying = false;
        private boolean isPaused = false;
        private long pausePosition = 0; // Armazena o momento da pausa em milissegundos

        public void play(String caminhoArquivo) {
            if (isPlaying && !isPaused) {
                System.out.println("A música já está sendo reproduzida.");
                return;
            }

            if (isPaused) {
                System.out.println("Retomando a música a partir de " + pausePosition + "ms.");
                isPaused = false;
            } else {
                System.out.println("Reproduzindo: " + caminhoArquivo);
                pausePosition = 0; // Reinicia o ponto de reprodução
            }

            isPlaying = true;
            // Simulação de reprodução de áudio
        }

        public void pause() {
            if (isPlaying && !isPaused) {
                isPaused = true;
                isPlaying = false;
                pausePosition = 5000; // Exemplo: armazena o momento da pausa (5 segundos)
                System.out.println("Música pausada em " + pausePosition + "ms.");
            } else {
                System.out.println("A música não está sendo reproduzida ou já está pausada.");
            }
        }

        public void resume() {
            if (isPaused) {
                System.out.println("Retomando a música a partir de " + pausePosition + "ms.");
                isPaused = false;
                isPlaying = true;
            } else {
                System.out.println("A música não está pausada.");
            }
        }

        public void stop() {
            if (isPlaying || isPaused) {
                System.out.println("Música parada.");
                isPlaying = false;
                isPaused = false;
                pausePosition = 0; // Reinicia o ponto de reprodução
            } else {
                System.out.println("A música já está parada.");
            }
        }
    }
}